package fund.paul.userapi.api;

import fund.paul.common.basic.Result;
import fund.paul.common.constant.SystemConstants;
import fund.paul.userapi.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", path = SystemConstants.ServicePrefix.USER_SERVICE + "/auth")
public interface AuthApi {
    
    @PostMapping("/login")
    Result<String> login(@RequestBody UserDTO userDTO);
    
    @PostMapping("/logout")
    Result<Void> logout();
    
    @PostMapping("/register")
    Result<UserDTO> register(@RequestBody UserDTO userDTO);
}