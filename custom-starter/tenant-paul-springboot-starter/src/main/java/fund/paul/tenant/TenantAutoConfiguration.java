package fund.paul.tenant;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import fund.paul.db.utils.MyBatisPlusUtils;
import fund.paul.tenant.impl.MyBatisTenantHandler;
import fund.paul.tenant.properties.MybatisPlusTenantProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 租户自动配置类
 *
 * @author paul
 * @date 2024/3/15 00:49
 */
@EnableConfigurationProperties({MybatisPlusTenantProperties.class})
public class TenantAutoConfiguration {
    private MybatisPlusTenantProperties mybatisPlusTenantProperties;

    private MybatisPlusInterceptor mybatisPlusInterceptor;

    public TenantAutoConfiguration(MybatisPlusTenantProperties mybatisPlusTenantProperties, MybatisPlusInterceptor mybatisPlusInterceptor) {
        this.mybatisPlusTenantProperties = mybatisPlusTenantProperties;
        this.mybatisPlusInterceptor = mybatisPlusInterceptor;
    }


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "paul.tenant", name = "enabled", havingValue = "true", matchIfMissing = true)
    public TenantLineInnerInterceptor tenantHandler() {
        TenantLineInnerInterceptor inner = new TenantLineInnerInterceptor(new MyBatisTenantHandler(mybatisPlusTenantProperties));
        // 添加到 interceptor 中
        // 需要加在首个，主要是为了在分页插件前面。这个是 MyBatis Plus 的规定
        MyBatisPlusUtils.addInterceptor(mybatisPlusInterceptor, inner, 0);
        return inner;
    }
}
