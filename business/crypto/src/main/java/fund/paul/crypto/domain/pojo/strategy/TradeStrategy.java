package fund.paul.crypto.domain.pojo.strategy;

import fund.paul.crypto.domain.business.future.service.IWssConnService;
import fund.paul.cryptoapi.pojo.OrderDTO;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author paul
 * @date 2025/1/10 13:59
 */
public interface TradeStrategy {
    Logger LOGGER = LoggerFactory.getLogger(IWssConnService.class);

    /**
     * 策略执行所需要的参数
     *
     * @param params 参数
     */
    public List<OrderDTO> execute(OrderDTO orderDTO, Map<String, Object> params);
}
