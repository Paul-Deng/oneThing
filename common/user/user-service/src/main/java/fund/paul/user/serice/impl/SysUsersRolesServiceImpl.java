package fund.paul.user.serice.impl;

import fund.paul.common.service.impl.SuperServiceImpl;
import fund.paul.user.bean.SysUsersRoles;
import fund.paul.user.mapper.SysUsersRolesMapper;
import fund.paul.user.serice.ISysUsersRolesService;
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
