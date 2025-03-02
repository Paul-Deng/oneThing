package fund.paul.crypto.domain.business.future.service.impl;

import fund.paul.common.basic.Result;
import fund.paul.crypto.domain.business.future.pojo.FutureDTO;
import fund.paul.crypto.domain.business.future.service.IWssConnService;
import fund.paul.crypto.domain.config.EnvConfigManager;
import fund.paul.crypto.domain.pojo.ExchangerType;
import fund.paul.crypto.domain.pojo.ProductType;
import fund.paul.crypto.domain.pojo.ProtocolType;
import fund.paul.websocket.application.pojo.WssCallback;
import fund.paul.websocket.domain.pojo.OkWssConnection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账户的实现类
 *
 * @author paul
 * @date 2025/1/10 11:32
 */
@Service
public class BNUWssConnAccountServiceImpl implements IWssConnService {
    @Autowired
    EnvConfigManager envConfigManager;

    private OkWssConnection SESSION;

    public void init() {
        initWebsocket();
        initLimit(ExchangerType.BINANCE);
    }

    @Override
    public String getWebSocketURL() {
        return envConfigManager.getWebsocketUrl(ExchangerType.BINANCE, ProductType.FUTURE_ACCOUNT, ProtocolType.WSS);
    }

    @Override
    public WssCallback getOnMessageCallback() {
        return OkWssConnection.NOOP_CALL_BACK;
    }

    @Override
    public WssCallback getOnClosingCallback() {
        return OkWssConnection.NOOP_CALL_BACK;
    }

    @Override
    public WssCallback getOnOpenCallback() {
        return OkWssConnection.NOOP_CALL_BACK;
    }

    @Override
    public Result<Object> sendMsg(FutureDTO futureDTO) {
        return null;
    }

    @Override
    public WssCallback getOnFailureCallback() {
        return OkWssConnection.NOOP_CALL_BACK;
    }

    @Override
    public Result sendMsgByWss(Object params) {
        return null;
    }

    @Override
    public String getValidSendMsg(Map<String, Object> params) {
        return "";
    }

    @Override
    public OkWssConnection getOkWssConnection() {
        return null;
    }

    @Override
    public void setOkWssConnection(OkWssConnection okWssConnection) {

    }

    @Override
    public String getApikey() {
        return envConfigManager.getApiKey(ExchangerType.BINANCE, ProductType.FUTURE_ACCOUNT);
    }

    @Override
    public ExchangerType getExchangerType() {
        return ExchangerType.BINANCE;
    }
}
