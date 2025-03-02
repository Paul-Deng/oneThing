package fund.paul.common.basic;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * @author paul
 * @date 2025/1/15 00:51
 */
@Data
@SuperBuilder
@AllArgsConstructor
public class PageParamsDTO {

    public Integer page = 0;

    public PageSize size = PageSize.S_10;

    public List<String> orderBy;

    @AllArgsConstructor
    @Getter
    public enum PageSize {
        S_10(10),
        S_20(20),
        S_50(50),
        S_100(100),
        ;

        private int size;
    }
}
