package fund.paul.crypto.domain.pojo.order;

import fund.paul.crypto.domain.business.future.pojo.FutureDTO;
import fund.paul.crypto.domain.business.future.pojo.FuturePO;
import fund.paul.cryptoapi.pojo.OrderDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author paul
 * @date 2025/1/9 14:25
 */
@Mapper
public interface OrderStructConvert {
    OrderStructConvert INSTANCE = Mappers.getMapper(OrderStructConvert.class);

    FuturePO DTO2PO(FutureDTO futureDTO);

    FutureDTO OrderDTO2FutureDTO(OrderDTO orderDTO);

    OrderDTO FuturePO2OrderDTO(FuturePO futurePO);

    FuturePO OrderDTO2FuturePO(OrderDTO orderDTO);

    List<OrderDTO> FuturePOList2OrderDTOList(List<FuturePO> futurePOList);

}
