package pojo.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserInfo implements Serializable {
    @TableId
    private String userInfoId;

    private String userId;

    private String nickName;

    private Integer age;

    private Integer userType;

    private String avatar;

    private Integer sex;

    @TableLogic
    private Integer delFlag;

    private LocalDateTime create_time;

    private LocalDateTime update_time;
}
