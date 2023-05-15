package asserts;
import enums.CommonCodeEnum;
import org.springframework.lang.Nullable;

public abstract class SystemAssert {

    public static void isNull(@Nullable Object object, CommonCodeEnum commonCodeEnum) {
        if (object != null) {
            throw new IllegalArgumentException(commonCodeEnum.getMessage());
        }
    }
}
