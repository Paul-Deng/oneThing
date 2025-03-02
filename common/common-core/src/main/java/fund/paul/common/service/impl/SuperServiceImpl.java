package fund.paul.common.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fund.paul.common.exception.ExceptionEnum;
import fund.paul.common.exception.IdempotencyException;
import fund.paul.common.exception.LockException;
import fund.paul.common.lock.DistributedLock;
import fund.paul.common.lock.PaulLock;
import fund.paul.common.service.ISuperService;
import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 抽象过后的实现类方法，顶级实现
 *
 * @author paul
 * @date 2023/5/25 23:36
 */
public class SuperServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements ISuperService<T> {
    @Override
    public boolean saveIdempotency(T entity, DistributedLock locker, String lockKey, Wrapper<T> countWrapper, String msg, long leaseTime, long waitTime, TimeUnit timeUnit, boolean isFair) throws LockException {
        if (locker == null) {
            throw new LockException(ExceptionEnum.CONCURRENT_LOCK_NULL);
        }
        if (StrUtil.isEmpty(lockKey)) {
            throw new LockException(ExceptionEnum.CONCURRENT_LOCK_KEY_NULL);
        }
        try (PaulLock lock = locker.tryLock(lockKey, waitTime, leaseTime, TimeUnit.SECONDS, isFair)) {
            if (lock != null) {
                //判断记录是否已存在
                long count = super.count(countWrapper);
                if (count == 0) {
                    return super.save(entity);
                } else {
                    if (StrUtil.isEmpty(msg)) {
                        msg = "existed";
                    }
                    throw new IdempotencyException(msg);
                }
            } else {
                throw new LockException(ExceptionEnum.CONCURRENT_LOCK_TIME_OUT);
            }
        } catch (Exception e) {
            throw new LockException(ExceptionEnum.CONCURRENT_LOCK_FAILED);
        }
    }

    @Override
    public boolean saveOrUpdateIdempotency(T entity, DistributedLock lock, String lockKey, Wrapper<T> countWrapper, String msg) throws LockException {
        if (null != entity) {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            if (null != tableInfo && StrUtil.isNotEmpty(tableInfo.getKeyProperty())) {
                Object idVal = ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty());
                if (StringUtils.checkValNull(idVal) || Objects.isNull(getById((Serializable) idVal))) {
                    if (StrUtil.isEmpty(msg)) {
                        msg = "existed";
                    }
                    return this.saveIdempotency(entity, lock, lockKey, countWrapper, msg);
                } else {
                    return updateById(entity);
                }
            } else {
                throw ExceptionUtils.mpe("Error:  Can not execute. Could not find @TableId.");
            }
        }
        return false;
    }

    @Override
    public boolean saveOrUpdateIdempotency(T entity, DistributedLock lock, String lockKey, Wrapper<T> countWrapper) throws LockException {
        return this.saveOrUpdateIdempotency(entity, lock, lockKey, countWrapper, null);
    }
}
