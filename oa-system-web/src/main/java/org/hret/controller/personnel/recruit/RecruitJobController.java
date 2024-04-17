package org.hret.controller.personnel.recruit;

import lombok.RequiredArgsConstructor;
import org.hret.pojo.EasyPoiUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.hret.entity.personnel.recruit.RecruitJob;
import org.hret.pojo.JsonResult;
import org.hret.pojo.OssFileUtil;
import org.hret.service.personnel.recruit.RecruitJobService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Authar:liujintao
 * Data:2024/3/18
 * jdk:17
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "recruitJob")
@Tag(name = "职位管理", description = "职位管理")
public class RecruitJobController {

    private final RecruitJobService recruitJobService;

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
            return JsonResult.error("更新状态失败");
        }
    }

    @Operation(summary = "更新职位", description = "更新职位")
    @RequestMapping(value = "updateJob", method = RequestMethod.POST)
    public JsonResult updateJob(@RequestBody RecruitJob recruitJob) {
        try {
            return recruitJobService.updateJob(recruitJob);
        } catch (Exception e) {
            return JsonResult.error("更新失败");
        }
    }

    @Operation(summary = "添加职位", description = "添加职位")
    @RequestMapping(value = "addJob", method = RequestMethod.POST)
    public JsonResult addJob(@RequestBody RecruitJob recruitJob) {
//        try {
            return recruitJobService.addJob(recruitJob);
//        } catch (Exception e) {
//            return JsonResult.error("添加失败");
//        }
    }

    @Operation(summary = "删除职位", description = "删除职位")
    @RequestMapping(value = "deleteJob", method = RequestMethod.POST)
    public JsonResult deleteJob(@RequestParam(value = "JobId") Long JobId) {
        try {
            return recruitJobService.deleteJob(JobId);
        } catch (Exception e) {
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

    @RequestMapping(value = "userImg")
    @Operation(summary = "上传图片", description = "上传图片")
    public JsonResult userImg(@RequestParam("file") MultipartFile file) {
        try {
            String s = OssFileUtil.uploadFile(file);
            String imgUrl = OssFileUtil.getImgUrl(s);
            return JsonResult.ok("200", imgUrl);
        } catch (Exception e) {
            return JsonResult.error("上传失败");
        }
    }
}
