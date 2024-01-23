package fund.paul.user.serice.impl;

import fund.paul.common.service.impl.SuperServiceImpl;
import fund.paul.user.bean.SysRole;
import fund.paul.user.mapper.SysRoleMapper;
import fund.paul.user.serice.ISysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author paul
 * @date 2024/1/23 13:21
 */
@Service
@Slf4j
public class SysRoleServiceImpl extends SuperServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public int createRole(SysRole role) {
        return roleMapper.insert(role);
    }

    @Override
    public int updateRole(SysRole role) {
        return roleMapper.updateById(role);
    }

    @Override
    public int deleteOrDisableRole(SysRole role) {
        return roleMapper.deleteOrDisableRole(role);
    }

    @Override
    public List<SysRole> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    @Override
    public List<SysRole> getRolesByIds(List<Long> ids) {
        return roleMapper.getRolesByIds(ids);
    }
}