package org.hret.controller.personnel.recruit;

import annotation.recruit.EasyPoiUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.hret.entity.recruit.po.RecruitJob;
import org.hret.entity.recruit.vo.JsonResult;
import org.hret.service.personnel.recruit.RecruitJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Authar:liujintao
 * Data:2024/3/18
 * jdk:17
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
    public JsonResult findPage(@RequestBody RecruitJob recruitJob, @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return JsonResult.ok("200",recruitJobService.findPage(recruitJob, pageNum, pageSize));
    }

    @RequestMapping(value = "updateState", method = RequestMethod.POST)
    @Operation(summary = "更新职位状态", description = "更新职位状态")
    public void updateState(@RequestBody RecruitJob recruitJob) {
        recruitJobService.updateState(recruitJob);
    }

    @Operation(summary = "更新职位", description = "更新职位")
    @RequestMapping(value = "updateJob", method = RequestMethod.POST)
    public void updateJob(@RequestBody RecruitJob recruitJob) {
        recruitJobService.updateJob(recruitJob);
    }

    @Operation(summary = "添加职位", description = "添加职位")
    @RequestMapping(value = "addJob", method = RequestMethod.POST)
    public void addJob(@RequestBody RecruitJob recruitJob) {
        recruitJobService.addJob(recruitJob);
    }

    @Operation(summary = "删除职位", description = "删除职位")
    @RequestMapping(value = "deleteJob", method = RequestMethod.POST)
    public void deleteJob(@RequestBody RecruitJob recruitJob) {
        recruitJobService.deleteJob(recruitJob);
    }

    @Operation(summary = "查询所有职位", description = "查询所有职位")
    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    public List<RecruitJob> findAll() {
        return recruitJobService.findAll();
    }


    @GetMapping(value = "exportData")
    @Operation(summary = "导出数据", description = "导出数据")
    void exportData(HttpServletResponse response){
        List<RecruitJob> all = recruitJobService.findAll();
        EasyPoiUtils.exportExcel(all, "日志导出", "日志导出", RecruitJob.class, "日志导出.xlsx", response);
    }
}
