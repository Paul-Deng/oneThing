package fund.paul.crypto.domain.pojo.strategy;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import fund.paul.common.exception.BusinessException;
import fund.paul.common.exception.ExceptionEnum;
import fund.paul.common.utils.IdGenerator;
import fund.paul.common.utils.OptUtils;
import fund.paul.crypto.domain.pojo.order.Order;
import fund.paul.crypto.domain.pojo.order.OrderDTO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
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
        public List<OrderDTO> execute(Map<String, Object> params) {
            if (MapUtil.isEmpty(params)) {
                LOGGER.error("params is empty");
                throw new BusinessException(ExceptionEnum.PARAMS_ERROR);
            }

            BigDecimal maxEntryPrice = MapUtil.get(params, Order.Col.MAX_ENTRY_PRICE, BigDecimal.class);
            BigDecimal minEntryPrice = MapUtil.get(params, Order.Col.MIN_ENTRY_PRICE, BigDecimal.class);
            if (ObjectUtils.isEmpty(maxEntryPrice) && ObjectUtils.isEmpty(minEntryPrice)) {
                // 没有入场价格，无法进行均分。
                LOGGER.error("no entry price. can't set entry price nad count");
                throw new BusinessException(ExceptionEnum.PARAMS_ERROR);
            }
            BigDecimal totalAmount = MapUtil.get(params, Order.Col.TOTAL_PURCHASE_AMOUNT, BigDecimal.class);
            Long orderId = MapUtil.getLong(params, Order.Col.ORDER_ID);
            // 如果其中一个为空，则选中其中一个价格直接置为所有的入场价。
            if (ObjectUtils.isEmpty(maxEntryPrice) || ObjectUtils.isEmpty(minEntryPrice)) {
                BigDecimal entryPrice = OptUtils.orElse(maxEntryPrice, minEntryPrice);
                OrderDTO orderDTO = OrderDTO.builder()
                        .count(NumberUtil.div(totalAmount, entryPrice, Order.Format.MAX_SCALE, RoundingMode.DOWN))
                        .status(Order.Status.WAITING)
                        .price(entryPrice)
                        .parentId(orderId)
                        .build();
                orderDTO.setId(IdGenerator.getId());
                return Collections.singletonList(orderDTO);
            }
            Integer splitCount = MapUtil.getInt(params, Order.Col.SPLIT_COUNT);
            // 差值
            BigDecimal sub = maxEntryPrice.subtract(minEntryPrice);
            // 每一个差值需要设置的数据
            BigDecimal div = NumberUtil.div(sub, splitCount, Order.Format.MAX_SCALE, RoundingMode.DOWN);
            List<OrderDTO> orderDTOList = new ArrayList<>(splitCount);
            // 每个差值需要购买的量
            BigDecimal count = NumberUtil.div(div, splitCount, Order.Format.MAX_SCALE, RoundingMode.DOWN);
            for (int i = 0; i < splitCount; i++) {
                OrderDTO orderDTO = OrderDTO.builder()
                        .count(count)
                        .status(Order.Status.WAITING)
                        .price(minEntryPrice.add(div.multiply(BigDecimal.valueOf(i))))
                        .parentId(orderId)
                        .build();
                orderDTO.setId(IdGenerator.getId());
                orderDTOList.add(orderDTO);
            }
            return orderDTOList;
        }
    }
    ;
}
