package fund.paul.common.basic;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * Long主键id
 *
 * @author paul
 * @date 2024/1/30 02:01
 */
@Data
public class LongPKModel<T extends Model<?>> extends BaseModel<T> {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
}
