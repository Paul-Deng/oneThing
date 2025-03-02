package fund.paul.crypto.domain.business.future.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fund.paul.crypto.domain.business.future.pojo.FuturePO;
import fund.paul.cryptoapi.pojo.OrderRequestParams;
import java.util.List;

/**
 * 合约交易的接口类
 *
 * @author paul
 * @date 2024/12/12 13:28
 */
public interface IFutureService extends IService<FuturePO> {

    /**
     * 获取合约交易的接口
     *
     * @param orderRequestParams 订单请求参数
     * @return 合约列表
     */
    public List<FuturePO> list(OrderRequestParams orderRequestParams);

    public Long count(OrderRequestParams orderRequestParams);
}
