package fund.paul.user.controller;

import fund.paul.api.ISysPermissionApi;
import fund.paul.common.basic.Result;
import fund.paul.user.serice.ISysPermissionsService;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;

/**
 * @author paul
 * @date 2024/1/25 01:25
 */
@RestController
public class SysPermissionApiImpl implements ISysPermissionApi {

    ISysPermissionsService sysPermissionsService;

    public SysPermissionApiImpl(ISysPermissionsService sysPermissionsService) {
        this.sysPermissionsService = sysPermissionsService;
    }

    @Override
    public Result<Set<Long>> getUserRoleIdListByRoleIds(Collection<Long> roleIds) {
        return null;
    }

    @Override
    public Result<Boolean> hasAnyPermissions(Long userId, String... permissions) {
        return null;
    }

    @Override
    public Result<Boolean> hasAnyRoles(Long userId, String... roles) {
        return null;
    }
}
