package fund.paul.common.basic;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serial;
import lombok.Data;

/**
 * 基础模型
 *
 * @author paul
 * @date 2023/5/18 00:42
 */
@Data
public class BaseModel<T extends Model<?>> extends Model<T> {

    @Serial
    private static final long serialVersionUID = -2523019266771621945L;

    /**
     * 是否被逻辑删除
     */
    @TableLogic
    private int deleted;

    /**
     * 创建时间
     */
    private Long createdTime;

    /**
     * 更新时间
     */
    private Long updatedTime;

    /**
     * 更新
     */
    private Long updatedBy;

    /**
     * 创建
     */
    private Long createdBy;
}
