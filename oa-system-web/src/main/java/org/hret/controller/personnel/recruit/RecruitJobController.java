package org.hret.controller.personnel.recruit;

import org.hret.pojo.EasyPoiUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.hret.entity.personnel.recruit.po.RecruitJob;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.recruit.RecruitJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author:intaglio
 * Data:2024/3/18
 * jdk:17
 * @author HRET
 */
@CrossOrigin
@RestController
@RequestMapping(value = "recruitJob")
@Tag(name = "职位管理", description = "职位管理")
public class RecruitJobController {

    @Autowired
    private RecruitJobService recruitJobService;

    @RequestMapping(value = "findPage", method = RequestMethod.POST)
    @Operation(summary = "查询职位", description = "查询职位")
    public JsonResult findPage(@RequestBody RecruitJob recruitJob) {
        return JsonResult.ok("200", recruitJobService.findPage(recruitJob));
    }

    @RequestMapping(value = "updateState", method = RequestMethod.POST)
    @Operation(summary = "更新职位状态", description = "更新职位状态")
    public JsonResult updateState(@RequestBody RecruitJob recruitJob) {
        try {
            return recruitJobService.updateState(recruitJob);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("更新状态失败");
        }
    }

    @Operation(summary = "更新职位", description = "更新职位")
    @RequestMapping(value = "updateJob", method = RequestMethod.POST)
    public JsonResult updateJob(@RequestBody RecruitJob recruitJob) {
        try {
            return recruitJobService.updateJob(recruitJob);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("更新失败");
        }
    }

    @Operation(summary = "添加职位", description = "添加职位")
    @RequestMapping(value = "addJob", method = RequestMethod.POST)
    public JsonResult addJob(@RequestBody RecruitJob recruitJob) {
        try {
            return recruitJobService.addJob(recruitJob);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("添加失败");
        }
    }

    @Operation(summary = "删除职位", description = "删除职位")
    @RequestMapping(value = "deleteJob", method = RequestMethod.POST)
    public JsonResult deleteJob(@RequestBody RecruitJob recruitJob) {
        try {
            return recruitJobService.deleteJob(recruitJob);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("删除失败");
        }
    }

    @Operation(summary = "查询所有职位", description = "查询所有职位")
    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    public List<RecruitJob> findAll() {
        return recruitJobService.findAll();
    }


    @RequestMapping(value = "exportData")
    @Operation(summary = "导出数据", description = "导出数据")
    void exportData(HttpServletResponse response) {
        List<RecruitJob> all = recruitJobService.findAll();
        EasyPoiUtils.exportExcel(all, "日志导出", "日志导出", RecruitJob.class, "日志导出.xlsx", response);
    }
}
