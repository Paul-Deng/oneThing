package fund.paul.userapi.api;

import fund.paul.userapi.SystemConstants;
import fund.paul.common.basic.Result;
import fund.paul.userapi.dto.UserDTO;
import fund.paul.userapi.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author paul
 * @date 2024/1/30 23:40
 */
@FeignClient(name = SystemConstants.NAME, path = SystemConstants.PREFIX + "/user")
public interface ISysUserApi {
     @PostMapping(path = "/add")
    public Result<UserDTO> createUser(UserDTO userDTO);

     @PostMapping(path = "/register")
    public UserVO register(UserVO userVO);
}
