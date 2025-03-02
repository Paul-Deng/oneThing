package fund.paul.user.controller;

import fund.paul.common.basic.Result;
import fund.paul.common.constant.SystemConstants;
import fund.paul.user.domain.pojo.SysUser;
import fund.paul.user.domain.pojo.convert.UserConvert;
import fund.paul.user.domain.service.ISysUserService;
import fund.paul.userapi.api.UserApi;
import fund.paul.userapi.dto.UserDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(SystemConstants.ServicePrefix.USER_SERVICE + "/user")
public class UserController implements UserApi {

    @Resource
    private ISysUserService userService;

    @Override
    public Result<UserDTO> getById(Long id) {
        return Result.succeed(UserConvert.INSTANCE.PO2DTO(userService.getById(id)));
    }

    @Override
    public Result<UserDTO> getByUsername(String username) {
        return Result.succeed(UserConvert.INSTANCE.PO2DTO(userService.getByUsername(username)));
    }

    @Override
    public Result<UserDTO> getByPhone(String phone) {
        return Result.succeed(UserConvert.INSTANCE.PO2DTO(userService.getByPhone(phone)));
    }

    @Override
    public Result<UserDTO> getByEmail(String email) {
        return Result.succeed(UserConvert.INSTANCE.PO2DTO(userService.getByEmail(email)));
    }

    @Override
    public Result<UserDTO> updateUser(UserDTO userDTO) {
        SysUser user = UserConvert.INSTANCE.DTO2PO(userDTO);
        userService.updateById(user);
        return Result.succeed(UserConvert.INSTANCE.PO2DTO(user));
    }
}