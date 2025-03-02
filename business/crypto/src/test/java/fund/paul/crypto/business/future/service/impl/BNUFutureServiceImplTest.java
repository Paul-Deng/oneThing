package fund.paul.crypto.business.future.service.impl;

import fund.paul.crypto.Application;
import fund.paul.crypto.domain.business.future.pojo.binance.BNRequestDTO;
import fund.paul.crypto.domain.business.future.service.impl.BNUWssConnServiceImpl;
import fund.paul.crypto.domain.config.EnvConfigManager;
import fund.paul.crypto.domain.pojo.OperateType;
import fund.paul.crypto.domain.pojo.ResType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author paul
 * @date 2024/12/21 12:39
 */
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class BNUFutureServiceImplTest {
    @Autowired
    private BNUWssConnServiceImpl bnuFutureService;

    @Autowired
    private EnvConfigManager envConfigManager;

    @Test
    void getPrice() {
        long nowTime = System.currentTimeMillis();
//        Map<String, Object> params = MapBuilder.<String, Object>create()
//                .put("apiKey", "80D0YPTmLyaFUTYptsngrlAVHNz1HTxTAtK8bWjcTi6bYSu81uRn6y3c7lJUYphY")
//                .put("symbol", "BTCUSDT")
//                .put("timestamp", nowTime)
//                .build();
        List<String> params = new ArrayList<>();
        params.add("btcusdt@markPrice");
//        String signature = SignatureGenerator.getSignature(JSONUtil.toJsonStr(params), "80D0YPTmLyaFUTYptsngrlAVHNz1HTxTAtK8bWjcTi6bYSu81uRn6y3c7lJUYphY");
        BNRequestDTO bnRequestDTO = BNRequestDTO.builder()
                .method("SUBSCRIBE")
                .params(params)
                .id("1")
                .build();
        params = new ArrayList<>();
        params.add("ethusdt@markPrice");
        BNRequestDTO bnRequestDTO2 = BNRequestDTO.builder()
                .method("SUBSCRIBE")
                .params(params)
                .id("2")
                .build();
        while (true) {

        }
    }

    @Test
    void subscribeCoin() {
        String coin = "btcusdt";
        bnuFutureService.subscribeCoin(coin, ResType.MARKET_PRICE, OperateType.SUBSCRIBE);
        while (true) {

        }
    }

    @Test
    void getValidSendMsg() {
    }
}