package fund.paul.common.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import fund.paul.common.exception.LockException;
import fund.paul.common.lock.DistributedLock;
import java.util.concurrent.TimeUnit;

/**
 * 顶级父类抽象数据
 *
 * @author paul
 * @date 2023/5/25 23:35
 */
public interface ISuperService <T> extends IService<T> {

    /**
     * 幂等性新增记录
     * 例子如下：
     * String username = sysUser.getUsername();
     * boolean result = super.saveIdempotency(sysUser, lock
     *                 , LOCK_KEY_USERNAME+username
     *                 , new QueryWrapper<SysUser>().eq("username", username));
     *
     * @param entity       实体对象
     * @param locker       锁实例
     * @param lockKey      锁的key
     * @param countWrapper 判断是否存在的条件
     * @param msg          对象已存在提示信息
     * @param leaseTime     释放时间
     * @param waitTime      等待时间
     * @param timeUnit      时间单位
     * @param isFair        是否公平
     * @return 是否存在
     */
    boolean saveIdempotency(T entity, DistributedLock locker, String lockKey, Wrapper<T> countWrapper, String msg, long leaseTime, long waitTime, TimeUnit timeUnit, boolean isFair) throws LockException;

    default boolean saveIdempotency(T entity, DistributedLock locker, String lockKey, Wrapper<T> countWrapper, String msg) throws LockException {
        return this.saveIdempotency(entity, locker, lockKey, countWrapper, msg, -1, 3, TimeUnit.SECONDS, false);
    }

    default boolean saveIdempotency(T entity, DistributedLock locker, String lockKey, Wrapper<T> countWrapper) throws LockException {
        return this.saveIdempotency(entity, locker, lockKey, countWrapper, null);
    }

    /**
     * 幂等性新增或更新记录
     * 例子如下：
     * String username = sysUser.getUsername();
     * boolean result = super.saveOrUpdateIdempotency(sysUser, lock
     *                 , LOCK_KEY_USERNAME+username
     *                 , new QueryWrapper<SysUser>().eq("username", username));
     *
     * @param entity       实体对象
     * @param locker       锁实例
     * @param lockKey      锁的key
     * @param countWrapper 判断是否存在的条件
     * @param msg          对象已存在提示信息
     * @return 是否存在
     */
    boolean saveOrUpdateIdempotency(T entity, DistributedLock locker, String lockKey, Wrapper<T> countWrapper, String msg) throws LockException;

    boolean saveOrUpdateIdempotency(T entity, DistributedLock locker, String lockKey, Wrapper<T> countWrapper) throws LockException;
}
