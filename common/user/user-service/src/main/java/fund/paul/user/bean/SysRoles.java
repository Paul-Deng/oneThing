package fund.paul.user.bean;

import fund.paul.common.basic.LongPKModel;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import lombok.Data;

/**
 * 系统角色类
 *
 * @author paul
 * @date 2024/1/23 13:10
 */
@Data
public class SysRoles extends LongPKModel<SysRoles> {

    @Serial
    private static final long serialVersionUID = 6639472247341229625L;
    /**
     * 角色名称，非空字符串
     */
    @NotNull
    private String name;

    // 状态：0-禁用；1-启用
    private boolean status;

    /**
     * 角色描述，可选的长字符串
     */
    private String description;
}