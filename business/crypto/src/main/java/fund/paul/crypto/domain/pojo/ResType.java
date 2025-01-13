package fund.paul.crypto.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 资源类型
 *
 * @author paul
 * @date 2025/1/6 23:50
 */
@Getter
@AllArgsConstructor
public enum ResType {
    DEPTH("depth"),
    MARKET_PRICE("markPrice@1s"),
    AGG_TRADE("aggTrade"),
    ;
    final String binance;

}
