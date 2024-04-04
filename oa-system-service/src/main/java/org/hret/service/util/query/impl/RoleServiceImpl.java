package org.hret.service.util.query.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.hret.entity.utils.Menu;
import org.hret.entity.utils.query.Role;
import org.hret.entity.utils.query.RoleMenu;
import org.hret.entity.utils.query.User;
import org.hret.entity.utils.query.UserRole;
import org.hret.mapper.util.MenuMapper;
import org.hret.mapper.util.query.RoleMapper;
import org.hret.mapper.util.query.RoleMenuMapper;
import org.hret.mapper.util.query.UserMapper;
import org.hret.mapper.util.query.UserRoleMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.util.query.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author HRET Milky Way
 * @version 1.0
 * Date:2024/4/1
 * description TODO
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleMenuMapper roleMenuMapper;
    private final UserRoleMapper userRoleMapper;
    private final UserMapper userMapper;
    private final MenuMapper menuMapper;

    /**
     * 封装角色关联信息
     * @param roles 角色集合
     * @return 封装后的角色集合
     */
    public List<Role> getAllRoles(List<Role> roles) {
        // 遍历角色集合
        roles.forEach(role -> {
            // 根据角色id查询所关联的用户id
            List<UserRole> userRoles = userRoleMapper.selectList(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getRoleId, role.getRoleId()));
            List<User> users = new ArrayList<>();
            userRoles.forEach(userRole -> {
                users.add(userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserId, userRole.getUserId())));
            });
            role.setUsers(users);

            // 判断是否为超级管理员 注：超级管理员拥有所有权限
            if ( role.getRoleId() != 1 ) {
                // 不为超级管理员查询所拥有的权限
                // 根据角色id查询所关联的权限id
                List<RoleMenu> roleMenus = roleMenuMapper.selectList(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRoleId, role.getRoleId()));
                // 创建权限集合
                List<Menu> menus = new ArrayList<>();
                roleMenus.forEach(roleMenu -> {
                    menus.add(menuMapper.selectOne(Wrappers.<Menu>lambdaQuery().eq(Menu::getMenuId, roleMenu.getMenuId())));
                });
                // 将权限集合添加到角色中
                role.setMenus(menus);
            }
        });

        // 返回封装后的角色集合
        return roles;
    }

    @Override
    public Role getAuthorizationRole(String userId) {

        // 根据用户id查询角色id
        UserRole userRoles = userRoleMapper.selectOne(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId));

        // 创建角色
        Role role = null;

        // 判断是否查询到角色id
        if ( userRoles.getRoleId() != null ) {
            // 查询角色
            role = this.getById(userRoles.getRoleId());
            if ( role != null ) {
                role.setMenus(menuMapper.selectList(Wrappers.<Menu>lambdaQuery().in(Menu::getMenuId, roleMenuMapper.selectList(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRoleId, role.getRoleId())).stream().map(RoleMenu::getMenuId).toArray())));
            }
        }

        // 返回角色
        return role;
    }

    @Override
    public PageInfo<Role> findByRoles(Role role) {

        // 添加分页参数
        PageHelper.startPage(role.getPageNum(), role.getPageSize());

        // 创建queryWrapper对象
        QueryWrapper<Role> wrapper = new QueryWrapper<>();

        // 判断查询条件
        if (StringUtils.hasText(role.getRoleName())) {
            // 添加查询条件
            wrapper.like("role_name", role.getRoleName());
        }

        List<Role> list = getAllRoles(this.list(wrapper));

        return new PageInfo<>(list);

    }

    @Override
    public JsonResult addRole(Role role) {

        // 添加角色
        boolean save = this.save(role);
        // 判断添加结果
        if (save) {
            // 判断是否有添加权限
            if ( role.getMenus() != null && !role.getMenus().isEmpty()) {
                // 添加权限
                role.getMenus().forEach(menu -> {
                    roleMenuMapper.insert(new RoleMenu(role.getRoleId(), Math.toIntExact(menu.getMenuId())));
                });
            }
        } else {
            return JsonResult.error("添加角色失败");
        }

        return JsonResult.ok("添加角色成功");

    }

    @Override
    public JsonResult updateRole(Role role) {

        // 更新角色
        boolean update = this.updateById(role);

        if (update) {
            // 判断是否有添加权限
            if ( role.getMenus() != null && !role.getMenus().isEmpty() ) {
                // 删除角色权限
                roleMenuMapper.delete(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRoleId, role.getRoleId()));

                // 添加权限
                role.getMenus().forEach(menu -> {
                    roleMenuMapper.insert(new RoleMenu(role.getRoleId(), Math.toIntExact(menu.getMenuId())));
                });
            }
        }else{
            return JsonResult.error("更新角色失败");
        }

        return JsonResult.ok("更新角色成功");
    }

    @Override
    public JsonResult deleteRole(Integer[] roleId) {

        // 删除角色
        int remove = this.baseMapper.deleteBatchIds(Arrays.asList(roleId));

        if (remove > 0) {
            // 删除角色权限
            roleMenuMapper.deleteBatchIds(Arrays.asList(roleId));
        }else {
            return JsonResult.error("删除角色失败");
        }

        return JsonResult.ok("删除角色成功");
    }
}
