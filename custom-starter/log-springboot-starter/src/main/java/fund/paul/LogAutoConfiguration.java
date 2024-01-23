package fund.paul;

import fund.paul.properties.TraceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * 自动配置类
 *
 * @author paul
 * @date 2023/5/24 13:25
 */

@ComponentScan
@EnableConfigurationProperties({TraceProperties.class})
public class LogAutoConfiguration {
}