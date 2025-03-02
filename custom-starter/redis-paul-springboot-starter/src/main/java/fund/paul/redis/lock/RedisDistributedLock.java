package fund.paul.redis.lock;

import fund.paul.common.exception.ExceptionEnum;
import fund.paul.common.exception.LockException;
import fund.paul.common.lock.DistributedLock;
import fund.paul.common.lock.PaulLock;
import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Component
public class RedisDistributedLock implements DistributedLock {
    
    private RedissonClient redissonClient;

    public RedisDistributedLock(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public PaulLock lock(String key, long leaseTime, TimeUnit unit, boolean isFair) throws LockException {
        RLock lock = isFair ? redissonClient.getFairLock(key) : redissonClient.getLock(key);
        if (leaseTime != -1) {
            lock.lock(leaseTime, unit);
        } else {
            lock.lock();
        }
        return new PaulLock(lock, this);
    }

    @Override
    public PaulLock tryLock(String key, long waitTime, long leaseTime, TimeUnit unit, boolean isFair) throws LockException {
        RLock lock = isFair ? redissonClient.getFairLock(key) : redissonClient.getLock(key);
        boolean locked = false;
        try {
            locked = leaseTime != -1 ?
                    lock.tryLock(waitTime, leaseTime, unit) :
                    lock.tryLock(waitTime, unit);
        } catch (InterruptedException e) {
            throw new LockException(ExceptionEnum.CONCURRENT_LOCK_TIME_OUT);
        }
        return locked ? new PaulLock(lock, this) : null;
    }

    @Override
    public void unlock(Object lock) throws LockException {
        if (lock != null) {
            RLock rLock = (RLock) lock;
            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }
        }
    }
}