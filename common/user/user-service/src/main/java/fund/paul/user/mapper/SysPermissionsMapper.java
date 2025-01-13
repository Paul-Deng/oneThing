package fund.paul.user.mapper;

import fund.paul.db.mapper.SuperMapper;
import fund.paul.user.bean.SysPermissions;
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

