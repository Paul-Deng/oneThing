package fund.paul.user.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import fund.paul.common.basic.TenantIdModel;
import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author paul
 * @date 2024/1/23 01:46
 */
@Data
@TableName("tbl_users")
@NoArgsConstructor
@AllArgsConstructor
public class SysUser extends TenantIdModel<SysUser> {

    @Serial
    private static final long serialVersionUID = 6575103716313132655L;

    // 用户名，唯一标识用户的登录名
    private String username;

    // 密码，通常进行加密存储，此处仅为示例
    private String password;

    // 昵称，用户的显示名称
    private String nickname;

    // 性别，0-未知；1-男；2-女
    private Integer gender;

    // 邮箱地址
    private String email;

    // 手机号码
    private String phone;

    // 头像URL
    private String avatar;

    // 用户状态，0-启用；1-禁用
    private Integer status;
}
