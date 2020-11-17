package com.kangec.vcms.entity;

import com.kangec.vcms.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author kangec 10/25/2020 10:45 PM
 * @Email ardien@126.com
 **/

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponse {
    private Integer code;
    private String message;
    private Object data;

    public static ResultResponse ok(Object data) {
        return new ResultResponse(Constants.STATUS_OK,Constants.MESSAGE_OK,data);
    }

    public static ResultResponse fail(String data) {
        return new ResultResponse(Constants.STATUS_FAIL,Constants.MESSAGE_FAIL,data);
    }

    public static ResultResponse login(Integer status ,String message, Object data) {
        return new ResultResponse(status, message, data);
    }
}
