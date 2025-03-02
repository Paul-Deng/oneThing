package fund.paul.common.basic;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 分页实体类
 *
 * @author paul
 * @date 2023/5/18 12:57
 */
@Builder
@Setter
@Getter
public class PageResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -3200216714967829940L;

    /**
     * 总数
     */
    private Long total;

    /**
     * 当前页结果集
     */
    private List<T> data;
}
