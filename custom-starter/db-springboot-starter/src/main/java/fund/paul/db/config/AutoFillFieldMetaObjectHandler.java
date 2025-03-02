package fund.paul.db.config;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import fund.paul.common.constant.Constants;
import fund.paul.db.properties.MybatisPlusAutoFillProperties;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

/**
 * 数据库字段处理
 *
 * @author paul
 * @date 2023/5/18 00:55
 */
public class AutoFillFieldMetaObjectHandler implements MetaObjectHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(AutoFillFieldMetaObjectHandler.class);

    private final MybatisPlusAutoFillProperties autoFillProperties;

    private static final Map<String, ClassMethodParams> METHOD_CACHE = new ConcurrentHashMap<>();

    static {
        MybatisPlusAutoFillProperties.MybatisAutoFiled createdTime = new MybatisPlusAutoFillProperties.MybatisAutoFiled();
        createdTime.setFieldName(Constants.Col.CREATED_TIME);
        createdTime.setClassName(System.class.getName());
        createdTime.setMethodName("currentTimeMillis");
        MybatisPlusAutoFillProperties.MybatisAutoFiled updatedTime = new MybatisPlusAutoFillProperties.MybatisAutoFiled();
        BeanUtils.copyProperties(createdTime, updatedTime);
        updatedTime.setFieldName(Constants.Col.UPDATED_TIME);
        ClassMethodParams build = ClassMethodParams.builder().build();
        try {
            Method currentTimeMillis = System.class.getDeclaredMethod("currentTimeMillis");
            build.setMethod(currentTimeMillis);
            build.setFieldType(Long.class);
            METHOD_CACHE.put(constructKey(createdTime), build);
            METHOD_CACHE.put(constructKey(updatedTime), build);
        } catch (NoSuchMethodException e) {

        }
    }


    public AutoFillFieldMetaObjectHandler(MybatisPlusAutoFillProperties autoFillProperties) {
        this.autoFillProperties = autoFillProperties;
    }

    /**
     * 是否开启了插入填充
     */
    @Override
    public boolean openInsertFill(MappedStatement mappedStatement) {
        return autoFillProperties.getEnableInsertFill();
    }

    /**
     * 是否开启了更新填充
     */
    @Override
    public boolean openUpdateFill(MappedStatement mappedStatement) {
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
        for (MybatisPlusAutoFillProperties.MybatisAutoFiled autoField : autoFieldList) {
            Object fieldVal = getFieldValByName(autoField.getFieldName(), metaObject);
            if (ObjectUtil.isEmpty(fieldVal)) {
                doCustomFill(metaObject, autoField, this::strictInsertFill);
            }
        }
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
                doCustomFill(metaObject, autoField, this::strictUpdateFill);
            }
        }
    }

    private void doCustomFill(MetaObject metaObject, MybatisPlusAutoFillProperties.MybatisAutoFiled autoField, FourConsumer consumer) {
        if (!StringUtils.hasLength(autoField.getFieldName()) || !StringUtils.hasLength(autoField.getClassName())
                || !StringUtils.hasLength(autoField.getMethodName())) {
            return;
        }

        try {
            Object res = null;
            Class<?> filedType = null;
            if (METHOD_CACHE.containsKey(constructKey(autoField))) {
                ClassMethodParams classMethodParams = METHOD_CACHE.get(constructKey(autoField));
                Method method = classMethodParams.getMethod();
                Object[] params = classMethodParams.getParams();
                filedType = classMethodParams.getFieldType();
                if (ObjectUtil.isEmpty(params)) {
                    res = method.invoke(classMethodParams.instance);
                } else {
                    res = method.invoke(classMethodParams.instance, classMethodParams.params);
                }
            } else {
                Class<?> clazz = Class.forName(autoField.getClassName());
                Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
                declaredConstructor.setAccessible(true);
                Object instance = declaredConstructor.newInstance();
                Method[] declaredMethods = clazz.getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    if (declaredMethod.getName().equals(autoField.getMethodName())) {
                        Class<?>[] paramTypes = declaredMethod.getParameterTypes();
                        ClassMethodParams classMethodParams = ClassMethodParams.builder().method(declaredMethod)
                                .instance(instance).fieldType(declaredMethod.getReturnType()).build();
                        if (paramTypes.length == 0) {
                            // 如果方法无参，直接调用
                            res = declaredMethod.invoke(instance);
                        } else {
                            Object[] parsedParams = parseParams(paramTypes, autoField.getParams());
                            classMethodParams.setParams(parsedParams);
                            res = declaredMethod.invoke(instance, parsedParams);
                        }
                        METHOD_CACHE.put(constructKey(autoField), classMethodParams);
                    }
                }
            }
            this.strictUpdateFill(metaObject, autoField.getFieldName(), (Class<Object>) filedType,  res);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static String constructKey(MybatisPlusAutoFillProperties.MybatisAutoFiled autoField) {
        return String.join(Constants.Characters.AT, autoField.getClassName(), autoField.getMethodName());
    }

    // 参数解析方法：根据方法参数类型解析配置中的参数
    private static Object[] parseParams(Class<?>[] paramTypes, List<String> paramList) {
        Object[] parsedParams = new Object[paramTypes.length];


        for (int i = 0; i < paramTypes.length; i++) {
            String paramTrim = paramList.get(i).trim();
            if (paramTypes[i] == int.class) {
                parsedParams[i] = Integer.parseInt(paramTrim);
            } else if (paramTypes[i] == double.class) {
                parsedParams[i] = Double.parseDouble(paramTrim);
            } else if (paramTypes[i] == String.class) {
                parsedParams[i] = paramTrim;
            } else {
                throw new IllegalArgumentException("Unsupported parameter type: " + paramTypes[i]);
            }
        }

        return parsedParams;
    }

    @Getter
    @Setter
    @Builder
    private static class ClassMethodParams {
        private Object instance;

        private Method method;

        private Class<?> fieldType;

        private Object[] params;
    }

    @FunctionalInterface
    private interface FourConsumer {
        <T, E extends T> void consumer(MetaObject metaObject, String fieldName, Class<T> fieldType, E fieldVal);
    }
}
