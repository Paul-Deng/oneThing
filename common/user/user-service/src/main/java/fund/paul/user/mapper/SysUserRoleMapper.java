package fund.paul.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fund.paul.user.bean.SysUserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author paul
 * @date 2024/1/23 13:30
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    @Insert({"INSERT INTO public.tbl_user_roles(userId, roleId, deleted, status, created_time, updated_time, updated_by, created_by) " +
            "VALUES(#{userId}, #{roleId}, #{deleted}, #{status}, #{createdTime}, #{updatedTime}, #{updatedBy}, #{createdBy})"})
    int insertUserRole(SysUserRole userRole);

    @Delete("DELETE FROM public.tbl_user_roles WHERE userId = #{userId} AND roleId = #{roleId}")
    int deleteUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    @Update({"UPDATE public.tbl_user_roles SET deleted = #{deleted}, status = #{status}, updated_time = #{updatedTime}, updated_by = #{updatedBy} " +
            "WHERE userId = #{userId} AND roleId = #{roleId}"})
    int updateUserRoleStatus(SysUserRole userRole);

    @Select("SELECT * FROM public.tbl_user_roles WHERE userId = #{userId} AND deleted = 0")
    List<SysUserRole> getUserRolesByUserId(Integer userId);
}