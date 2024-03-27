package org.hret.controller.util.query;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.utils.query.Dept;
import org.hret.service.util.query.DeptService;
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

    @RequestMapping(value = "/findDeptUser", method = RequestMethod.GET)
    @Operation(summary = "查询部门员工信息", description = "查询部门员工信息")
    public List<Dept> findDeptUser() {
        return deptService.findDeptUser();
    }

    @RequestMapping(value = "/findDeptByUserName", method = RequestMethod.GET)
    @Operation(summary = "根据员工姓名查询部门", description = "根据员工姓名查询部门")
    public List<Dept> findDeptByUserName(@RequestParam(value = "userName") String userName) {
        return deptService.findDeptByUserName(userName);
    }

}
