package fund.paul.user.domain.pojo;

/**
 * 用户组表
 *
 * @author paul
 * @date 2024/1/30 02:10
 */

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import fund.paul.common.basic.LongPKModel;

@TableName("userservice.tbl_user_groups")
public class SysUserGroups extends LongPKModel<SysUserGroups> {

    @TableField("name")
    private String name; // 用户组名称

    @TableField("description")
    private String description; // 用户组描述

    @TableField
    private Integer status; // 状态：0-禁用；1-启用
}
