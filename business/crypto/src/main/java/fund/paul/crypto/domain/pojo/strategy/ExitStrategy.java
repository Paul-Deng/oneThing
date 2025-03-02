package fund.paul.crypto.domain.pojo.strategy;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import fund.paul.common.exception.BusinessException;
import fund.paul.common.exception.ExceptionEnum;
import fund.paul.cryptoapi.pojo.Order;
import fund.paul.cryptoapi.pojo.OrderDTO;
import fund.paul.crypto.domain.utils.OrderUtils;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.util.ObjectUtils;

/**
 * 离场策略
 *
 * @author paul
 * @date 2025/1/14 12:45
 */
public enum ExitStrategy implements TradeStrategy {
    PREVIOUS_EXIT() {
        @Override
        public List<OrderDTO> execute(OrderDTO curOrder, Map<String, Object> params) {
            if (ObjectUtils.isEmpty(curOrder)) {
                LOGGER.error("params is empty");
                throw new BusinessException(ExceptionEnum.PARAMS_ERROR);
            }
            if (!OrderUtils.isRunning(curOrder)) {
                LOGGER.error("order is not running");
                throw new BusinessException(ExceptionEnum.PARAMS_ERROR);
            }
            BigDecimal curPrice = MapUtil.get(params, Order.Col.CUR_PRICE, BigDecimal.class);
            LinkedList<BigDecimal> priceOrderList = new LinkedList<>();
            priceOrderList.add(curOrder.getSlPrice());
            priceOrderList.add(curOrder.getAvgPrice());
            priceOrderList.addAll(curOrder.getTpPriceList());
//            OrderDTO orderDTO = OrderDTO.builder().parentId(curOrder.getId())
//                    .avgPrice(priceOrderList.getFirst())
//                    .count(curOrder.getCount())
//                    .status(Order.Status.WAITING)
//                    .build();
//            if (NumberUtil.isLessOrEqual(curPrice, curOrder.getSlPrice())) {
//                orderDTO.setSlPrice(OrderUtils.standardize(0L));
//                return List.of(orderDTO);
//            }
            for (int i = 0; i < priceOrderList.size() - 1; i++) {
                if (ObjectUtils.isEmpty(priceOrderList.get(i))) {
                    continue;
                }
                // 设置为上一个赢利点的止损
                if (NumberUtil.isGreater(curPrice, priceOrderList.get(i))
                        && NumberUtil.isLessOrEqual(curPrice, priceOrderList.get(i + 1))) {
//                    orderDTO.setAvgPrice(priceOrderList.get(i));
//                    return List.of(orderDTO);
                }
                // 当数值无限大时，设置为上一次的赢利点。
                if (NumberUtil.isGreater(curPrice, priceOrderList.get(i))) {
//                    orderDTO.setAvgPrice(priceOrderList.get(i));
                }
            }
            if (NumberUtil.isGreater(curPrice, priceOrderList.getLast())) {
//                orderDTO.setAvgPrice(priceOrderList.getLast());
            }
//            return List.of(orderDTO);
            return null;
        }
    }
}
