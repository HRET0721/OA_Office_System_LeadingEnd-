package org.hret.controller.util.query;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.utils.query.Dept;
import org.hret.service.util.query.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author:HRET Milky Way
 * Date:2024/3/24
 * version:1.0
 * @author HRET
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/dept")
@Tag(name = "部门模块", description = "部门模块")
public class DeptController {

    private final DeptService deptService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/findDeptUser", method = RequestMethod.GET)
    @Operation(summary = "查询部门员工信息", description = "查询部门员工信息")
    public List<Dept> findDeptUser() {

        // 从缓存中获取数据
        Object deptList = redisTemplate.opsForValue().get("deptList");

        // 判断redis中是否有数据
        if (!ObjectUtils.isEmpty(deptList)) {
            // 如果有数据则直接返回
            return (List<Dept>) deptList;
        }

        // 没有数据则从数据库中查询
        List<Dept> deptUser = deptService.findDeptUser();

        // 将数据存入redis中
        redisTemplate.opsForValue().set("deptList", deptUser);

        return deptUser;
    }

    @RequestMapping(value = "/findDeptByUserName", method = RequestMethod.GET)
    @Operation(summary = "根据员工姓名查询部门", description = "根据员工姓名查询部门")
    public List<Dept> findDeptByUserName(@RequestParam(value = "userName") String userName) {
        return deptService.findDeptByUserName(userName);
    }

}
