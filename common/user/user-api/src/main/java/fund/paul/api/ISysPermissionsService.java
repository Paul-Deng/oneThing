package fund.paul.api;

import fund.paul.common.service.ISuperService;
import fund.paul.user.bean.SysPermissions;

import java.util.List;
import java.util.Set;

/**
 * 接口类
 *
 * @author paul
 * @date 2023/5/25 23:34
 */
public interface ISysPermissionsService extends ISuperService<SysPermissions> {
    /**
     * 查询所有菜单
     */
    List<SysPermissions> findAll();

    /**
     * 查询所有一级菜单
     */
    List<SysPermissions> findOnes();

    /**
     * 角色分配菜单
     * @param roleId
     * @param menuIds
     */
    void setMenuToRole(Long roleId, Set<Long> menuIds);

    /**
     * 角色菜单列表
     * @param roleIds 角色ids
     * @return
     */
    List<SysPermissions> findByRoles(Set<Long> roleIds);

    /**
     * 角色菜单列表
     * @param roleIds 角色ids
     * @param roleIds 是否菜单
     * @return
     */
    List<SysPermissions> findByRoles(Set<Long> roleIds, Integer type);

    /**
     * 角色菜单列表
     * @param roleCodes
     * @return
     */
    List<SysPermissions> findByRoleCodes(Set<String> roleCodes, Integer type);
}
