package fund.paul.user.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import fund.paul.common.basic.BaseModel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.List;
import java.util.Set;

/**
 * 定义权限表
 *
 * @author paul
 * @date 2023/5/25 23:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_permissions")
public class SysPermissions extends BaseModel {

    @Serial
    private static final long serialVersionUID = 749360940290141180L;

    /**
     * 父类id
     */
    @NotNull
    private Long parentId;

    /**
     * 菜单名字
     */
    @NotNull
    private String name;

    /**
     * 样式
     */
    private String css;

    /**
     * 路径
     */
    private String path;

    /**
     * 请求的类型
     */
    private Integer method;

    /**
     * 请求的地址
     */
    @NotNull
    private String url;

    /**
     * 排序顺序
     */
    private Integer sort;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 是否隐藏
     */
    private Boolean hidden;

    /**
     * 状态
     */
    private int status;

    @TableField(exist = false)
    private List<SysPermissions> subMenus;

    @TableField(exist = false)
    private Long roleId;

    @TableField(exist = false)
    private Set<Long> menuIds;
}