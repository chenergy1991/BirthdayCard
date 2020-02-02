package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.model.Admin;

import java.util.List;

/**
 *Admin Mapper接口
 * @author Jake J.Chen
 * @date 2019/11/10
 */
@Mapper
public interface AdminMapper {
    /**
     *增加管理员对象
     *
     * @param admin 管理员对象
     * @return int
     */
    int addAdmin(Admin admin);

    /**
     *通过ID删除管理员信息
     *
     * @param id 管理员ID
     * @return int
     */
    int deleteAdminById(Integer id);

    /**
     *更新管理员对象
     *
     * @param admin 管理员对象
     * @return int
     */
    int updateAdminById(Admin admin);

    /**
     *通过ID获得管理员对象
     *
     * @param id 管理员ID
     * @return List <People>
     */
    Admin getAdminById(Integer id);

    /**
     *获得所有管理员的列表
     *
     * @return List <Admin>
     */
    List<Admin> getAllAdmins();

    /**
     *管理员登录
     *
     * @param name 管理员名称
     * @param password 管理员密码
     * @return List <Admin>
     */
    Admin adminLogin(@Param("name") String name, @Param("password") String password);

}
