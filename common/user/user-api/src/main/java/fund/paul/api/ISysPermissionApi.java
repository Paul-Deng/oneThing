package fund.paul.api;

import fund.paul.SystemConstants;
import fund.paul.common.basic.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Set;

@FeignClient(name = SystemConstants.NAME, path = SystemConstants.PREFIX + "/permission")
public interface ISysPermissionApi {

    /**
     * 获得拥有多个角色的用户编号集合
     * 
     * @param roleIds 角色编号集合 example : 1, 2
     * @return 获取用户角色id List
     */
    @GetMapping("/user-role-id-list-by-role-id")
    Result<Set<Long>> getUserRoleIdListByRoleIds(@RequestParam("roleIds") Collection<Long> roleIds);

    /**
     * 判断是否有权限，任一一个即可
     *
     * @param userId 用户编号 example: 1
     * @param permissions 权限 read,write
     * @return
     */
    @GetMapping("/has-any-permissions")
    Result<Boolean> hasAnyPermissions(@RequestParam("userId") Long userId,
                                            @RequestParam("permissions") String... permissions);

    /**
     * 判断是否有角色，任一一个即可
     *
     * @param userId 用户编号 example : 1
     * @param roles 角色数组 exmple: 1,2,3
     * @return  是否
     */
    @GetMapping("/has-any-roles")
    Result<Boolean> hasAnyRoles(@RequestParam("userId") Long userId,
                                      @RequestParam("roles") String... roles);

//    /**
//     * 获得登陆用户的部门数据权限
//     *
//     * @param userId 用户编号 2
//     * @return
//     */
//    @GetMapping("/get-dept-data-permission")
//    Result<DeptDataPermissionRespDTO> getDeptDataPermission(@RequestParam("userId") Long userId);
}