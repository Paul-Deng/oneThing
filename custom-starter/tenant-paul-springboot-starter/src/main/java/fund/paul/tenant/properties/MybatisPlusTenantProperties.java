package fund.paul.tenant.properties;

import cn.hutool.core.collection.CollUtil;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * mybatis plus 自动填充属性
 *
 * @author paul
 * @date 2023/5/18 00:57
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "paul.mybatis-plus.tenant")
@RefreshScope
public class MybatisPlusTenantProperties {
    /**
     * 需要注册租户的表。
     */
    private Set<String> tableNames = CollUtil.newHashSet();

    /**
     * 租户字段
     */
    private String fieldName;
}
