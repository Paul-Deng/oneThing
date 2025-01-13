package fund.paul.user.controller;

import fund.paul.user.bean.SysUsers;
import fund.paul.user.serice.ISysUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ISysUsersService userService;

    // GET请求获取用户信息
    @GetMapping("/{id}")
    public SysUsers getUser(@PathVariable Long id) {
        return userService.selectUserById(id);
    }

//    // PUT请求更新用户信息
//    @PutMapping("/{id}")
//    public int updateUser(@PathVariable Long id, @RequestBody UserDTO dto) {
//        Long currentUserId = getNowSysSuser();
//        return userService.updateUser(dto, currentUserId);
//    }
//
//    // DELETE请求逻辑删除用户
//    @DeleteMapping("/{id}")
//    public int deleteUser(@PathVariable Long id, @RequestBody UserDTO dto) {
//        Long currentUserId = // 获取当前操作用户的ID...
//        return userService.deleteUserById(dto, currentUserId);
//    }
}