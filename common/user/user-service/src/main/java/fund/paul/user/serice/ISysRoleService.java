package fund.paul.user.serice;

import fund.paul.common.service.ISuperService;
import fund.paul.user.bean.SysRole;

import java.util.List;

public interface ISysRoleService extends ISuperService<SysRole> {
    int createRole(SysRole role);

    int updateRole(SysRole role);

    int deleteOrDisableRole(SysRole role);

    List<SysRole> getAllRoles();

    List<SysRole> getRolesByIds(List<Long> ids);
}