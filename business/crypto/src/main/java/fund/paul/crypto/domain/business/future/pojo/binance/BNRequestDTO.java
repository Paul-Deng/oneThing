package fund.paul.crypto.domain.business.future.pojo.binance;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author paul
 * @date 2024/12/15 12:06
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BNRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 请求函数名称可以以显式版本为前缀，例如："v3/order.place"
     */
    private String method;

    /**
     *     "apiKey": "yeqKcXjtA9Eu4Tr3nJk61UJAGzXsEmFqqfVterxpMpR4peNfqE7Zl7oans8Qj089",
     *     "price": "42088.0",
     *     "quantity": "0.1",
     *     "recvWindow": 5000,
     *     "side": "BUY",
     *     "signature": "996962a19802b5a09d7bc6ab1524227894533322a2f8a1f8934991689cabf8fe",
     *     "symbol": "BTCUSDT",
     *     "timeInForce": "GTC",
     *     "timestamp": 1705311512994,
     *     "type": "LIMIT"
     *
     *     params 的顺序不重要。
     */
    private Object params;
}
