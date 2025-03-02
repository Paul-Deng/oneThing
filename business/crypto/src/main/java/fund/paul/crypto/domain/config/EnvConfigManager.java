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
            .put(constructKey(ExchangerType.BINANCE, ProductType.FUTURE_TEST, ProtocolType.WSS), ExchangerConstants.Binance.U_TEST_FUTURE_WEBSOCKET_URL)
            .put(constructKey(ExchangerType.BINANCE, ProductType.FUTURE_TEST, ProtocolType.WSS_API), ExchangerConstants.Binance.U_TEST_FUTURE_WEBSOCKET_API_URL)
            .put(constructKey(ExchangerType.BINANCE, ProductType.SPOT_TEST, ProtocolType.WSS_API), ExchangerConstants.Binance.SPOT_TEST_WEBSOCKET_API_URL)
            .put(constructKey(ExchangerType.BINANCE, ProductType.SPOT, ProtocolType.WSS_API), ExchangerConstants.Binance.SPOT_WEBSOCKET_API_URL)
            .put(constructKey(ExchangerType.BINANCE, ProductType.FUTURE, ProtocolType.WSS), ExchangerConstants.Binance.U_FUTURE_WEBSOCKET_URL)
            .put(constructKey(ExchangerType.BINANCE, ProductType.FUTURE, ProtocolType.WSS_API), ExchangerConstants.Binance.U_FUTURE_WEBSOCKET_API_URL)
            .build();

    public static final Map<String, String> API_KEY_MAP = MapBuilder.<String, String>create()
            .put(constructKey(ExchangerType.BINANCE, ProductType.FUTURE_TEST), ExchangerConstants.Binance.U_TEST_FUTURE_WEBSOCKET_URL)
            .put(constructKey(ExchangerType.BINANCE, ProductType.SPOT_TEST), ExchangerConstants.Binance.SPOT_TEST_WEBSOCKET_API_URL)
            .put(constructKey(ExchangerType.BINANCE, ProductType.SPOT), ExchangerConstants.Binance.SPOT_WEBSOCKET_API_URL)
            .put(constructKey(ExchangerType.BINANCE, ProductType.FUTURE), ExchangerConstants.Binance.U_FUTURE_WEBSOCKET_URL)
            .put(constructKey(ExchangerType.BINANCE, ProductType.FUTURE), "80D0YPTmLyaFUTYptsngrlAVHNz1HTxTAtK8bWjcTi6bYSu81uRn6y3c7lJUYphY")
            .build();

    public String getWebsocketUrl(ExchangerType exchangerType, ProductType productType, ProtocolType protocolType) {
        if (ObjectUtil.isEmpty(productType) || ObjectUtil.isEmpty(exchangerType) || ObjectUtil.isEmpty(protocolType)) {
            return null;
        }
        return URL_MAP.get(constructKey(exchangerType, productType, protocolType));
    }

    private static String constructKey(ExchangerType exchangerType, ProductType productType,  ProtocolType protocolType) {
        return StrUtil.join(Constants.Characters.AT, exchangerType.name(), protocolType.name(), productType.name());
    }

    private static String constructKey(ExchangerType exchangerType, ProductType productType) {
        return StrUtil.join(Constants.Characters.AT, exchangerType.name(), productType.name());
    }

    public String getApiKey(ExchangerType exchangerType, ProductType productType) {
        if (ObjectUtil.isEmpty(productType) || ObjectUtil.isEmpty(exchangerType)) {
            return null;
        }
        return API_KEY_MAP.get(constructKey(exchangerType, productType));
    }
}
