package fund.paul.blackjackapi.feign.fallback;

import fund.paul.blackjackapi.feign.BlackJackWebService;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * @author paul
 * @date 2025/1/31 19:36
 */
public class BlackJackWebServiceFallbackFactory implements FallbackFactory<BlackJackWebService> {
    @Override
    public BlackJackWebService create(Throwable cause) {
        return null;
    }
}
