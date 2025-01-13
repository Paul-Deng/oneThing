package fund.paul.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author paul
 * @date 2025/1/7 12:45
 */

@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    REQ_TOO_MANY(429, "request.rpc.tooMany"),

    WSS_CONNECTION_FAILED(50000, "wss.connection.failed"),

    PARAMS_ERROR(400, "request.params.error"),
    ;

    private Integer code;

    private String msg;
}
