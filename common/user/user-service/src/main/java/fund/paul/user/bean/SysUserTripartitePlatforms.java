package fund.paul.user.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import fund.paul.common.basic.BaseModel;

/**
 * 支持第三方登录
 *
 * @author paul
 * @date 2024/1/30 01:58
 */
@TableName("userservice.tbl_user_third_party")
public class SysUserTripartitePlatforms extends BaseModel<SysUserTripartitePlatforms> {

    @TableField("userId")
    private Long userId; // 假设这是外键关联到tbl_users表的id字段

    @TableField("providerId")
    private Long providerId;

    @TableField("openId")
    private String openId;
}