package pojo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {

     @TableId(value = "user_id",type = IdType.ASSIGN_ID)
     private String userId;

     private String username;

     private String password;

     private String salt;

     private String phone;

     private String email;

     @TableLogic
     private Integer delFlag;

     private Integer status;

     private LocalDateTime createTime;

     private LocalDateTime update_time;
}
