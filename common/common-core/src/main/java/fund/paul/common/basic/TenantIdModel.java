package fund.paul.common.basic;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 租户id模型
 *
 * @author paul
 * @date 2024/3/13 00:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TenantIdModel<T extends Model<?>> extends LongPKModel<T> {

    /**
     * 主键ID
     */
    @TableId
    private Long tenantId;
}
