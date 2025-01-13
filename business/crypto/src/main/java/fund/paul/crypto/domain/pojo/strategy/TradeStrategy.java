package fund.paul.crypto.domain.pojo.strategy;

import fund.paul.crypto.domain.business.future.service.IFutureService;
import fund.paul.crypto.domain.pojo.order.OrderDTO;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author paul
 * @date 2025/1/10 13:59
 */
public interface TradeStrategy {
    Logger LOGGER = LoggerFactory.getLogger(IFutureService.class);

    /**
     * 策略执行所需要的参数
     *
     * @param params 参数
     */
    public List<OrderDTO> execute(Map<String, Object> params);
}
