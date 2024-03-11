package fund.paul.user.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import fund.paul.common.basic.LongPKModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统职位表
 *
 * @author paul
 * @date 2024/1/30 02:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysPositions extends LongPKModel<SysPositions> {

    @TableField("name")
    private String name; // 职位名称

    @TableField("description")
    private String description; // 职位描述

    /**
     *  所属组织的id
     */
    private String orgId;

    @TableField
    private Integer status; // 状态：0-禁用；1-启用
}
