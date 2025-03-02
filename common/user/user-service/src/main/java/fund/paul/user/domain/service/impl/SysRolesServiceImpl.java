package fund.paul.user.domain.service.impl;

import fund.paul.common.service.impl.SuperServiceImpl;
import fund.paul.user.domain.pojo.SysRoles;
import fund.paul.user.domain.mapper.SysRolesMapper;
import fund.paul.user.domain.service.ISysRolesService;
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
public class SysRolesServiceImpl extends SuperServiceImpl<SysRolesMapper, SysRoles> implements ISysRolesService {
    @Autowired
    private SysRolesMapper roleMapper;

    @Override
    public int createRole(SysRoles role) {
        return roleMapper.insert(role);
    }

    @Override
    public int updateRole(SysRoles role) {
        return roleMapper.updateById(role);
    }

    @Override
    public int deleteOrDisableRole(SysRoles role) {
        return roleMapper.deleteOrDisableRole(role);
    }

    @Override
    public List<SysRoles> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    @Override
    public List<SysRoles> getRolesByIds(List<Long> ids) {
        return roleMapper.getRolesByIds(ids);
    }
}