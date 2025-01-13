package fund.paul.crypto.domain.config;

import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import fund.paul.common.constant.Constants;
import fund.paul.crypto.domain.business.constants.ExchangerConstants;
import fund.paul.crypto.domain.pojo.ExchangerType;
import fund.paul.crypto.domain.pojo.ProductType;
import fund.paul.crypto.domain.pojo.ProtocolType;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * 环境变量设置
 *
 * @author paul
 * @date 2025/1/7 00:57
 */
@Component
public class EnvConfigManager {
    public static final Map<String, String> URL_MAP = MapBuilder.<String, String>create()
            .put(constructKey(ProductType.FUTURE_TEST, ExchangerType.BINANCE, ProtocolType.WSS), ExchangerConstants.Binance.U_TEST_FUTURE_WEBSOCKET_URL)
            .put(constructKey(ProductType.FUTURE_TEST, ExchangerType.BINANCE, ProtocolType.WSS_API), ExchangerConstants.Binance.U_TEST_FUTURE_WEBSOCKET_API_URL)
            .put(constructKey(ProductType.SPOT_TEST, ExchangerType.BINANCE, ProtocolType.WSS_API), ExchangerConstants.Binance.SPOT_TEST_WEBSOCKET_API_URL)
            .put(constructKey(ProductType.SPOT, ExchangerType.BINANCE, ProtocolType.WSS_API), ExchangerConstants.Binance.SPOT_WEBSOCKET_API_URL)
            .put(constructKey(ProductType.FUTURE, ExchangerType.BINANCE, ProtocolType.WSS), ExchangerConstants.Binance.U_FUTURE_WEBSOCKET_URL)
            .put(constructKey(ProductType.FUTURE, ExchangerType.BINANCE, ProtocolType.WSS_API), ExchangerConstants.Binance.U_FUTURE_WEBSOCKET_API_URL)
            .build();

    public String getWebsocketUrl(ProductType productType, ExchangerType exchangerType, ProtocolType protocolType) {
        if (ObjectUtil.isEmpty(productType) || ObjectUtil.isEmpty(exchangerType) || ObjectUtil.isEmpty(protocolType)) {
            return null;
        }
        return URL_MAP.get(constructKey(productType, exchangerType, protocolType));
    }

    private static String constructKey(ProductType productType, ExchangerType exchangerType, ProtocolType protocolType) {
        return StrUtil.join(Constants.Characters.AT, ExchangerType.BINANCE.name(), ProtocolType.WSS.name(), ProductType.FUTURE_TEST.name());
    }

}
