package fund.paul.user.domain.mapper;

import fund.paul.user.domain.pojo.SysUser;
import fund.paul.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author paul
 * @date 2024/1/23 01:53
 */
@Mapper
public interface SysUserMapper extends SuperMapper<SysUser> {
    // 更新用户信息，除了密码以外的基本信息可以修改
    @Update({"UPDATE public.tbl_users SET nickname = #{nickname}, gender = #{gender}, email = #{email}, phone = #{phone}, " +
            "avatar = #{avatar}, updatedTime = #{updatedTime}, updatedBy = #{updatedBy} WHERE id = #{id}"})
    int updateUser(SysUser user);

    // 对用户进行逻辑删除，将deleted字段设置为1
    @Update({"UPDATE public.tbl_users SET deleted = #{deleted}, updated_time = #{updatedTime}, updated_by = #{updatedBy} WHERE id = #{id}"})
    int deleteUserById(SysUser user);
}
