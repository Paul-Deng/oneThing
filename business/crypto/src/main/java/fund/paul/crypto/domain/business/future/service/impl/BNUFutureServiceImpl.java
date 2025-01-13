package fund.paul.crypto.domain.business.future.service.impl;

import cn.hutool.json.JSONUtil;
import fund.paul.common.basic.Result;
import fund.paul.common.constant.Constants;
import fund.paul.common.exception.ExceptionEnum;
import fund.paul.common.service.impl.SuperServiceImpl;
import fund.paul.common.utils.IdGenerator;
import fund.paul.crypto.domain.business.future.pojo.FutureDTO;
import fund.paul.crypto.domain.business.future.pojo.FuturePO;
import fund.paul.crypto.domain.business.future.pojo.FutureStructConvert;
import fund.paul.crypto.domain.business.future.pojo.binance.BNRequestDTO;
import fund.paul.crypto.domain.business.future.pojo.binance.BNRestRspDTO;
import fund.paul.crypto.domain.business.future.pojo.binance.BNWssRspDTO;
import fund.paul.crypto.domain.business.future.service.IUFutureService;
import fund.paul.crypto.domain.business.utils.SignatureGenerator;
import fund.paul.crypto.domain.config.EnvConfigManager;
import fund.paul.crypto.domain.pojo.ExchangerType;
import fund.paul.crypto.domain.pojo.OperateType;
import fund.paul.crypto.domain.pojo.ProductType;
import fund.paul.crypto.domain.pojo.ProtocolType;
import fund.paul.crypto.domain.pojo.ResType;
import fund.paul.crypto.manufacture.mapper.FutureMapper;
import fund.paul.websocket.application.pojo.WssCallback;
import fund.paul.websocket.domain.pojo.OkWssConnection;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * U本位合约的实现类
 *
 * @author paul
 * @date 2024/12/12 13:50
 */
@Service
public class BNUFutureServiceImpl extends SuperServiceImpl<FutureMapper, FuturePO> implements IUFutureService {

    private EnvConfigManager envConfigManager;

    /**
     * 这里要评估是不是只能用并发map
     */
    private static final Map<String, BigDecimal> PRICE_CACHE = new ConcurrentHashMap<>();

    private OkWssConnection SESSION;

    @Autowired
    public BNUFutureServiceImpl(EnvConfigManager envConfigManager) {
        this.envConfigManager = envConfigManager;
    }

    @PostConstruct
    public void init() {
        initWebsocket();
        initLimit(ExchangerType.BINANCE);
    }

    @Override
    public BigDecimal getPrice(String coinKey) {
        if (!PRICE_CACHE.containsKey(coinKey)) {
            return null;
        }
        return PRICE_CACHE.get(coinKey);
    }

    @Override
    public String getWebSocketURL() {
        return envConfigManager.getWebsocketUrl(ProductType.FUTURE, ExchangerType.BINANCE, ProtocolType.WSS);
    }

    @Override
    public WssCallback getOnMessageCallback() {
        return (msg) -> {
            LOGGER.info("receive msg: {}", msg);
            // 这里后需要处理成工厂模式
            if (Constants.Network.PING.equals(msg)) {
                getOkWssConnection().send(Constants.Network.PONG);
            }
            // 异常错误码情况
            BNRestRspDTO response = JSONUtil.toBean(msg, BNRestRspDTO.class);
            if (!ObjectUtils.isEmpty(response) && ExceptionEnum.REQ_TOO_MANY.getCode().equals(response.getCode())) {
                trans2Limit(ExchangerType.BINANCE);
            }
            BNWssRspDTO wssRspDTO = JSONUtil.toBean(msg, BNWssRspDTO.class);
        };
    }

    @Override
    public WssCallback getOnClosingCallback() {
        return (msg) -> {
            LOGGER.warn("closing call back: connection :{}, msg :{}", SESSION.getConnectionId(), msg);
            PRICE_CACHE.clear();
            init();
        };
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
    public OkWssConnection getOkWssConnection() {
        return SESSION;
    }

    @Override
    public void setOkWssConnection(OkWssConnection okWssConnection) {
        this.SESSION = okWssConnection;
    }

    @Override
    public String getApikey() {
        return "80D0YPTmLyaFUTYptsngrlAVHNz1HTxTAtK8bWjcTi6bYSu81uRn6y3c7lJUYphY";
    }

    @Override
    public void subscribeCoin(String coin, ResType resType, OperateType operateType) {
        String requestId = IdGenerator.getIdStr();
        LOGGER.warn("subscribe coin: {}, requestId : {}", coin, requestId);
        if (!StringUtils.hasLength(coin) || ObjectUtils.isEmpty(resType) || ObjectUtils.isEmpty(operateType)) {
            LOGGER.error("subscribe coin failed : coin :{}, resType : {}, operateType : {}", coin, resType, operateType);
            return;
        }
        List<String> params = Collections.singletonList(String.join(Constants.Characters.AT, coin, resType.getBinance()));
        BNRequestDTO bnRequestDTO = BNRequestDTO.builder()
                .method(operateType.getBinance())
                .params(params)
                .id(requestId)
                .build();
        sendMsgByWss(bnRequestDTO);
    }

    public String getValidSendMsg(Map<String, Object> params) {
        String signature = SignatureGenerator.getSignature(JSONUtil.toJsonStr(params), getApikey());
        params.put("signature", signature);
        return JSONUtil.toJsonStr(params);
    }

    @Override
    public Result<Object> orderByWss(FutureDTO futureDTO) {
        FuturePO futurePO = FutureStructConvert.INSTANCE.DTO2PO(futureDTO);
        return Result.succeed();
    }
}
