package fund.paul.crypto.domain.business.future.pojo;

import fund.paul.common.basic.LongPKModel;
import fund.paul.crypto.domain.pojo.ExchangerType;
import java.math.BigDecimal;

/**
 * @author paul
 * @date 2025/1/10 11:24
 */
public class FutureAccountPO extends LongPKModel<FutureAccountPO> {

    /**
     * 交易所的平台
     */
    private ExchangerType exchangerType;

    /**
     * 计价标准
     */
    private String coinKey;

    /**
     * 资产
     */
    private BigDecimal count;


}
