package fund.paul.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fund.paul.mapper.SuperMapper;
import fund.paul.user.bean.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author paul
 * @date 2024/1/23 13:16
 */
@Mapper
public interface SysRoleMapper extends SuperMapper<SysRole> {

    @Update({"UPDATE public.tbl_roles SET name = #{name}, description = #{description}, status = #{status} WHERE id = #{id}"})
    int updateRole(@Param("role") SysRole role);

    @Update({"UPDATE public.tbl_roles SET deleted = 1, status = #{status}, updated_time = #{updatedTime}, updated_by = #{updatedBy} WHERE id = #{id}"})
    int deleteOrDisableRole(@Param("role") SysRole role);

    @Select("SELECT * FROM public.tbl_roles WHERE deleted = 0")
    List<SysRole> getAllRoles();

    // 如果需要批量查询指定ID的角色信息，可以添加如下方法：
    @Select("<script>" +
            "SELECT * FROM public.tbl_roles WHERE deleted = 0 AND id IN " +
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    List<SysRole> getRolesByIds(@Param("ids") List<Long> ids);
}