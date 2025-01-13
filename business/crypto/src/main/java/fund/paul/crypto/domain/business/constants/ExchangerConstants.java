package fund.paul.crypto.domain.business.constants;

/**
 * 交易所的常量
 *
 * @author paul
 * @date 2024/12/14 19:21
 */
public interface ExchangerConstants {
    interface Binance {
        // Websocket server will send a ping frame every 3 minutes.
        // If the websocket server does not receive a pong frame back from the connection within a 10 minute period, the connection will be disconnected.
        // When you receive a ping, you must send a pong with a copy of ping's payload as soon as possible.
        // https://developers.binance.com/docs/zh-CN/binance-spot-api-docs/testnet/web-socket-api/public-websocket-api-for-binance-spot-testnet
        String SPOT_TEST_WEBSOCKET_API_URL = "wss://testnet.binance.vision/ws-api/v3:443";

        String SPOT_WEBSOCKET_API_URL = "wss://ws-api.binance.com:443/ws-api/v3";

        String U_TEST_FUTURE_WEBSOCKET_API_URL = "wss://testnet.binancefuture.com/ws-fapi/v1";

        String U_FUTURE_WEBSOCKET_API_URL = "wss://ws-fapi.binance.com/ws-fapi/v1'";

        String U_FUTURE_WEBSOCKET_URL = "wss://fstream.binance.com/stream";

        String U_TEST_FUTURE_WEBSOCKET_URL = "wss://fstream.binancefuture.com";

        /**
         * 用于订阅账户数据的 listenKey 从创建时刻起有效期为60分钟
         */
        String U_ACCOUNT_FUTURE_WEBSOCKET_URL = "wss://fstream.binance.com";

        String U_ACCOUNT_FUTURE_REST_URL = "https://fapi.binance.com";
    }
}
