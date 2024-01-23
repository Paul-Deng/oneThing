package fund.paul.common.basic;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 分页实体类
 *
 * @author paul
 * @date 2023/5/18 12:57
 */
public class PageResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -3200176714967829940L;

    /**
     * 总数
     */
    private Long count;
    /**
     * 是否成功：0 成功、1 失败
     */
    private int code;
    /**
     * 当前页结果集
     */
    private List<T> data;
}
