package fund.paul.crypto.domain.business.future.pojo.binance;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 币安websocket响应流
 *
 * @author paul
 * @date 2025/1/7 13:12
 */
@Getter
@AllArgsConstructor
public class BNWssRspDTO {
    private Integer id;

    private Object result;
}
