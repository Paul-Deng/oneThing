package fund.paul.user.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import fund.paul.common.basic.BaseModel;
import lombok.Data;

/**
 * 用户权限
 *
 * @author paul
 * @date 2024/1/30 01:52
 */
@TableName("tbl_user_groups_roles")
@Data
public class SysUserGroupRoles extends BaseModel<SysUserGroupRoles> {
    @TableField("roleId")
    private Long roleId;

    private Long userGroupId;

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

    // 如果需要表示联合主键，则可以添加以下代码（但根据原表结构，此处没有显示联合主键）
    // MyBatis Plus本身不直接支持复合主键，可以通过组合唯一索引或自定义KeyGenerator实现。
    // 若是联合主键：
    // @TableId(type = IdType.COMPOSITE, include={"userGroupId", "roleId"})
    // private UserGroupRoleId id;
    // 其中UserGroupRoleId是一个包含userGroupId和roleId的类。

    // 如果不需要复合主键，MyBatis Plus会默认将userGroupId作为主键字段处理。
}
