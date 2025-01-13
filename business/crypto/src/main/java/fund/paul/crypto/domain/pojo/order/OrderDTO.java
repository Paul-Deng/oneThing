package fund.paul.crypto.domain.pojo.order;

import fund.paul.common.basic.LongPKModel;
import java.io.Serial;
import java.math.BigDecimal;
import lombok.Builder;

/**
 * 订单传输层对象
 *
 * @author paul
 * @date 2025/1/11 21:31
 */
@Builder
public class OrderDTO extends LongPKModel<OrderDTO> {
    @Serial
    private static final long serialVersionUID = 7581889233829636726L;

    private BigDecimal price;

    private BigDecimal count;

    private Order.Status status;

    private Long parentId;
}
