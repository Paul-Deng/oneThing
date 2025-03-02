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
    PARAMS_ERROR(400, "request.params.error"),

    REQ_TOO_MANY(429, "request.rpc.tooMany"),

    SERVICE_EXCEPTION(500, "request.service.exception"),


    USER_NAME_OR_PASSWORD_ERROR(40000, "auth.usernameOrPassword.error"),

    USER_NAME_EXISTED(40001, "auth.username.existed"),

    CONCURRENT_EXCEPTION(41001, "concurrent.operation.error"),

    CONCURRENT_LOCK_NULL(41002, "concurrent.lock.isnull"),

    CONCURRENT_LOCK_KEY_NULL(41003, "concurrent.lockKey.isnull"),

    CONCURRENT_LOCK_TIME_OUT(41004, "concurrent.lock.timeout"),

    CONCURRENT_LOCK_FAILED(41005, "concurrent.lock.failed"),

    WSS_CONNECTION_FAILED(50000, "wss.connection.failed"),
    ;

    private Integer code;

    private String msg;
}
