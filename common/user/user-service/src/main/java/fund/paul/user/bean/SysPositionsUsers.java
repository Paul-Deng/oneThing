package fund.paul.user.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import fund.paul.common.basic.BaseModel;

/**
 * 用户与职位的关系表
 *
 * @author paul
 * @date 2024/1/30 02:24
 */
public class SysPositionsUsers extends BaseModel<SysPositionsUsers> {


    @TableId(value = "userId", type = IdType.INPUT)
    private Long userId;

    private Long positionId;

    @TableField(value = "defaultSwitch", fill = FieldFill.INSERT)
    private Integer defaultSwitch; // 默认职位标志：0-否；1-是

    @TableField(value = "status", fill = FieldFill.INSERT)
    private Integer status; // 状态：0-禁用；1-启用
}
