package fund.paul.common.lock;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 分布式锁对象
 *
 * @author paul
 * @date 2023/5/25 23:00
 */
@AllArgsConstructor
public class PaulLock implements AutoCloseable {
    @Getter
    private final Object lock;

    private final DistributedLock locker;

    @Override
    public void close() throws Exception {
        locker.unlock(lock);
    }
}
