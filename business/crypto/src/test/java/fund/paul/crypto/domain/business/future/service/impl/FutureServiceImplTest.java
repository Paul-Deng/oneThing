package fund.paul.crypto.domain.business.future.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * 测试类
 *
 * @author paul
 * @date 2025/1/16 23:53
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class FutureServiceImplTest {

    @Autowired
    private FutureServiceImpl futureService;

//    @Test
//    void insert_and_query() {
//        OrderDTO orderDTO = OrderDTO.builder()
//                .coinKey("BTCUSDT")
//                .avgPrice(OrderUtils.standardize(50L))
//                .totalAmount(OrderUtils.standardize(500L))
//                .status(Order.Status.WAITING)
//                .build();
//        orderDTO.setId(IdGenerator.getId());
//        FuturePO futurePO = OrderStructConvert.INSTANCE.OrderDTO2FuturePO(orderDTO);
//        boolean save = futureService.save(futurePO);
//        Assertions.assertTrue(save);
//        List<FuturePO> list = futureService.list();
//        Assertions.assertNotNull(list);
//        Assertions.assertEquals(1, list.size());
//        Assertions.assertEquals(1, futureService.count());
//        FuturePO res = list.getFirst();
//        Assertions.assertNotNull(res);
//        Assertions.assertEquals(orderDTO.getId(), res.getId());
//        Assertions.assertEquals(orderDTO.getCoinKey(), res.getCoinKey());
//        Assertions.assertEquals(orderDTO.getTotalAmount(), res.getTotalAmount());
//        Assertions.assertEquals(orderDTO.getAvgPrice(), res.getAvgPrice());
//        Assertions.assertNotNull(res.getCreatedTime());
//        Assertions.assertNotNull(res.getUpdatedTime());
//        Assertions.assertNull(res.getCreatedBy());
//        Assertions.assertNull(res.getUpdatedBy());
//    }
}