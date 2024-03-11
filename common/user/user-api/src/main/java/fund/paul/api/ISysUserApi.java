package fund.paul.api;

import fund.paul.SystemConstants;
import fund.paul.dto.UserDTO;
import fund.paul.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author paul
 * @date 2024/1/30 23:40
 */
@FeignClient(name = SystemConstants.NAME, path = SystemConstants.PREFIX + "/user")
public interface ISysUserApi {
     @PostMapping(path = "/add")
    public UserDTO addUser(UserDTO userDTO);

     @PostMapping(path = "/register")
    public UserVO register(UserVO userVO);
}
