package fund.paul.user.domain.service.impl;

import fund.paul.common.service.impl.SuperServiceImpl;
import fund.paul.user.domain.pojo.SysUser;
import fund.paul.user.domain.mapper.SysUserMapper;
import fund.paul.user.domain.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Slf4j
@Service
public class SysUserServiceImpl extends SuperServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    private final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Resource
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysUserMapper SysUserMapper;

    // 获取指定ID的用户信息
    public SysUser selectUserById(Long id) {
        LOGGER.info("start select user information: {}", id);
        SysUser user = SysUserMapper.selectById(id);
        LOGGER.info("No user found with the ID: {}", id);
        return user;
    }

    // 更新用户信息
    public int updateUser(SysUser dto, Long currentUserId) {
        SysUser user = new SysUser();
        // 将dto中的属性赋值给user实体

        user.setUpdatedTime(System.currentTimeMillis());
        user.setUpdatedBy(currentUserId);

        int result = SysUserMapper.updateUser(user);
        if (result > 0) {
            LOGGER.info("Successfully updated user information: {}", dto);
        } else {
            LOGGER.warn("Failed to update user information: {}", dto);
        }
        return result;
    }

    // 逻辑删除用户
    public int deleteUserById(SysUser dto, Long currentUserId) {
        SysUser user = new SysUser();
        // 将dto中的属性（主要是id和deleted）赋值给user实体

        user.setUpdatedTime(System.currentTimeMillis());
        user.setUpdatedBy(currentUserId);

        int result = SysUserMapper.deleteUserById(user);
        if (result > 0) {
            LOGGER.info("Successfully logically deleted the user: {}", dto.getId());
        } else {
            LOGGER.warn("Failed to logically delete the user: {}", dto.getId());
        }
        return result;
    }

    @Override
    public SysUser getByUsername(String username) {
        return lambdaQuery()
                .eq(SysUser::getUsername, username)
                .one();
    }

    @Override
    public SysUser getByPhone(String phone) {
        return lambdaQuery()
                .eq(SysUser::getPhone, phone)
                .one();
    }

    @Override
    public SysUser getByEmail(String email) {
        return lambdaQuery()
                .eq(SysUser::getEmail, email)
                .one();
    }

    @Override
    public boolean validatePassword(String inputPassword, String storedPassword) {
        return passwordEncoder.matches(inputPassword, storedPassword);
    }

    @Override
    public boolean checkUsernameExists(String username) {
        return lambdaQuery()
                .eq(SysUser::getUsername, username)
                .exists();
    }
}