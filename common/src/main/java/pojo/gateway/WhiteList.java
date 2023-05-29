package pojo.gateway;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class WhiteList implements Serializable {

    private Integer id;

    private String routeType;

    private String path;

    private String comment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate crateDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateDate;
}
