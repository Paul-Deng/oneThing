package fund.paul.properties;

import cn.hutool.core.collection.ListUtil;
import java.util.List;
import java.util.function.Supplier;
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

    /**
     * 自动插入的字段
     */
    private List<MybatisAutoFiled> autoInsertFieldList = ListUtil.list(false);

    /**
     * 自动更新的字段
     */
    private List<MybatisAutoFiled> autoUpdatedFieldList = ListUtil.list(false);

    @Setter
    @Getter
    public class MybatisAutoFiled {
        private String fieldName;

        private Supplier<?> supplier;

        private Class<?> classType;


        public MybatisAutoFiled(String fieldName, String initMethod, String valueType) {
            this.fieldName = fieldName;
        }
    }
}
