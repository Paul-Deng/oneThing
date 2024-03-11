package fund.paul.user.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import fund.paul.common.basic.BaseModel;

/**
 * 用户组角色关系表
 *
 * @author paul
 * @date 2024/1/30 02:37
 */
public class SysUserGroupsRoles extends BaseModel<SysUserGroupRoles> {

    @TableId(value = "userGroupId", type = IdType.INPUT)
    private Long userGroupId; // 自定义复合主键类

    private Long roleId; // 角色id

    @TableField(value = "status", fill = FieldFill.INSERT)
    private Integer status; // 状态：0-禁用；1-启用
}
