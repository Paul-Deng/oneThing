package fund.paul.user.serice.impl;

import fund.paul.common.constant.CommonConstant;
import fund.paul.common.service.impl.SuperServiceImpl;
import fund.paul.user.bean.SysPermissions;
import fund.paul.user.mapper.SysPermissionsMapper;
import fund.paul.user.serice.ISysPermissionsService;
import fund.paul.utils.CustomLambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author paul
 * @date 2023/5/25 23:34
 */
@Slf4j
@Service
public class SysPermissionsServiceImpl extends SuperServiceImpl<SysPermissionsMapper, SysPermissions> implements ISysPermissionsService {

    @Override
    public List<SysPermissions> findAll() {
        return baseMapper.selectList(
                new CustomLambdaQueryWrapper<SysPermissions>().orderByAsc(SysPermissions::getSort));
    }


    @Override
    public List<SysPermissions> findOnes() {
        return baseMapper.selectList(
                new CustomLambdaQueryWrapper<SysPermissions>()
                        .eq(SysPermissions::getType, CommonConstant.SysPermission.TYPE_LEVEL_ONE));
    }

    @Override
    public void setMenuToRole(Long roleId, Collection<Long> menuIds) {

    }

    @Override
    public List<SysPermissions> findByRoles(Collection<Long> roleIds) {
        return null;
    }

    @Override
    public List<SysPermissions> findByRoles(Collection<Long> roleIds, Integer type) {
        return null;
    }

    @Override
    public List<SysPermissions> findByRoleCodes(Collection<String> roleCodes, Integer type) {
        return null;
    }
}
