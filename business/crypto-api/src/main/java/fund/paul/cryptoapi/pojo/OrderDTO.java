package fund.paul.cryptoapi.pojo;

import fund.paul.common.basic.LongPKModel;
import java.io.Serial;
import java.math.BigDecimal;
import java.util.LinkedList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 订单传输层对象
 *
 * @author paul
 * @date 2025/1/11 21:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO extends LongPKModel<OrderDTO> {

    @Serial
    private static final long serialVersionUID = 7581889233829636726L;

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

    /**
     * 父订单id
     */
    private Long parentId;

    /**
     * 被哪位驱动下定
     */
    private Long orderByWho;
}
