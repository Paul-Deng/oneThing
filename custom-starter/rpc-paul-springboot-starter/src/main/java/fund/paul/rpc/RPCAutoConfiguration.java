package fund.paul.rpc;

import fund.paul.rpc.properties.TraceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author paul
 * @date 2024/3/30 23:57
 */

@ComponentScan
@EnableConfigurationProperties({TraceProperties.class})
public class RPCAutoConfiguration {
}
