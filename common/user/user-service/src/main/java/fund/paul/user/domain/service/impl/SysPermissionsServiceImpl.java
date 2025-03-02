package fund.paul.user.domain.service.impl;

import fund.paul.common.constant.Constants;
import fund.paul.common.service.impl.SuperServiceImpl;
import fund.paul.user.domain.pojo.SysPermissions;
import fund.paul.user.domain.mapper.SysPermissionsMapper;
import fund.paul.user.domain.service.ISysPermissionsService;
import fund.paul.db.utils.CustomLambdaQueryWrapper;
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
                        .eq(SysPermissions::getType, Constants.SysPermission.TYPE_LEVEL_ONE));
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
