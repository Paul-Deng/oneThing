package fund.paul.user.controller;

import fund.paul.userapi.api.ISysUserApi;
import fund.paul.common.basic.Result;
import fund.paul.userapi.dto.UserDTO;
import fund.paul.user.serice.ISysUsersService;
import fund.paul.userapi.vo.UserVO;
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
    public Result<UserDTO> createUser(UserDTO userDTO) {
        
        // 鉴权，用户是否有权限操作该数据。
        return Result.succeed(UserDTO.builder().build());
    }

    /**
     * 注册用户
     *
     * @param userVO 用户vo视图
     * @return 用户vo
     */
    @Override
    public UserVO register(UserVO userVO) {
        return UserVO.childBuilder().build();
    }
}
