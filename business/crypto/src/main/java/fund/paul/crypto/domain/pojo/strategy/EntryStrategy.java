package fund.paul.crypto.domain.pojo.strategy;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import fund.paul.common.exception.BusinessException;
import fund.paul.common.exception.ExceptionEnum;
import fund.paul.common.utils.OptUtils;
import fund.paul.cryptoapi.pojo.Order;
import fund.paul.cryptoapi.pojo.OrderDTO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.util.ObjectUtils;

/**
 * 入场策略的枚举
 *
 * @author paul
 * @date 2025/1/11 21:51
 */
public enum EntryStrategy implements TradeStrategy {
    /**
     * 均分入场价
     */
    AVG_ENTRY(){
        @Override
        public List<OrderDTO> execute(OrderDTO curOrder, Map<String, Object> params) {
            if (ObjectUtils.isEmpty(curOrder)) {
                LOGGER.error("params is empty");
                throw new BusinessException(ExceptionEnum.PARAMS_ERROR);
            }
            BigDecimal maxEntryPrice = curOrder.getMaxEntryPrice();
            BigDecimal minEntryPrice = curOrder.getMinEntryPrice();
            if (ObjectUtils.isEmpty(maxEntryPrice) && ObjectUtils.isEmpty(minEntryPrice)) {
                // 没有入场价格，无法进行均分。
                LOGGER.error("no entry price. can't set entry price nad count");
                throw new BusinessException(ExceptionEnum.PARAMS_ERROR);
            }
            BigDecimal totalAmount = curOrder.getTotalAmount();
            if (ObjectUtils.isEmpty(totalAmount)) {
                LOGGER.error("no total amount. can't set entry price nad count.");
                throw new BusinessException(ExceptionEnum.PARAMS_ERROR);
            }
            Long orderId = curOrder.getId();
            // 如果其中一个为空，则选中其中一个价格直接置为所有的入场价。
            if (ObjectUtils.isEmpty(maxEntryPrice) || ObjectUtils.isEmpty(minEntryPrice)) {
                BigDecimal entryPrice = OptUtils.orElse(maxEntryPrice, minEntryPrice);
//                OrderDTO orderDTO = OrderDTO.builder()
//                        .count(NumberUtil.div(totalAmount, entryPrice, Order.Format.MAX_SCALE, RoundingMode.DOWN))
//                        .status(Order.Status.WAITING)
//                        .avgPrice(entryPrice.setScale(Order.Format.MAX_SCALE, RoundingMode.DOWN))
//                        .parentId(orderId)
//                        .build();
//                orderDTO.setId(IdGenerator.getId());
//                return Collections.singletonList(orderDTO);
            }
            Integer splitCount = MapUtil.getInt(params, Order.Col.SPLIT_COUNT);
            if (splitCount == null || splitCount <= 1) {
//                BigDecimal curPrice = NumberUtil.div(NumberUtil.add(maxEntryPrice, minEntryPrice), 2);
//                OrderDTO orderDTO = OrderDTO.builder()
//                        .count(NumberUtil.div(totalAmount, curPrice, Order.Format.MAX_SCALE, RoundingMode.DOWN))
//                        .status(Order.Status.WAITING)
//                        .avgPrice(curPrice.setScale(Order.Format.MAX_SCALE, RoundingMode.DOWN))
//                        .parentId(orderId)
//                        .build();
//                orderDTO.setId(IdGenerator.getId());
//                return Collections.singletonList(orderDTO);
            }
            // 因为还包含了两边。
            int interval = splitCount - 1;
            // 差值
            BigDecimal sub = maxEntryPrice.subtract(minEntryPrice);
            // 每一个差值需要设置的数据
            BigDecimal div = NumberUtil.div(sub, interval, Order.Format.MAX_SCALE, RoundingMode.DOWN);
            // 每个差值需要购买的额度
            BigDecimal curAmount = NumberUtil.div(totalAmount, splitCount, Order.Format.MAX_SCALE, RoundingMode.DOWN);
            List<OrderDTO> orderDTOList = new ArrayList<>(splitCount);
            // 每个差值需要购买的量
            for (int i = 0; i < splitCount; i++) {
                BigDecimal curPrice = minEntryPrice.add(div.multiply(BigDecimal.valueOf(i)));
//                OrderDTO orderDTO = OrderDTO.builder()
//                        .count(NumberUtil.div(curAmount, curPrice, Order.Format.MAX_SCALE, RoundingMode.DOWN))
//                        .status(Order.Status.WAITING)
//                        .avgPrice(curPrice)
//                        .parentId(orderId)
//                        .build();
//                orderDTO.setId(IdGenerator.getId());
//                orderDTOList.add(orderDTO);
            }
            return orderDTOList;
        }
    }
    ;
}
