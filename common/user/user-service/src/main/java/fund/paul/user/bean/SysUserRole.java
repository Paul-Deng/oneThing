package fund.paul.user.bean;

import fund.paul.common.basic.BaseModel;
import lombok.Data;

/**
 * @author paul
 * @date 2024/1/23 13:28
 */
@Data
public class SysUserRole extends BaseModel<SysUserRole> {
    // 用户ID
    private Integer userId;

    // 角色ID
    private Integer roleId;

    // 状态：0-禁用；1-启用
    private boolean status;
}