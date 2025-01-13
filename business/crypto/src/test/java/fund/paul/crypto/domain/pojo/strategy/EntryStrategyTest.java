package fund.paul.crypto.domain.pojo.strategy;

import fund.paul.common.exception.BusinessException;
import java.util.Collections;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author paul
 * @date 2025/1/14 00:56
 */
class EntryStrategyTest {

    @Test
    void test_avg_price_empty() {
        Assertions.assertThrowsExactly(BusinessException.class, () -> EntryStrategy.AVG_ENTRY.execute(getEmptyMap()));
    }

    Map<String, Object> getEmptyMap() {
        return Collections.emptyMap();
    }
}