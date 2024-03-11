package fund.paul.user.bean;

import fund.paul.common.basic.BaseModel;
import lombok.Data;

/**
 * @author paul
 * @date 2024/1/23 13:28
 */
@Data
public class SysUsersRoles extends BaseModel<SysUsersRoles> {
    // 用户ID
    private Integer userId;

    // 角色ID
    private Integer roleId;
}