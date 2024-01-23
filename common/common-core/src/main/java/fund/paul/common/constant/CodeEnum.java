package fund.paul.common.constant;

/**
 * 响应枚举类
 *
 * @author paul
 * @date 2023/5/10 23:40
 */
public enum CodeEnum {
    SUCCESS(0),
    ERROR(1);

    private Integer code;

    CodeEnum(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
