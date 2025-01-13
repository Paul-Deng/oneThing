package fund.paul.tenant.context;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 租号上下文
 *
 * @author paul
 * @date 2024/3/15 00:59
 */
public class TenantContextHolder {

    /**
     * 当前租户编号
     */
    private static final ThreadLocal<Long> TENANT_ID = new TransmittableThreadLocal<>();

    /**
     * 是否忽略租户
     */
    private static final ThreadLocal<Boolean> IGNORE = new TransmittableThreadLocal<>();

    /**
     * 获得租户编号
     *
     * @return 租户编号
     */
    public static Long getTenantId() {
        return TENANT_ID.get();
    }

    /**
     * 获得租户编号。如果不存在，则抛出 NullPointerException 异常
     *
     * @return 租户编号
     */
    public static Long getRequiredTenantId() {
        Long tenantId = getTenantId();
        if (tenantId == null) {
            throw new NullPointerException("TenantContextHolder get tenant id failed");
        }
        return tenantId;
    }

    /**
     * 设置租户id
     *
     * @param tenantId 租户id
     */
    public static void setTenantId(Long tenantId) {
        TENANT_ID.set(tenantId);
    }

    /**
     * 是否需要忽略
     *
     * @param ignore 忽略
     */
    public static void setIgnore(Boolean ignore) {
        IGNORE.set(ignore);
    }

    /**
     * 移除上下文
     */
    public static void clear() {
        TENANT_ID.remove();
        IGNORE.remove();
    }
}
