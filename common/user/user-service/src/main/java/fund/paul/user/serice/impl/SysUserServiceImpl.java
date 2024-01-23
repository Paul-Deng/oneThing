package fund.paul.user.serice.impl;

import fund.paul.api.ISysUserService;
import fund.paul.bean.SysUser;
import fund.paul.common.service.impl.SuperServiceImpl;
import fund.paul.user.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends SuperServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    private final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    // 获取指定ID的用户信息
    public SysUser selectUserById(Long id) {
        logger.info("start select user information: {}", id);
        SysUser user = sysUserMapper.selectById(id);
        logger.info("No user found with the ID: {}", id);
        return user;
    }

    // 更新用户信息
    public int updateUser(SysUser dto, Long currentUserId) {
        SysUser user = new SysUser();
        // 将dto中的属性赋值给user实体

        user.setUpdatedTime(System.currentTimeMillis());
        user.setUpdatedBy(currentUserId);

        int result = sysUserMapper.updateUser(user);
        if (result > 0) {
            logger.info("Successfully updated user information: {}", dto);
        } else {
            logger.warn("Failed to update user information: {}", dto);
        }
        return result;
    }

    // 逻辑删除用户
    public int deleteUserById(SysUser dto, Long currentUserId) {
        SysUser user = new SysUser();
        // 将dto中的属性（主要是id和deleted）赋值给user实体

        user.setDeleted(1);
        user.setUpdatedTime(System.currentTimeMillis());
        user.setUpdatedBy(currentUserId);

        int result = sysUserMapper.deleteUserById(user);
        if (result > 0) {
            logger.info("Successfully logically deleted the user: {}", dto.getId());
        } else {
            logger.warn("Failed to logically delete the user: {}", dto.getId());
        }
        return result;
    }
}