package fund.paul.userapi.api;

import fund.paul.common.basic.Result;
import fund.paul.common.constant.SystemConstants;
import fund.paul.userapi.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = SystemConstants.Service.USER_SERVICE, path = SystemConstants.ServicePrefix.USER_SERVICE)
public interface UserApi {
    @GetMapping("/{id}")
    Result<UserDTO> getById(@PathVariable Long id);
    
    @GetMapping("/username/{username}")
    Result<UserDTO> getByUsername(@PathVariable String username);
    
    @GetMapping("/phone/{phone}")
    Result<UserDTO> getByPhone(@PathVariable String phone);
    
    @GetMapping("/email/{email}")
    Result<UserDTO> getByEmail(@PathVariable String email);
    
    @PutMapping("/update")
    Result<UserDTO> updateUser(@RequestBody UserDTO userDTO);
}