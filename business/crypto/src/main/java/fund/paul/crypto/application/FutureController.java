package fund.paul.crypto.application;

import fund.paul.common.basic.PageResult;
import fund.paul.crypto.domain.business.future.pojo.FuturePO;
import fund.paul.crypto.domain.business.future.service.IFutureService;
import fund.paul.cryptoapi.pojo.OrderDTO;
import fund.paul.crypto.domain.pojo.order.OrderStructConvert;
import fund.paul.cryptoapi.feign.IFutureWebService;
import fund.paul.cryptoapi.pojo.OrderRequestParams;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 合约controller
 *
 * @author paul
 * @date 2025/1/15 01:27
 */
@RestController
public class FutureController implements IFutureWebService {

    @Autowired
    IFutureService futureService;

    @Override
    public PageResult<OrderDTO> getOrderList(OrderRequestParams orderRequestParams) {
        List<FuturePO> futurePOList = futureService.list(orderRequestParams);
        return PageResult.<OrderDTO>builder()
                .data(OrderStructConvert.INSTANCE.FuturePOList2OrderDTOList(futurePOList))
                .total(futureService.count(orderRequestParams)).build();
    }
}
