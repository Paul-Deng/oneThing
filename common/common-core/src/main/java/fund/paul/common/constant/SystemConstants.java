package fund.paul.common.constant;

/**
 * 系统常量
 *
 * @author paul
 * @date 2025/1/15 00:11
 */
public interface SystemConstants {
    public interface Service {
        String CRYPTO_SERVICE = "crypto-service";

        String USER_SERVICE = "user-service";
    }

    public interface ServicePrefix {
        String CRYPTO_SERVICE = "crypto";

        String USER_SERVICE = "user";
    }

    public interface Bean {
        String BN_U_FUTURE_WSS = "binanceUFutureWss";
    }
}
