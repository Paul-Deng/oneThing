package fund.paul.blackjackapi.feign;

import fund.paul.blackjackapi.feign.fallback.BlackJackWebServiceFallbackFactory;
import fund.paul.common.constant.SystemConstants;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author paul
 * @date 2025/1/31 19:31
 */

@FeignClient(name = SystemConstants.Service.CRYPTO_SERVICE, fallbackFactory = BlackJackWebServiceFallbackFactory.class)
public interface BlackJackWebService {
}
