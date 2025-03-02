package fund.paul.common.exception;

import java.io.Serial;

/**
 * 分布式锁异常
 *
 * @author paul
 * @date 2023/5/25 23:37
 */
public class LockException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 6610083281801529147L;

    public LockException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
