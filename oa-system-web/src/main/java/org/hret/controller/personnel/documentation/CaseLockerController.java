package org.hret.controller.personnel.documentation;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.documentation.ArchivesDocumentation;
import org.hret.entity.personnel.documentation.CaseLocker;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.impl.documentation.CaseLockerServicelmpl;
import org.springframework.web.bind.annotation.*;

import java.time.chrono.JapaneseChronology;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
//final注解
@RequiredArgsConstructor
@RequestMapping(value = "locker")
@Tag(name = "案库方法", description = "案库方法")
public class CaseLockerController {
    private final CaseLockerServicelmpl caseLockerServicelmpl;
    @RequestMapping(value = "findLockerPaginationList", method = RequestMethod.POST)
    @Operation(summary = "查询案库方法列表和分页", description = "查询案库方法列表和分页")
    public PageInfo<CaseLocker> findLockerPaginationList(@RequestBody CaseLocker caseLocker){
        return caseLockerServicelmpl.findLockerPaginationList(caseLocker);
    }
    @RequestMapping(value = "deleteLocker", method = RequestMethod.POST)
    @Operation(summary = "删除案库方法", description = "删除案库方法")
    public JsonResult deleteLocker(@RequestParam (value = "lockerId")Long lockerId){
        try {
            return caseLockerServicelmpl.deleteLocker(lockerId);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("删除失败");
        }
    }
    @RequestMapping(value = "addLocker", method = RequestMethod.POST)
    @Operation(summary = "新增案库方法", description = "新增案库方法")
    public JsonResult addLocker(@RequestBody CaseLocker caseLocker){
        try {
            caseLocker.setDocumentationId(1);
            caseLocker.setWholeSectId(1);
            caseLocker.setCreationTime(new Date());
            caseLocker.setUpdated(new Date());
            return caseLockerServicelmpl.addLocker(caseLocker);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("新增失败");
        }
    }
    @RequestMapping(value = "findLock", method = RequestMethod.POST)
    @Operation(summary = "回显案库方法", description = "回显案库方法")
    public CaseLocker findLock(@RequestParam(value = "lockerId")Long lockerId){
        return caseLockerServicelmpl.findLock(lockerId);
    }
    @RequestMapping(value = "updateLocker", method = RequestMethod.POST)
    @Operation(summary = "修改案库方法", description = "修改案库方法")
    public JsonResult updateLocker(@RequestBody CaseLocker caseLocker){
        try {
            caseLocker.setUpdated(new Date());
            return caseLockerServicelmpl.updateLocker(caseLocker);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("修改失败");
        }
    }
    @RequestMapping(value = "findLockerPaginationListById", method = RequestMethod.POST)
    @Operation(summary = "所属案库查询", description = "所属案库查询")
    public JsonResult findLockerPaginationListById(Long affiliationss){
        return caseLockerServicelmpl.findLockerPaginationListById(affiliationss);

    }
    @RequestMapping(value = "findAlll", method = RequestMethod.POST)
    @Operation(summary = "查询所有", description = "查询所有")
    public List<CaseLocker> findAlll(CaseLocker caseLocker){
        return caseLockerServicelmpl.findAlll(caseLocker);

    }

}
