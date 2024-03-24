package org.hret.controller.util.query;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.utils.query.Dept;
import org.hret.service.util.query.DeptService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
