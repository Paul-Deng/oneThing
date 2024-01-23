package fund.paul.api;

import fund.paul.bean.SysUser;
import fund.paul.common.service.ISuperService;

/**
 * @author paul
 * @date 2024/1/23 02:01
 */
public interface ISysUserService extends ISuperService<SysUser> {
    public SysUser selectUserById(Long id);
}
