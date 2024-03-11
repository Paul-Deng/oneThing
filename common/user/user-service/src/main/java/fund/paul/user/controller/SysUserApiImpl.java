package fund.paul.user.controller;

import fund.paul.api.ISysUserApi;
import fund.paul.dto.UserDTO;
import fund.paul.user.serice.ISysUsersService;
import fund.paul.vo.UserVO;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author paul
 * @date 2024/1/30 23:46
 */
@RestController
public class SysUserApiImpl implements ISysUserApi {

    ISysUsersService usersService;

    public SysUserApiImpl(ISysUsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        
    }

    /**
     * 注册用户
     *
     * @param userVO 用户vo视图
     * @return 用户vo
     */
    @Override
    public UserVO register(UserVO userVO) {
        return null;
    }
}
