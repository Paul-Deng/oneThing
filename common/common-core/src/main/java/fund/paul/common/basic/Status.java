package fund.paul.common.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    SCCUESS(0),
    ;
    private final int code;
}