package fund.paul.user.domain.mapper;

import fund.paul.db.mapper.SuperMapper;
import fund.paul.user.domain.pojo.SysPermissions;
import org.apache.ibatis.annotations.Mapper;

/**
 * 权限的Mapper类
 *
 * @author paul
 * @date 2024/1/30 23:28
 */
@Mapper
public interface SysPermissionsMapper extends SuperMapper<SysPermissions> {

}

