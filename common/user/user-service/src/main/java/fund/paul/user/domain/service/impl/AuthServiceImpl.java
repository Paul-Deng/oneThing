package fund.paul.user.domain.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fund.paul.common.exception.BusinessException;
import fund.paul.common.exception.ExceptionEnum;
import fund.paul.db.utils.CustomLambdaQueryWrapper;
import fund.paul.redis.lock.RedisDistributedLock;
import fund.paul.user.domain.pojo.SysUser;
import fund.paul.user.domain.pojo.convert.UserConvert;
import fund.paul.user.domain.service.IAuthService;
import fund.paul.user.domain.service.ISysUserService;
import fund.paul.userapi.dto.UserDTO;
import jakarta.annotation.Resource;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class AuthServiceImpl implements IAuthService {

    @Resource
    private ISysUserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;

    private static final String REGISTER_KEY = "register-";

    @Resource
    RedissonClient redissonClient;

    @Override
    public String login(UserDTO userDTO) {
        SysUser user = userService.getByUsername(userDTO.getUsername());
        if (user == null || !userService.validatePassword(userDTO.getPassword(), user.getPassword())) {
            throw new BusinessException(ExceptionEnum.USER_NAME_OR_PASSWORD_ERROR);
        }
        
        StpUtil.login(user.getId());
        return StpUtil.getTokenValue();
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        if (ObjectUtils.isEmpty(userDTO) || !StringUtils.hasLength(userDTO.getUsername())) {
            throw new BusinessException(ExceptionEnum.PARAMS_ERROR);
        }
        LambdaQueryWrapper<SysUser> eq = new CustomLambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, userDTO.getUsername());
        SysUser sysUser = UserConvert.INSTANCE.DTO2PO(userDTO);
        sysUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userService.saveIdempotency(sysUser, new RedisDistributedLock(redissonClient), REGISTER_KEY + userDTO.getUsername(), eq);
        SysUser one = userService.getOne(eq);
        Arrays.fill(one.getPassword().toCharArray(), '0');
        return UserConvert.INSTANCE.PO2DTO(one);
    }
}