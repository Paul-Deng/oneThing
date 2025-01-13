package fund.paul.crypto.domain.business.future.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fund.paul.common.basic.Result;
import fund.paul.common.exception.BusinessException;
import fund.paul.common.exception.ExceptionEnum;
import fund.paul.crypto.domain.business.future.pojo.FutureDTO;
import fund.paul.crypto.domain.business.future.pojo.FuturePO;
import fund.paul.crypto.domain.pojo.OperateType;
import fund.paul.crypto.domain.pojo.ResType;
import java.math.BigDecimal;
import java.util.Map;
import org.springframework.util.ObjectUtils;

/**
 * 合约交易的接口类
 *
 * @author paul
 * @date 2024/12/12 13:28
 */
public interface IUFutureService extends IFutureService, IService<FuturePO> {

    public BigDecimal getPrice(String coinKey);

    void subscribeCoin(String coin, ResType resType, OperateType operateType);

    @Override
    default Result<Object> sendMsgByWss(Object params) {
        if (ObjectUtils.isEmpty(getOkWssConnection())) {
            LOGGER.error("connection is empty");
            throw new BusinessException(ExceptionEnum.WSS_CONNECTION_FAILED);
        }
        if (checkSendLimit()) {
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

    public Result<Object> orderByWss(FutureDTO futureDTO);
}
