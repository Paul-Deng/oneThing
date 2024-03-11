package fund.paul.user.serice;

import fund.paul.user.bean.SysUsers;
import fund.paul.common.service.ISuperService;

/**
 * @author paul
 * @date 2024/1/23 02:01
 */
public interface ISysUsersService extends ISuperService<SysUsers> {
    public SysUsers selectUserById(Long id);
}
