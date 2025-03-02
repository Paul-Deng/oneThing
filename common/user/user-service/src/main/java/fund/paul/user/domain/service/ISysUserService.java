package fund.paul.user.domain.service;

import fund.paul.common.service.ISuperService;
import fund.paul.user.domain.pojo.SysUser;

public interface ISysUserService extends ISuperService<SysUser> {
    SysUser getByUsername(String username);
    SysUser getByPhone(String phone);
    SysUser getByEmail(String email);
    boolean validatePassword(String inputPassword, String storedPassword);
    boolean checkUsernameExists(String username);
}
