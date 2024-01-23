package fund.paul.properties;

import cn.hutool.core.collection.ListUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;
import java.util.function.Supplier;

/**
 * mybatis plus 自动填充属性
 *
 * @author paul
 * @date 2023/5/18 00:57
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "paul.mybatis-plus.auto-fill")
@RefreshScope
public class MybatisPlusAutoFillProperties {
    /**
     * 是否开启自动填充字段
     */
    private Boolean enabled = true;

    /**
     * 是否开启了插入填充
     */
    private Boolean enableInsertFill = true;

    /**
     * 是否开启了更新填充
     */
    private Boolean enableUpdateFill = true;

    private List<AutoInsertField> autoInsertFieldList = ListUtil.list(false);

    private List<AutoInsertField> autoUpdatedFieldList = ListUtil.list(false);

    @Setter
    @Getter
    public class AutoInsertField<T> {
        private String fieldName;

        private Supplier<T> supplier;
    }
}
