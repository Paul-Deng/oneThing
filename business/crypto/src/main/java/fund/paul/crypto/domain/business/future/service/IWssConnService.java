package fund.paul.crypto.domain.business.future.service;

import fund.paul.common.basic.Result;
import fund.paul.common.exception.BusinessException;
import fund.paul.common.exception.ExceptionEnum;
import fund.paul.crypto.domain.business.future.pojo.FutureDTO;
import fund.paul.crypto.domain.business.future.pojo.LimitedDTO;
import fund.paul.crypto.domain.pojo.ExchangerType;
import fund.paul.crypto.domain.pojo.OperateType;
import fund.paul.crypto.domain.pojo.ResType;
import fund.paul.websocket.application.pojo.WssCallback;
import fund.paul.websocket.domain.pojo.OkWssConnection;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

/**
 * 合约的接口
 *
 * @author paul
 * @date 2024/12/12 13:28
 */
public interface IWssConnService {
    Logger LOGGER = LoggerFactory.getLogger(IWssConnService.class);

    Map<ExchangerType, LimitedDTO> LIMIT_MAP = new ConcurrentHashMap<>();

    public String getWebSocketURL();

    public WssCallback getOnMessageCallback();

    public WssCallback getOnClosingCallback();

    public WssCallback getOnOpenCallback();

    default public BigDecimal getPrice(String coinKey) {
        throw new UnsupportedOperationException();
    }

    public Result<Object> sendMsg(FutureDTO futureDTO);

    public WssCallback getOnFailureCallback();


    default void subscribeCoin(String coin, ResType resType, OperateType operateType) {
        throw new UnsupportedOperationException();
    }

    public OkWssConnection getOkWssConnection();

    public void setOkWssConnection(OkWssConnection okWssConnection);

    public String getApikey();

    ExchangerType getExchangerType();

    default Result<Object> sendMsgByWss(Object params) {
        if (ObjectUtils.isEmpty(getOkWssConnection())) {
            LOGGER.error("connection is empty");
            throw new BusinessException(ExceptionEnum.WSS_CONNECTION_FAILED);
        }
        if (checkLimit(getExchangerType())) {
            LOGGER.error("send limit exceeded");
            return Result.failed(ExceptionEnum.REQ_TOO_MANY);
        }
        if (params instanceof Map<?,?>) {
            getOkWssConnection().send(getValidSendMsg((Map<String, Object>) params));
        } else {
            getOkWssConnection().send(params);
        }
        LOGGER.warn("send ok wss. connection :{}, params :{}", getOkWssConnection().getConnectionId(), params);
        return Result.succeed();
    }

    public String getValidSendMsg(Map<String, Object> params);

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
