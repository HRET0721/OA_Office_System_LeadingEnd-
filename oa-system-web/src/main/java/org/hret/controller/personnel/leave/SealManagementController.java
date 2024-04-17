package org.hret.controller.personnel.leave;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.leave.SealManagement;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.impl.leave.SealManagementServicelmpl;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "seal")
@Tag(name = "用章方法", description = "用章方法")
public class SealManagementController {
    private final SealManagementServicelmpl sealManagementServicelmpl;
    @RequestMapping(value = "findSealPaginationList", method = RequestMethod.POST)
    @Operation(summary = "分页查询用章记录方法", description = "分页查询用章记录方法")
    public PageInfo<SealManagement>findSealPaginationList(@RequestBody SealManagement sealManagement){
            return sealManagementServicelmpl.findSealPaginationList(sealManagement);

    }
    @RequestMapping(value = "deleteSeal", method = RequestMethod.POST)
    @Operation(summary = "删除用章方法", description = "删除用章方法")
    public JsonResult deleteSeal(@RequestParam(value = "sealId")Long sealId){
        try {
            return sealManagementServicelmpl.deleteSeal(sealId);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("删除失败");
        }
    }
    @RequestMapping(value = "addSeal", method = RequestMethod.POST)
    @Operation(summary = "新增用章方法", description = "新增用章方法")
    public JsonResult addSeal(@RequestBody SealManagement sealManagement){
        try {
            return sealManagementServicelmpl.addSeal(sealManagement);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("删除失败");
        }
    }
    @RequestMapping(value = "findSeal", method = RequestMethod.POST)
    @Operation(summary = "回显用章方法", description = "回显用章方法")
    public SealManagement findSeal(@RequestParam(value = "sealId")Long sealId){
        return sealManagementServicelmpl.findSeal(sealId);
    }
    @RequestMapping(value = "updateSeal", method = RequestMethod.POST)
    @Operation(summary = "修改用章方法", description = "修改用章方法")
    public JsonResult updateSeal(@RequestBody SealManagement sealManagement){
        try {
            return sealManagementServicelmpl.updateSeal(sealManagement);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("删除失败");
        }
    }
}
