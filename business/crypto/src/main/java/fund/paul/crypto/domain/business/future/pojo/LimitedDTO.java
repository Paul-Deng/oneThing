package fund.paul.crypto.domain.business.future.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 被限流的具体承载对象
 *
 * @author paul
 * @date 2025/1/7 12:55
 */
@Getter
@AllArgsConstructor
public class LimitedDTO {
    /**
     * 限制的启动时间
     */
    private long startTime;

    /**
     * 出否出现限制
     */
    private boolean limit;

    /**
     * 限制的具体时长
     */
    private long timeOut;

    public boolean isLimit() {
        if (limit && System.currentTimeMillis() - startTime <= timeOut) {
            return true;
        }
        if (System.currentTimeMillis() - startTime > timeOut) {
            limit = false;
            startTime = 0L;
        }
        return false;
    }

    public void trans2Limit() {
        this.limit = true;
        this.startTime = System.currentTimeMillis();
    }
}
