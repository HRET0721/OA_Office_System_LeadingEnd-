package org.hret.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Author:HRET Milky Way
 * Date:2023/11/30
 * version:1.0
 * @author HRET
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class JsonResult implements Serializable {

    private static final long serialVersionUID = -5486653975868600354L;

    private Integer code;
    private String msg;
    private Object data;

    public static JsonResult error(String msg) {
        return new JsonResult(500, msg, null);
    }

    public static JsonResult error(String msg, Object data) {
        return new JsonResult(500, msg, data);
    }

    public static JsonResult ok(String msg) {
        return new JsonResult(200, msg, null);
    }

    public static JsonResult ok(String msg, Object data) {
        return new JsonResult(200, msg, data);
    }

    public static JsonResult build(Integer code, String msg) {
        return new JsonResult(code, msg, null);
    }

}
