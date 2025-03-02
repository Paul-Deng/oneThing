package fund.paul.user.domain.pojo;

import fund.paul.common.basic.BaseModel;
import lombok.Builder;
import lombok.Data;

/**
 * @author paul
 * @date 2024/1/23 13:28
 */
@Builder
@Data
public class SysUsersRoles extends BaseModel<SysUsersRoles> {
    // 用户ID
    private Integer userId;

    // 角色ID
    private Integer roleId;
}