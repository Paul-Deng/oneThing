package fund.paul.user.domain.pojo.convert;

import fund.paul.user.domain.pojo.SysUser;
import fund.paul.userapi.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);
    
    UserDTO PO2DTO(SysUser user);
    
    SysUser DTO2PO(UserDTO userDTO);
}