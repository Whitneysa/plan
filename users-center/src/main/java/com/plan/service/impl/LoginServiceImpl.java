package com.plan.service.impl;

import asserts.SystemAssert;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.plan.dao.UserDao;
import com.plan.dao.UserInfoDao;
import com.plan.pojo.dto.LoginDTO;
import com.plan.pojo.dto.MessageLoginDTO;
import com.plan.pojo.dto.RegisterDTO;
import com.plan.pojo.vo.LoginVO;
import com.plan.service.LoginService;
import constants.CommonConstant;
import constants.ConstantRedisKey;
import constants.RocketMqCommon;
import constants.user.UserConstant;
import enums.CommonCodeEnum;
import exceptions.SysException;
import message.MessageMes;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.assertj.core.util.Strings;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.user.User;
import pojo.user.UserInfo;
import response.RespResult;
import response.Result;
import utils.JwtUtil;
import utils.MD5Utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static constants.CommonConstant.TOKEN_EXPIRE_TIME;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Resource
    private UserDao userDao;

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public Result<String> getCaptcha(HttpServletResponse response) throws IOException {
        //获取生成的二维码
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 5);
        String code = captcha.getCode();

        //生成uuid
        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(ConstantRedisKey.USER_LOGIN_AND_REGISTER_CAPTCHA + uuid
                , code, ConstantRedisKey.LOGIN_CAPTCHA_EXPIRE_TIME, TimeUnit.SECONDS);

        // 将验证码图片的二进制数据写入【响应体 response 】
        response.setContentType("text/html;charset=utf-8");
        response.setContentType("image/png");
        captcha.write(response.getOutputStream());
        return RespResult.success(uuid);
    }

    @Override
    @Transactional(rollbackFor = SysException.class)
    public Result register(RegisterDTO registerDTO) {
        //校验验证码
        String captcha = stringRedisTemplate.opsForValue().get(ConstantRedisKey.USER_LOGIN_AND_REGISTER_CAPTCHA
               + registerDTO.getUuid());
        if (Strings.isNullOrEmpty(captcha) || !registerDTO.getCode().equals(captcha)){
            throw new SysException(CommonCodeEnum.CAPTCHA_INVALID);
        }

        //用户名是否已存在
        if (!Objects.isNull(userDao.getUserByUsername(registerDTO.getUsername()))) {
            throw new SysException(CommonCodeEnum.USERNAME_EXISTED);
        }

        //用户基本信息入库,同步用户信息;
        User user = defaultUser(registerDTO.getUsername(), registerDTO.getPassword());
        if (userDao.save(user)){
            throw new SysException(CommonCodeEnum.SYSTEM_ERROR_FAIL);
        }
        UserInfo userInfo = defaultUserInfo(user.getUserId());
        if (userDao.saveUserInfo(userInfo)){
            throw new SysException(CommonCodeEnum.SYSTEM_ERROR_FAIL);
        }
        return RespResult.success();
    }

    @Override
    public Result checkUserName(String username) {
        User user = userDao.getUserByUsername(username);
        return Objects.isNull(user) ? RespResult.fail(CommonCodeEnum.USERNAME_EXISTED)
                : RespResult.success();
    }

    @Override
    public Result<LoginVO> login(LoginDTO loginDTO) {
        //校验
        String sysCode = stringRedisTemplate.opsForValue().get(ConstantRedisKey.USER_LOGIN_AND_REGISTER_CAPTCHA
                + loginDTO.getCode());
        if (Strings.isNullOrEmpty(sysCode) || !loginDTO.getCode().equals(sysCode)){
            return RespResult.fail(CommonCodeEnum.CAPTCHA_INVALID);
        }
        User user = userDao.getUserByUsername(loginDTO.getUsername());
        if (Objects.isNull(user) ||
                user.getPassword().equals(MD5Utils.md5(loginDTO.getPassword() + user.getSalt()))){
            return RespResult.fail(CommonCodeEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        //生成jwt
        String userId = Base64.encode(user.getUserId());
        String token = JwtUtil.createJWT(null, userId, TOKEN_EXPIRE_TIME);
        UserInfo userInfo = userInfoDao.getByUserId(userId);

        //封装返回结果
        LoginVO loginVO = new LoginVO();
        loginVO.setAvatar(userInfo.getAvatar());
        loginVO.setNickname(userInfo.getNickName());
        loginVO.setToken(token);
        return RespResult.success(loginVO);
    }

    @Override
    public Result<LoginVO> messageLogin(MessageLoginDTO messageLoginDTO) {
        //手机号是否存在
        User user = userDao.getUserByPhone(messageLoginDTO.getPhone());
        SystemAssert.isNull(user, CommonCodeEnum.USER_PHONE_NOT_FIND);

        //校验验证码
        String captcha = stringRedisTemplate.opsForValue().get(ConstantRedisKey.USER_MESSAGE_LOGIN_CODE
                + messageLoginDTO.getPhone());
        if (Strings.isNullOrEmpty(captcha) || !messageLoginDTO.getCaptcha().equals(captcha)) {
            return RespResult.fail(CommonCodeEnum.CAPTCHA_INVALID);
        }

        //生成jwt
        UserInfo userInfo = userInfoDao.getByUserId(user.getUserId());
        String userId = Base64.encode(user.getUserId());
        String token = JwtUtil.createJWT(null, userId, TOKEN_EXPIRE_TIME);

        //封装返回结果
        LoginVO loginVO = new LoginVO();
        loginVO.setAvatar(userInfo.getAvatar());
        loginVO.setNickname(userInfo.getNickName());
        loginVO.setToken(token);
        return null;
    }

    @Override
    public Result sendMessage(String phone) {
        //手机格式校验
        if (! Validator.isMobile(phone)) {
            return RespResult.fail(CommonCodeEnum.PHONE_FORMAT_ERROR);
        }

        //手机号是否存在
        User user = userDao.getUserByPhone(phone);
        SystemAssert.isNull(user, CommonCodeEnum.USER_PHONE_NOT_FIND);

        //获取随机6位验证码
        int captcha = RandomUtil.randomInt(100000, 999999);
        stringRedisTemplate.opsForValue().set(ConstantRedisKey.USER_MESSAGE_LOGIN_CODE + phone, String.valueOf(captcha),
                ConstantRedisKey.LOGIN_CAPTCHA_EXPIRE_TIME, TimeUnit.SECONDS);

        //发送消息
        MessageMes messageMes = new MessageMes();
        messageMes.setCaptcha(captcha);
        messageMes.setPhone(phone);
        String message = JSONObject.toJSONString(messageMes);
        rocketMQTemplate.convertAndSend(RocketMqCommon.USER_MESSAGE_LOGIN_TOPIC, message);
        return RespResult.success();
    }

    public User defaultUser(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setDelFlag(CommonConstant.EXIST);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdate_time(LocalDateTime.now());

        //处理密码 手机号邮箱进去在绑定,现在可以是空的
        String salt = UUID.randomUUID().toString().replace("-", "").substring(0, 4);
        user.setSalt(salt);
        user.setPassword(MD5Utils.md5(password + salt));
        return user;
    }

    public UserInfo defaultUserInfo(String userId){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setUserType(UserConstant.USER_TYPE_COMMON_USER);
        userInfo.setAge(0);
        userInfo.setDelFlag(CommonConstant.EXIST);
        userInfo.setNickName(UUID.randomUUID().toString().substring(0,6));
        userInfo.setCreate_time(LocalDateTime.now());
        userInfo.setUpdate_time(LocalDateTime.now());
        return userInfo;
    }
}
