package fund.paul.crypto.domain.business.future.pojo.binance;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 币安的响应体
 *
 * @author paul
 * @date 2024/12/15 12:10
 */
@Getter
@AllArgsConstructor
public class BNRestRspDTO {
    private Integer code;

    private String msg;

    private List<BNRateLimitDTO> rateLimits;
}
