package fund.paul.common.exception;

import java.io.Serial;

/**
 * 幂等性异常
 *
 * @author paul
 * @date 2023/5/12 13:13
 */
public class IdempotencyException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4983048971272880363L;

    public IdempotencyException(String message) {
        super(message);
    }
}
