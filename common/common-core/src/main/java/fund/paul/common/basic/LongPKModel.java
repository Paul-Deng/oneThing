package fund.paul.common.basic;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Long主键id
 *
 * @author paul
 * @date 2024/1/30 02:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LongPKModel<T extends Model<?>> extends BaseModel<T> {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
}
