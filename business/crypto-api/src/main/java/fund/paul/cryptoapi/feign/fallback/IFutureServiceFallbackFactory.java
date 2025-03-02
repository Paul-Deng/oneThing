package fund.paul.cryptoapi.feign.fallback;

import fund.paul.common.basic.PageResult;
import fund.paul.cryptoapi.feign.IFutureWebService;
import fund.paul.cryptoapi.pojo.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 合约降级工厂
 *
 * @author paul
 * @date 2025/1/15 00:13
 */

@Slf4j
@Component
public class IFutureServiceFallbackFactory implements FallbackFactory<IFutureWebService> {


    @Override
    public IFutureWebService create(Throwable throwable) {
        return (orderDTO) -> {
            log.error("future service error", throwable);
            return PageResult.<OrderDTO>builder().build();
        };
    }
}
