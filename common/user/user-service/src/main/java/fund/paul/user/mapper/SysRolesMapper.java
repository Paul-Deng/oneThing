package fund.paul.user.mapper;

import fund.paul.db.mapper.SuperMapper;
import fund.paul.user.bean.SysRoles;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author paul
 * @date 2024/1/23 13:16
 */
@Mapper
public interface SysRolesMapper extends SuperMapper<SysRoles> {

    @Update({"UPDATE public.tbl_roles SET name = #{name}, description = #{description}, status = #{status} WHERE id = #{id}"})
    int updateRole(@Param("role") SysRoles role);

    @Update({"UPDATE public.tbl_roles SET deleted = 1, status = #{status}, updated_time = #{updatedTime}, updated_by = #{updatedBy} WHERE id = #{id}"})
    int deleteOrDisableRole(@Param("role") SysRoles role);

    @Select("SELECT * FROM public.tbl_roles WHERE deleted = 0")
    List<SysRoles> getAllRoles();

    // 如果需要批量查询指定ID的角色信息，可以添加如下方法：
    @Select("<script>" +
            "SELECT * FROM public.tbl_roles WHERE deleted = 0 AND id IN " +
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    List<SysRoles> getRolesByIds(@Param("ids") List<Long> ids);
}