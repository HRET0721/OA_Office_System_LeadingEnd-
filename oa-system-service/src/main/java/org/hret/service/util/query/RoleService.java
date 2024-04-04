package org.hret.service.util.query;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.utils.query.Role;
import org.hret.pojo.JsonResult;

/**
 * @author HRET Milky Way
 * @version 1.0
 * Date:2024/4/1
 * description TODO
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户id查询角色以及权限信息
     * @param userId 用户id
     */
    Role getAuthorizationRole(String userId);

    /**
     * 查询角色
     * @param role 查询条件
     * @return 查询结果
     */
    PageInfo<Role> findByRoles(Role role);

    /**
     * 添加角色
     * @param role 角色信息
     * @return 添加结果
     */
    JsonResult addRole(Role role);

    /**
     * 更新角色
     * @param role 角色信息
     * @return 修改结果
     */
    JsonResult updateRole(Role role);

    /**
     * 删除角色
     * @param roleId 角色id
     * @return 删除结果
     */
    JsonResult deleteRole(Integer[] roleId);

}
