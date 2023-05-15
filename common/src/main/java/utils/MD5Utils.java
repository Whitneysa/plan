package utils;
import org.springframework.util.DigestUtils;


public class MD5Utils {
    public static String md5(String password){
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
