package fund.paul.user.domain.service;

import fund.paul.common.service.ISuperService;
import fund.paul.user.domain.pojo.SysRoles;

import java.util.List;

public interface ISysRolesService extends ISuperService<SysRoles> {
    int createRole(SysRoles role);

    int updateRole(SysRoles role);

    int deleteOrDisableRole(SysRoles role);

    List<SysRoles> getAllRoles();

    List<SysRoles> getRolesByIds(List<Long> ids);
}