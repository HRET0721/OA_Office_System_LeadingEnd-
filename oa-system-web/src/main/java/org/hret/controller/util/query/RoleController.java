package org.hret.controller.util.query;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.utils.query.Role;
import org.hret.pojo.JsonResult;
import org.hret.service.util.query.RoleService;
import org.springframework.web.bind.annotation.*;

/**
 * @author HRET Milky Way
 * @version 1.0
 * Date:2024/4/2
 * description TODO
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "role")
@Tag(name = "角色模块", description = "角色模块")
public class RoleController {

    private final RoleService roleService;

    @RequestMapping(value = "/getAuthorizationRole", method = RequestMethod.POST)
    @Operation(summary = "查询角色权限", description = "查询角色权限")
    public JsonResult getAuthorizationRole(@RequestParam(value = "roleId") String roleId, @RequestParam(value = "path") String path) {
        return roleService.getAuthorizationRole(roleId, path);
    }

    @RequestMapping(value = "/findByRoles")
    @Operation(summary = "查询角色信息", description = "查询角色信息")
    public PageInfo<Role> findByRoles(@RequestBody Role role) {
        return roleService.findByRoles(role);
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    @Operation(summary = "添加角色信息", description = "添加角色信息")
    public JsonResult addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    @Operation(summary = "修改角色信息", description = "修改角色信息")
    public JsonResult updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    @Operation(summary = "删除角色信息", description = "删除角色信息")
    public JsonResult deleteRole(@RequestParam(value = "roleIds") Integer[] roleIds) {
        return roleService.deleteRole(roleIds);
    }

}
