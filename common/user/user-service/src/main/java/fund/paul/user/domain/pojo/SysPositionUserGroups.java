package fund.paul.user.domain.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import fund.paul.common.basic.BaseModel;
import lombok.Builder;

/**
 * 职位与用户组的关系
 *
 * @author paul
 * @date 2024/1/30 02:32
 */
@Builder
public class SysPositionUserGroups extends BaseModel<SysPositionUserGroups> {
    @TableId(value = "positionId", type = IdType.INPUT)
    private Long positionId;

    @TableId(value = "userGroupId", type = IdType.INPUT)
    private Long userGroupId;

    @TableField(value = "defaultSwitch", fill = FieldFill.INSERT)
    private Integer defaultSwitch; // 默认用户组标志：0-否；1-是

    @TableField(value = "status", fill = FieldFill.INSERT)
    private Integer status; // 状态：0-禁用；1-启用
}
