package fund.paul;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

/**
 * 数据库自动配置类
 *
 * @author paul
 * @date 2023-05-15 13:17:55
 */
public class DBAutoConfiguration {
    /**
     *
     */
    @ConditionalOnClass(HikariConfig.class)
    public static class DBAutoConfigure {}
}
