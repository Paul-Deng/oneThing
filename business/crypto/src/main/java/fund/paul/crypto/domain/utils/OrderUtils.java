package fund.paul.crypto.domain.utils;

import fund.paul.cryptoapi.pojo.Order;
import fund.paul.cryptoapi.pojo.OrderDTO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;
import org.springframework.util.ObjectUtils;

/**
 * 订单工具类
 *
 * @author paul
 * @date 2025/1/14 22:57
 */
public final class OrderUtils {
    private static final Set<Order.Status> RUNNING_STATUS = Set.of(Order.Status.FILLED, Order.Status.PARTIALLY_FILLED);

    private OrderUtils() {}

    public static boolean isRunning(OrderDTO orderDTO) {
        if (ObjectUtils.isEmpty(orderDTO.getStatus())) {
            return false;
        }
        return RUNNING_STATUS.contains(orderDTO.getStatus());
    }

    public static BigDecimal standardize(BigDecimal bigDecimal) {
        return bigDecimal.setScale(2, RoundingMode.DOWN);
    }

    public static BigDecimal standardize(int count) {
        return standardize(BigDecimal.valueOf(count));
    }

    public static BigDecimal standardize(double count) {
        return standardize(BigDecimal.valueOf(count));
    }

    public static BigDecimal standardize(long count) {
        return standardize(BigDecimal.valueOf(count));
    }

    public static BigDecimal standardize(String count) {
        return standardize(new BigDecimal(count));
    }
}
