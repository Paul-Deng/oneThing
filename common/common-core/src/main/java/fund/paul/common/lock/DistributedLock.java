package fund.paul.common.lock;

import fund.paul.common.exception.LockException;
import java.util.concurrent.TimeUnit;

/**
 * 锁的顶级接口
 *
 * @author paul
 * @date 2023/5/25 22:59
 */
public interface DistributedLock {

    /**
     * 获取锁，如果获取不成功则一直等待直到lock被获取
     * @param key 锁的key
     * @param leaseTime 加锁的时间，超过这个时间后锁便自动解锁；
     *                  如果leaseTime为-1，则保持锁定直到显式解锁
     * @param unit {@code leaseTime} 参数的时间单位
     * @param isFair 是否公平锁
     * @return 锁对象
     */
    PaulLock lock(String key, long leaseTime, TimeUnit unit, boolean isFair) throws LockException;

    default PaulLock lock(String key, long leaseTime, TimeUnit unit) throws LockException {
        return this.lock(key, leaseTime, unit, false);
    }
    default PaulLock lock(String key, boolean isFair) throws LockException {
        return this.lock(key, -1, null, isFair);
    }
    default PaulLock lock(String key) throws LockException {
        return this.lock(key, -1, null, false);
    }

    /**
     * 尝试获取锁，如果锁不可用则等待最多waitTime时间后放弃
     * @param key 锁的key
     * @param waitTime 获取锁的最大尝试时间(单位 {@code unit})
     * @param leaseTime 加锁的时间，超过这个时间后锁便自动解锁；
     *                  如果leaseTime为-1，则保持锁定直到显式解锁
     * @param unit {@code waitTime} 和 {@code leaseTime} 参数的时间单位
     * @return 锁对象，如果获取锁失败则为null
     */
    PaulLock tryLock(String key, long waitTime, long leaseTime, TimeUnit unit, boolean isFair) throws LockException;

    default PaulLock tryLock(String key, long waitTime, long leaseTime, TimeUnit unit) throws LockException {
        return this.tryLock(key, waitTime, leaseTime, unit, false);
    }
    default PaulLock tryLock(String key, long waitTime, TimeUnit unit, boolean isFair) throws LockException {
        return this.tryLock(key, waitTime, -1, unit, isFair);
    }
    default PaulLock tryLock(String key, long waitTime, TimeUnit unit) throws LockException {
        return this.tryLock(key, waitTime, -1, unit, false);
    }

    /**
     * 释放锁
     * @param lock 锁对象
     */
    void unlock(Object lock) throws LockException;

    /**
     * 释放锁
     * @param paulLock 锁抽象对象
     */
    default void unlock(PaulLock paulLock) throws LockException {
        if (paulLock != null) {
            this.unlock(paulLock.getLock());
        }
    }
}
