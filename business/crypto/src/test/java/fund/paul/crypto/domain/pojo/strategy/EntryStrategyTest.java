//package fund.paul.crypto.domain.pojo.strategy;
//
//import cn.hutool.core.map.MapUtil;
//import cn.hutool.core.util.NumberUtil;
//import fund.paul.common.exception.BusinessException;
//import fund.paul.common.exception.ExceptionEnum;
//import fund.paul.cryptoapi.pojo.Order;
//import fund.paul.cryptoapi.pojo.OrderDTO;
//import fund.paul.crypto.domain.utils.OrderUtils;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
///**
// * @author paul
// * @date 2025/1/14 00:56
// */
//class EntryStrategyTest {
//
//    private static final int SPLIT_COUNT = 5;
//
//
//    @Test
//    void test_avg_price_empty() {
////        Assertions.assertThrowsExactly(BusinessException.class, () -> EntryStrategy.AVG_ENTRY.execute(OrderDTO.builder().build(), getEmptyMap()), ExceptionEnum.PARAMS_ERROR.getMsg());
//        Map<String, Object> map = MapUtil.newHashMap();
//        map.put("name", null);
//        Assertions.assertThrowsExactly(BusinessException.class, () -> EntryStrategy.AVG_ENTRY.execute(null, getEmptyMap()), ExceptionEnum.PARAMS_ERROR.getMsg());
//
//    }
//
//    @Test
//    void test_avg_price_justOnePrice() {
//        List<OrderDTO> minEntryPrice = EntryStrategy.AVG_ENTRY.execute(getOneEntryPriceOrderDTO(Order.Col.MIN_ENTRY_PRICE), getAllEntryPriceMap());
//        Assertions.assertNotNull(minEntryPrice);
//        Assertions.assertEquals(1, minEntryPrice.size());
//        Assertions.assertEquals(new BigDecimal(100000).setScale(Order.Format.MAX_SCALE, RoundingMode.DOWN), minEntryPrice.getFirst().getAvgPrice());
//        Assertions.assertEquals(new BigDecimal("0.1").setScale(Order.Format.MAX_SCALE, RoundingMode.DOWN), minEntryPrice.getFirst().getCount());
//        Assertions.assertEquals(Order.Status.WAITING, minEntryPrice.getFirst().getStatus());
//
//
//        List<OrderDTO> maxEntryPrice = EntryStrategy.AVG_ENTRY.execute(getOneEntryPriceOrderDTO(Order.Col.MAX_ENTRY_PRICE), getAllEntryPriceMap());
//        Assertions.assertNotNull(maxEntryPrice);
//        Assertions.assertEquals(1, maxEntryPrice.size());
//        Assertions.assertEquals(new BigDecimal(100000).setScale(Order.Format.MAX_SCALE, RoundingMode.DOWN), maxEntryPrice.getFirst().getAvgPrice());
//        Assertions.assertEquals(new BigDecimal("0.1").setScale(Order.Format.MAX_SCALE, RoundingMode.DOWN), maxEntryPrice.getFirst().getCount());
//        Assertions.assertEquals(Order.Status.WAITING, maxEntryPrice.getFirst().getStatus());
//    }
//
//    @Test
//    void test_avg_price_twoPrice() {
//        List<OrderDTO> res = EntryStrategy.AVG_ENTRY.execute(getAllEntryPriceOrderDTO(), getAllEntryPriceMap());
//        Assertions.assertNotNull(res);
//        Assertions.assertEquals(SPLIT_COUNT, res.size());
//        Assertions.assertEquals(new BigDecimal(95000).setScale(Order.Format.MAX_SCALE, RoundingMode.DOWN), res.get(0).getAvgPrice());
//        Assertions.assertEquals(new BigDecimal(96250).setScale(Order.Format.MAX_SCALE, RoundingMode.DOWN), res.get(1).getAvgPrice());
//        Assertions.assertEquals(new BigDecimal(97500).setScale(Order.Format.MAX_SCALE, RoundingMode.DOWN), res.get(2).getAvgPrice());
//        Assertions.assertEquals(new BigDecimal(98750).setScale(Order.Format.MAX_SCALE, RoundingMode.DOWN), res.get(3).getAvgPrice());
//        Assertions.assertEquals(new BigDecimal(100000).setScale(Order.Format.MAX_SCALE, RoundingMode.DOWN), res.get(4).getAvgPrice());
//
//        Assertions.assertEquals(NumberUtil.div(new BigDecimal(2000).setScale(Order.Format.MAX_SCALE, RoundingMode.DOWN),
//                new BigDecimal(95000).setScale(Order.Format.MAX_SCALE, RoundingMode.DOWN),
//                Order.Format.MAX_SCALE, RoundingMode.DOWN),
//            res.get(0).getCount());
//    }
//
//    Map<String, Object> getEmptyMap() {
//        return Collections.emptyMap();
//    }
//
//    public OrderDTO getAllEntryPriceOrderDTO() {
//        return OrderDTO.builder()
//                .minEntryPrice(OrderUtils.standardize(new BigDecimal(95000)))
//                .maxEntryPrice(OrderUtils.standardize(new BigDecimal(100000)))
//                .totalAmount(OrderUtils.standardize(new BigDecimal(10000)))
//                .build();
//    }
//
//    public OrderDTO getOneEntryPriceOrderDTO(String name) {
////        OrderDTO orderDTO = OrderDTO.builder()
////                .totalAmount(OrderUtils.standardize(new BigDecimal(10000)))
////                .build();
////        if (Order.Col.MAX_ENTRY_PRICE.equals(name)) {
////            orderDTO.setMaxEntryPrice(OrderUtils.standardize(new BigDecimal(100000)));
////        } else {
////            orderDTO.setMinEntryPrice(OrderUtils.standardize(new BigDecimal(100000)));
////        }
////        return orderDTO;
//        return null;
//    }
//
//    Map<String, Object> getAllEntryPriceMap() {
//        Map<String, Object> map = MapUtil.newHashMap();
//        map.put(Order.Col.SPLIT_COUNT, new BigDecimal(SPLIT_COUNT));
//        return map;
//    }
//}