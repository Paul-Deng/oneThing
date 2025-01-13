package fund.paul.db.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 链式表达式
 *
 * @author paul
 * @date 2023/5/25 23:59
 */
public class CustomLambdaQueryWrapper<T> extends LambdaQueryWrapper<T> {

    public CustomLambdaQueryWrapper<T> likeIfPresent(SFunction<T, ?> column, String val) {
        if (StringUtils.hasText(val)) {
            return (CustomLambdaQueryWrapper<T>) super.like(column, val);
        }
        return this;
    }

    public CustomLambdaQueryWrapper<T> inIfPresent(SFunction<T, ?> column, Collection<?> values) {
        if (!CollectionUtils.isEmpty(values)) {
            return (CustomLambdaQueryWrapper<T>) super.in(column, values);
        }
        return this;
    }

    public CustomLambdaQueryWrapper<T> inIfPresent(SFunction<T, ?> column, Object... values) {
        if (!ObjectUtils.isEmpty(values)) {
            return (CustomLambdaQueryWrapper<T>) super.in(column, values);
        }
        return this;
    }

    public CustomLambdaQueryWrapper<T> eqIfPresent(SFunction<T, ?> column, Object val) {
        if (val != null) {
            return (CustomLambdaQueryWrapper<T>) super.eq(column, val);
        }
        return this;
    }

    public CustomLambdaQueryWrapper<T> gtIfPresent(SFunction<T, ?> column, Object val) {
        if (val != null) {
            return (CustomLambdaQueryWrapper<T>) super.gt(column, val);
        }
        return this;
    }

    public CustomLambdaQueryWrapper<T> betweenIfPresent(SFunction<T, ?> column, Object val1, Object val2) {
        if (val1 != null && val2 != null) {
            return (CustomLambdaQueryWrapper<T>) super.between(column, val1, val2);
        }
        if (val1 != null) {
            return (CustomLambdaQueryWrapper<T>) ge(column, val1);
        }
        if (val2 != null) {
            return (CustomLambdaQueryWrapper<T>) le(column, val2);
        }
        return this;
    }


    @Override
    public CustomLambdaQueryWrapper<T> eq(boolean condition, SFunction<T, ?> column, Object val) {
        super.eq(condition, column, val);
        return this;
    }

    @Override
    public CustomLambdaQueryWrapper<T> in(SFunction<T, ?> column, Collection<?> coll) {
        super.in(column, coll);
        return this;
    }

    @Override
    public CustomLambdaQueryWrapper<T> like(SFunction<T, ?> column, Object val) {
        super.like(column, val);
        return this;
    }

    @Override
    public CustomLambdaQueryWrapper<T> orderByDesc(SFunction<T, ?> column) {
        super.orderByDesc(true, column);
        return this;
    }

    @Override
    public CustomLambdaQueryWrapper<T> orderByAsc(SFunction<T, ?> column) {
        super.orderByAsc(true, column);
        return this;
    }


    @Override
    public CustomLambdaQueryWrapper<T> last(String lastSql) {
        super.last(lastSql);
        return this;
    }

    public boolean isEmpty() {
        return this.getExpression().getNormal().isEmpty()
                && this.getExpression().getGroupBy().isEmpty()
                && this.getExpression().getHaving().isEmpty()
                && this.getExpression().getOrderBy().isEmpty();
    }
}