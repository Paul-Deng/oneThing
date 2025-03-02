package fund.paul.user.domain.service.impl;

import fund.paul.common.service.impl.SuperServiceImpl;
import fund.paul.user.domain.pojo.SysUsersRoles;
import fund.paul.user.domain.mapper.SysUsersRolesMapper;
import fund.paul.user.domain.service.ISysUsersRolesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author paul
 * @date 2024/1/23 13:31
 */
@Service
@Slf4j
public class SysUsersRolesServiceImpl extends SuperServiceImpl<SysUsersRolesMapper, SysUsersRoles> implements ISysUsersRolesService {
}
