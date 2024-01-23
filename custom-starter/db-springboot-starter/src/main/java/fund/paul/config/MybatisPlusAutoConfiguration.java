package fund.paul.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import fund.paul.properties.MybatisPlusAutoFillProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 自动装配
 *
 * @author paul
 * @date 2023/5/18 01:51
 */
@EnableConfigurationProperties({MybatisPlusAutoFillProperties.class})
public class MybatisPlusAutoConfiguration {

    private MybatisPlusAutoFillProperties autoFillProperties;

    public MybatisPlusAutoConfiguration(MybatisPlusAutoFillProperties autoFillProperties) {
        this.autoFillProperties = autoFillProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "zlt.mybatis-plus.auto-fill", name = "enabled", havingValue = "true", matchIfMissing = true)
    public MetaObjectHandler metaObjectHandler() {
        return new AutoFillFieldMetaObjectHandler(autoFillProperties);
    }
}
