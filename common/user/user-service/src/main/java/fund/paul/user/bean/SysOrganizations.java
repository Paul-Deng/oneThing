package fund.paul.user.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import fund.paul.common.basic.LongPKModel;
import lombok.Builder;

/**
 * 系统组织表
 *
 * @author paul
 * @date 2024/1/30 02:05
 */
@Builder
@TableName("userservice.tbl_organizations")
public class SysOrganizations extends LongPKModel<SysOrganizations> {

    @TableField("name")
    private String name; // 组织名称

    @TableField("orgType")
    private String orgType; // 组织类型

    @TableField("parentOrgId")
    private Integer parentOrgId; // 上级组织ID，假设这是外键关联到同一张表的id字段

    @TableField("description")
    private String description; // 组织描述或备注

    @TableField
    private Integer status; // 状态：0-禁用；1-启用
}