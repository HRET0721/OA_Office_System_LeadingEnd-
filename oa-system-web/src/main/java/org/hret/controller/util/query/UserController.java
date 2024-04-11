package org.hret.controller.util.query;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.hret.entity.utils.query.Role;
import org.hret.entity.utils.query.User;
import org.hret.entity.utils.query.UserRole;
import org.hret.mapper.util.query.UserRoleMapper;
import org.hret.pojo.GetCaptcha;
import org.hret.pojo.JsonResult;
import org.hret.service.util.query.RoleService;
import org.hret.service.util.query.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HRET Milky Way
 * @version 1.0
 * Date:2024/4/9
 * description TODO
 */
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Tag(name = "用户模块", description = "用户模块")
public class UserController {

    private UserService userService;
    private UserRoleMapper userRoleMapper;
    private RoleService roleService;

    @RequestMapping(value = "getCaptcha", method = RequestMethod.POST)
    @Operation(summary = "获取验证码验证状态", description = "获取验证码验证状态")
    public JsonResult getCaptcha(@RequestParam(value = "captchaVerifyParam") String captchaVerifyParam) {
        JsonResult captchaValidate = GetCaptcha.getCaptchaValidate(captchaVerifyParam);
        return captchaValidate;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Operation(summary = "用户登录", description = "用户登录")
    public JsonResult userLogin(@RequestBody User user) {
        // TODO: 用户登录

        // TODO: 验证用户账号
        List<User> list = userService.list(Wrappers.<User>lambdaQuery().eq(User::getUserAccount, user.getUserAccount()));
        if ( list.isEmpty() ) {
            // TODO: 用户账号验证失败使用手机号登录
            list = userService.list(Wrappers.<User>lambdaQuery().eq(User::getUserPhone, user.getUserAccount()));
        }

        // TODO: 验证是否有该用户
        if ( !list.isEmpty() ) {
            // TODO: 验证用户密码
            for (User data : list) {
                if ( data.getUserPassword().equals(user.getUserPassword()) ){
                    // 获取关联的角色id
                    UserRole userRoles = userRoleMapper.selectOne(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, data.getUserId()));
                    // 根据角色id查询信息并存入用户信息中
                    data.setRole(roleService.getOne(Wrappers.<Role>lambdaQuery().eq(Role::getRoleId, userRoles.getRoleId())));
                    // TODO: 用户登录成功
                    return JsonResult.ok("登录成功", data);
                }
            }
        }

        // TODO: 用户登录失败
        return JsonResult.error("用户名或密码错误");
    }

}
