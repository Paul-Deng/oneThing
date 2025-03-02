package fund.paul.common.basic;

import fund.paul.common.constant.CodeEnum;
import fund.paul.common.exception.ExceptionEnum;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应类
 *
 * @author paul
 * @date 2023/5/10 12:43
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private T data;
    private Integer code;
    private String msg;

    public static <T> Result<T> succeed(String msg) {
        return of(null, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed() {
        return of(null, CodeEnum.SUCCESS.getCode(), null);
    }

    public static <T> Result<T> succeed(T model, String msg) {
        return of(model, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T model) {
        return of(model, CodeEnum.SUCCESS.getCode(), "");
    }

    public static <T> Result<T> of(T data, Integer code, String msg) {
        return Result.<T>builder().code(code).msg(msg).data(data).build();
    }

    public static <T> Result<T> failed(String msg) {
        return of(null, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failed(ExceptionEnum exceptionEnum) {
        return of(null, exceptionEnum.getCode(), exceptionEnum.getMsg());
    }

    public static <T> Result<T> failed(T model, String msg) {
        return of(model, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<PageResult<T>> succeedByPage(long total, List<T> data) {
        PageResult<T> pageResult = PageResult.<T>builder().total(total).data(data).build();
        return of(pageResult, CodeEnum.SUCCESS.getCode(), "");
    }
}
