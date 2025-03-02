//package fund.paul.crypto.domain.pojo.strategy;
//
//import cn.hutool.core.collection.CollUtil;
//import cn.hutool.core.map.MapUtil;
//import fund.paul.cryptoapi.pojo.Order;
//import fund.paul.cryptoapi.pojo.OrderDTO;
//import fund.paul.crypto.domain.utils.OrderUtils;
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Map;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
///**
// * @author paul
// * @date 2025/1/14 22:49
// */
//class ExitStrategyTest {
//
//    private BigDecimal COUNT = OrderUtils.standardize(new BigDecimal(10000));
//
//    @Test
//    public void test_ExitStrategy_profit() {
////        OrderDTO orderDTO = getOrderDTO();
//        Map<String, Object> params = MapUtil.newHashMap();
//        params.put(Order.Col.CUR_PRICE, OrderUtils.standardize(new BigDecimal(106000)));
//        List<OrderDTO> orderDTOList = ExitStrategy.PREVIOUS_EXIT.execute(orderDTO, params);
//        Assertions.assertTrue(CollUtil.isNotEmpty(orderDTOList));
//        Assertions.assertEquals(1, orderDTOList.size());
//        Assertions.assertEquals(Order.Status.WAITING, orderDTOList.getFirst().getStatus());
//        Assertions.assertEquals(OrderUtils.standardize(105000), orderDTOList.getFirst().getAvgPrice());
//        Assertions.assertEquals(COUNT, orderDTOList.getFirst().getCount());
//    }
//
//    @Test
//    public void test_ExitStrategy_sl() {
//        OrderDTO orderDTO = getOrderDTO();
//        Map<String, Object> params = MapUtil.newHashMap();
//        params.put(Order.Col.CUR_PRICE, OrderUtils.standardize(new BigDecimal(90000)));
//        List<OrderDTO> orderDTOList = ExitStrategy.PREVIOUS_EXIT.execute(orderDTO, params);
//        Assertions.assertTrue(CollUtil.isNotEmpty(orderDTOList));
//        Assertions.assertEquals(1, orderDTOList.size());
//        Assertions.assertEquals(Order.Status.WAITING, orderDTOList.getFirst().getStatus());
//        Assertions.assertEquals(OrderUtils.standardize(90000L), orderDTOList.getFirst().getAvgPrice());
//        Assertions.assertEquals(COUNT, orderDTOList.getFirst().getCount());
//    }
//
//    @Test
//    public void test_ExitStrategy_entryPrice() {
//        OrderDTO orderDTO = getOrderDTO();
//        Map<String, Object> params = MapUtil.newHashMap();
//        params.put(Order.Col.CUR_PRICE, OrderUtils.standardize(95000));
//        BigDecimal standardize = OrderUtils.standardize(93000);
//        orderDTO.setAvgPrice(OrderUtils.standardize(standardize));
//        List<OrderDTO> orderDTOList = ExitStrategy.PREVIOUS_EXIT.execute(orderDTO, params);
//        Assertions.assertTrue(CollUtil.isNotEmpty(orderDTOList));
//        Assertions.assertEquals(1, orderDTOList.size());
//        Assertions.assertEquals(Order.Status.WAITING, orderDTOList.getFirst().getStatus());
//        Assertions.assertEquals(standardize, orderDTOList.getFirst().getAvgPrice());
//        Assertions.assertEquals(COUNT, orderDTOList.getFirst().getCount());
//    }
//
////    public OrderDTO getOrderDTO() {
////        return OrderDTO.builder()
////                .status(Order.Status.FILLED)
////                .slPrice(OrderUtils.standardize(new BigDecimal("90000")))
////                .minEntryPrice(OrderUtils.standardize(new BigDecimal("92000")))
////                .maxEntryPrice(OrderUtils.standardize(new BigDecimal("97000")))
////                .avgPrice(OrderUtils.standardize(new BigDecimal("95000")))
////                .tpPriceList(CollUtil.newLinkedList(
////                        OrderUtils.standardize(new BigDecimal("100000")),
////                        OrderUtils.standardize(new BigDecimal("105000")),
////                        OrderUtils.standardize(new BigDecimal("110000")),
////                        OrderUtils.standardize(new BigDecimal("115000"))
////                ))
////                .count(COUNT)
////                .build();
////    }
//}