package fund.paul.user.serice.impl;

import fund.paul.common.service.impl.SuperServiceImpl;
import fund.paul.user.bean.SysUserRole;
import fund.paul.user.mapper.SysUserRoleMapper;
import fund.paul.user.serice.ISysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author paul
 * @date 2024/1/23 13:31
 */
@Service
@Slf4j
public class SysUserRoleServiceImpl extends SuperServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
}
