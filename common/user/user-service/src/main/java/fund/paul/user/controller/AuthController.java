package fund.paul.user.controller;

import fund.paul.common.basic.Result;
import fund.paul.user.domain.service.IAuthService;
import fund.paul.userapi.api.AuthApi;
import fund.paul.userapi.dto.UserDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController implements AuthApi {

    @Resource
    private IAuthService authService;

    @Override
    public Result<String> login(UserDTO userDTO) {
        return Result.succeed(authService.login(userDTO));
    }

    @Override
    public Result<Void> logout() {
        authService.logout();
        return Result.succeed();
    }

    @Override
    public Result<UserDTO> register(UserDTO userDTO) {
        return Result.succeed(authService.register(userDTO));
    }
}