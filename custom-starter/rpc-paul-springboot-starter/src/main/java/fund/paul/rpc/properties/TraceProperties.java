package fund.paul.rpc.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 链路追踪配置列表
 *
 * @author paul
 * @date 2023/5/24 13:23
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "paul.trace")
@RefreshScope
public class TraceProperties {

    /**
     * 是否开启日志链路追踪
     */
    private Boolean enable = false;
}
