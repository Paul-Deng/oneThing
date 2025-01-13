package fund.paul.crypto.domain.config;

import com.binance.connector.futures.client.impl.UMWebsocketClientImpl;
import org.springframework.stereotype.Service;

/**
 * @author paul
 * @date 2024/12/10 01:44
 */
@Service
public class BinanceExchangeInfoImpl implements IExchangeInfo {

    @Override
    public void connect() {
        UMWebsocketClientImpl umWebsocketClientImpl = new UMWebsocketClientImpl();

    }


}
