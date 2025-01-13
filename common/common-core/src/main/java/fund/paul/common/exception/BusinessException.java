package fund.paul.common.exception;

import java.io.Serial;

/**
 * 基础业务异常
 *
 * @author paul
 * @date 2023/5/12 13:16
 */
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 516036989851548006L;

    private Integer code;

    private String msg;

    public BusinessException(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
    }
}
