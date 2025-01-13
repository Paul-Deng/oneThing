package fund.paul.crypto.domain.business.future.service.impl;

import fund.paul.common.basic.Result;
import fund.paul.common.service.impl.SuperServiceImpl;
import fund.paul.crypto.domain.business.future.pojo.FutureAccountPO;
import fund.paul.crypto.domain.business.future.service.IUFutureAccountService;
import fund.paul.crypto.domain.config.EnvConfigManager;
import fund.paul.crypto.domain.pojo.ExchangerType;
import fund.paul.crypto.domain.pojo.ProductType;
import fund.paul.crypto.domain.pojo.ProtocolType;
import fund.paul.crypto.manufacture.mapper.FutureAccountMapper;
import fund.paul.websocket.application.pojo.WssCallback;
import fund.paul.websocket.domain.pojo.OkWssConnection;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账户的实现类
 *
 * @author paul
 * @date 2025/1/10 11:32
 */
@Service
public class BNUFutureAccountServiceImpl extends SuperServiceImpl<FutureAccountMapper, FutureAccountPO> implements IUFutureAccountService {
    @Autowired
    EnvConfigManager envConfigManager;

    private OkWssConnection SESSION;

    @PostConstruct
    public void init() {
        initWebsocket();
        initLimit(ExchangerType.BINANCE);
    }

    @Override
    public String getWebSocketURL() {
        return envConfigManager.getWebsocketUrl(ProductType.FUTURE_ACCOUNT, ExchangerType.BINANCE, ProtocolType.WSS);
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
    public WssCallback getOnFailureCallback() {
        return OkWssConnection.NOOP_CALL_BACK;
    }

    @Override
    public Result sendMsgByWss(Object params) {
        return null;
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
        return "";
    }
}
