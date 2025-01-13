package fund.paul.tenant.impl;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import fund.paul.tenant.context.TenantContextHolder;
import fund.paul.tenant.properties.MybatisPlusTenantProperties;
import java.util.HashSet;
import java.util.Set;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

/**
 * 基于 MyBatis Plus 多租户的功能，实现 DB 层面的多租户的功能
 *
 * @author paul
 * @date 2024/3/13 01:15
 */
public class MyBatisTenantHandler implements TenantLineHandler {

    private final Set<String> ignoreTables = new HashSet<>();

    private final MybatisPlusTenantProperties mybatisPlusTenantProperties;

    /**
     * 构造器
     *
     * @param mybatisPlusTenantProperties 租户的配置
     */
    public MyBatisTenantHandler(MybatisPlusTenantProperties mybatisPlusTenantProperties) {
        this.mybatisPlusTenantProperties = mybatisPlusTenantProperties;
        // 不同 DB 下，大小写的习惯不同，所以需要都添加进去
        mybatisPlusTenantProperties.getTableNames().forEach(table -> {
            ignoreTables.add(table.toLowerCase());
            ignoreTables.add(table.toUpperCase());
        });
    }

    @Override
    public Expression getTenantId() {
        return new LongValue(TenantContextHolder.getRequiredTenantId());
    }

    @Override
    public String getTenantIdColumn() {
        return mybatisPlusTenantProperties.getFieldName();
    }

    @Override
    public boolean ignoreTable(String tableName) {

        return !ignoreTables.contains(tableName);
    }
}
