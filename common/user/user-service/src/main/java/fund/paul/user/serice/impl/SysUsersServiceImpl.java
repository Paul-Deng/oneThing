package fund.paul.user.serice.impl;

import fund.paul.user.serice.ISysUsersService;
import fund.paul.user.bean.SysUsers;
import fund.paul.common.service.impl.SuperServiceImpl;
import fund.paul.user.mapper.SysUsersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUsersServiceImpl extends SuperServiceImpl<SysUsersMapper, SysUsers> implements ISysUsersService {
    private final Logger logger = LoggerFactory.getLogger(SysUsersServiceImpl.class);

    @Autowired
    private SysUsersMapper SysUsersMapper;

    // 获取指定ID的用户信息
    public SysUsers selectUserById(Long id) {
        logger.info("start select user information: {}", id);
        SysUsers user = SysUsersMapper.selectById(id);
        logger.info("No user found with the ID: {}", id);
        return user;
    }

    // 更新用户信息
    public int updateUser(SysUsers dto, Long currentUserId) {
        SysUsers user = new SysUsers();
        // 将dto中的属性赋值给user实体

        user.setUpdatedTime(System.currentTimeMillis());
        user.setUpdatedBy(currentUserId);

        int result = SysUsersMapper.updateUser(user);
        if (result > 0) {
            logger.info("Successfully updated user information: {}", dto);
        } else {
            logger.warn("Failed to update user information: {}", dto);
        }
        return result;
    }

    // 逻辑删除用户
    public int deleteUserById(SysUsers dto, Long currentUserId) {
        SysUsers user = new SysUsers();
        // 将dto中的属性（主要是id和deleted）赋值给user实体

        user.setUpdatedTime(System.currentTimeMillis());
        user.setUpdatedBy(currentUserId);

        int result = SysUsersMapper.deleteUserById(user);
        if (result > 0) {
            logger.info("Successfully logically deleted the user: {}", dto.getId());
        } else {
            logger.warn("Failed to logically delete the user: {}", dto.getId());
        }
        return result;
    }
}