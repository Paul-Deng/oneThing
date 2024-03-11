package fund.paul.config;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import fund.paul.properties.MybatisPlusAutoFillProperties;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 数据库字段处理
 *
 * @author paul
 * @date 2023/5/18 00:55
 */
public class AutoFillFieldMetaObjectHandler implements MetaObjectHandler {
    private MybatisPlusAutoFillProperties autoFillProperties;

    public AutoFillFieldMetaObjectHandler(MybatisPlusAutoFillProperties autoFillProperties) {
        this.autoFillProperties = autoFillProperties;
    }

    /**
     * 是否开启了插入填充
     */
    @Override
    public boolean openInsertFill() {
        return autoFillProperties.getEnableInsertFill();
    }

    /**
     * 是否开启了更新填充
     */
    @Override
    public boolean openUpdateFill() {
        return autoFillProperties.getEnableUpdateFill();
    }

    /**
     * 插入填充，字段为空自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        List<MybatisPlusAutoFillProperties.MybatisAutoFiled> autoFieldList =
                autoFillProperties.getAutoInsertFieldList();
        autoFieldList.addAll(autoFillProperties.getAutoUpdatedFieldList());
        for (MybatisPlusAutoFillProperties.MybatisAutoFiled fields : autoFieldList) {
            Object fieldVal = getFieldValByName(fields.getFieldName(), metaObject);
            if (ObjectUtil.isEmpty(fieldVal)) {

            }
        }
    }

    MetaObjectHandler customInsertFill(MetaObject metaObject, String fieldName, Class<?> fieldType, Object val) {
        TableInfo tableInfo = this.findTableInfo(metaObject);
        Boolean insertFill = autoFillProperties.getEnableInsertFill();
        return this;
    }

    /**
     * 更新填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        List<MybatisPlusAutoFillProperties.MybatisAutoFiled> autoFieldList = autoFillProperties.getAutoUpdatedFieldList();
        for (MybatisPlusAutoFillProperties.MybatisAutoFiled autoField : autoFieldList) {
            Object fieldVal = getFieldValByName(autoField.getFieldName(), metaObject);
            if (ObjectUtil.isEmpty(fieldVal)) {
                this.strictUpdateFill(metaObject, autoField.getFieldName(), LocalDateTime::now, LocalDateTime.class);
            }
        }
    }
}
