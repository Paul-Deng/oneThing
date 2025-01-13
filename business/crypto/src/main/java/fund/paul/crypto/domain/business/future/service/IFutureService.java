package fund.paul.crypto.domain.business.future.service;

import fund.paul.common.basic.Result;
import fund.paul.crypto.domain.business.future.pojo.LimitedDTO;
import fund.paul.crypto.domain.pojo.ExchangerType;
import fund.paul.websocket.application.pojo.WssCallback;
import fund.paul.websocket.domain.pojo.OkWssConnection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 合约的接口
 *
 * @author paul
 * @date 2024/12/12 13:28
 */
public interface IFutureService {
    Logger LOGGER = LoggerFactory.getLogger(IFutureService.class);

    Map<ExchangerType, LimitedDTO> LIMIT_MAP = new ConcurrentHashMap<>();

    public String getWebSocketURL();

    public WssCallback getOnMessageCallback();

    public WssCallback getOnClosingCallback();

    public WssCallback getOnOpenCallback();

    public WssCallback getOnFailureCallback();

    public Result sendMsgByWss(Object params);

    public OkWssConnection getOkWssConnection();

    public void setOkWssConnection(OkWssConnection okWssConnection);

    public String getApikey();

    default boolean checkLimit(ExchangerType exchangerType) {
        if (!LIMIT_MAP.containsKey(exchangerType)) {
            return true;
        }
        return LIMIT_MAP.get(exchangerType).isLimit();
    }

    default void initLimit(ExchangerType exchangerType) {
        synchronized (exchangerType) {
            if (!LIMIT_MAP.containsKey(exchangerType)) {
                return;
            }
            LIMIT_MAP.put(exchangerType, new LimitedDTO(0L,false, TimeUnit.MINUTES.toMillis(3)));
        }
    }

    default void trans2Limit(ExchangerType exchangerType) {
        synchronized (exchangerType) {
            if (!LIMIT_MAP.containsKey(exchangerType)) {
                LIMIT_MAP.get(exchangerType).trans2Limit();
                return;
            }
            initLimit(exchangerType);
        }
    }

    default void initWebsocket() {
        Request request  = new Request.Builder().tag(getClass()).url(getWebSocketURL()).build();
        OkWssConnection okWssConnection = new OkWssConnection(getOnOpenCallback(), getOnMessageCallback(), getOnClosingCallback(), getOnFailureCallback(), request);
        okWssConnection.connect();
        setOkWssConnection(okWssConnection);
    }
}
