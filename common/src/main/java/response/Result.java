package response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
//链式掉用
@Accessors(chain = true)
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    private T t;
}
