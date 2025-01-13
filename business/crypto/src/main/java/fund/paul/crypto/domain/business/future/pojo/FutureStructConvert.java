package fund.paul.crypto.domain.business.future.pojo;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author paul
 * @date 2025/1/9 14:25
 */
@Mapper
public interface FutureStructConvert {
    FutureStructConvert INSTANCE = Mappers.getMapper(FutureStructConvert.class);

    FuturePO DTO2PO(FutureDTO futureDTO);
}
