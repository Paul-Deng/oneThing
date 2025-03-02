package fund.paul.crypto.domain.business.future.pojo;

import fund.paul.common.basic.LongPKModel;
import fund.paul.cryptoapi.pojo.Order;
import java.io.Serial;
import java.math.BigDecimal;
import java.util.LinkedList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 合约传输层对象
 *
 * @author paul
 * @date 2025/1/9 14:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FutureDTO extends LongPKModel<FutureDTO> {

    @Serial
    private static final long serialVersionUID = 2762121777645768216L;
    /**
     * 币价值对
     */
    private String coinKey;

    /**
     * 成交的平均价格。
     */
    private BigDecimal avgPrice;

    /**
     * 订单总额
     */
    private BigDecimal totalAmount;

    /**
     * 终止价格线
     */
    private BigDecimal slPrice;

    /**
     * 入场的最高价格
     */
    private BigDecimal maxEntryPrice;

    /**
     * 入场的最低价格
     */
    private BigDecimal minEntryPrice;

    /**
     * 合约盈利价格
     */
    private LinkedList<BigDecimal> tpPriceList;

    /**
     * 本次合约利润
     */
    private BigDecimal profit;

    /**
     * 合约数量
     */
    private BigDecimal count;

    /**
     * 状态
     */
    private Order.Status status;

    private Long parentId;
}
