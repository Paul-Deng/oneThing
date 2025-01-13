package fund.paul.crypto.domain.business.future.pojo.binance;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author paul
 * @date 2024/12/15 12:11
 */
@AllArgsConstructor
@NoArgsConstructor
public class BNRateLimitDTO {
    // 限制类型
    private String rateLimitType;

    private String interval;

    private int intervalNum;

    private int limit;

    private int count;
}
