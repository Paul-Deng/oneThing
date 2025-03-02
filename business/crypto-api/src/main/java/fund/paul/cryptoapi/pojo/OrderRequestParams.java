package fund.paul.cryptoapi.pojo;

import fund.paul.common.basic.PageParamsDTO;
import java.util.Set;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @author paul
 * @date 2025/1/15 00:49
 */
@Data
@SuperBuilder
public class OrderRequestParams extends PageParamsDTO {
    private Set<Long> orderIdCol;

    private Set<String> coinKeyCol;
}
