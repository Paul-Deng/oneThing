package fund.paul.cryptoapi.feign;

import fund.paul.common.basic.PageResult;
import fund.paul.common.constant.SystemConstants;
import fund.paul.cryptoapi.feign.fallback.IFutureServiceFallbackFactory;
import fund.paul.cryptoapi.pojo.OrderDTO;
import fund.paul.cryptoapi.pojo.OrderRequestParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 合约接口类
 *
 * @author paul
 * @date 2025/1/15 00:08
 */

@FeignClient(name = SystemConstants.Service.CRYPTO_SERVICE, path = SystemConstants.ServicePrefix.CRYPTO_SERVICE, fallbackFactory = IFutureServiceFallbackFactory.class)
public interface IFutureWebService {

    /**
     * 获取订单列表
     *
     * @param orderRequestParams 订单请求参数
     * @return 返回数据
     */
    @GetMapping(value = "/order")
    PageResult<OrderDTO> getOrderList(@RequestBody OrderRequestParams orderRequestParams);
}
