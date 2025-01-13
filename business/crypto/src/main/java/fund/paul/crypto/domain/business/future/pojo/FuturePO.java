package fund.paul.crypto.domain.business.future.pojo;

import fund.paul.common.basic.LongPKModel;
import java.math.BigDecimal;
import java.util.List;

/**
 * 合约的对象类
 *
 * @author paul
 * @date 2024/12/12 13:46
 */
public class FuturePO extends LongPKModel<FuturePO> {

    /**
     * 币价值对
     */
    private String coinKey;

    /**
     * 成交的平均价格。
     */
    private BigDecimal avgPrice;

    /**
     * 终止价格线
     */
    private BigDecimal slPrice;

    /**
     * 入场的最高价格
     */
    private BigDecimal entryMaxPrice;

    /**
     * 入场的最低价格
     */
    private BigDecimal entryMinPrice;

    /**
     * 合约盈利价格
     */
    private List<BigDecimal> tpPriceList;

    /**
     * 本次合约利润
     */
    private BigDecimal profit;

    /**
     * 合约数量
     */
    private BigDecimal count;
}
