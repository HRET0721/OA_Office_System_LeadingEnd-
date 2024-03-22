package org.hret.controller.personnel.recruit;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.hret.entity.personnel.recruit.RecruitCandidate;
import org.hret.pojo.EasyPoiUtils;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.recruit.RecruitCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Authar:liujintao
 * Data:2024/3/21
 * jdk:17
 */
@CrossOrigin
@RestController
@RequestMapping(value = "recruitCandidate")
@Tag(name = "候选人管理", description = "候选人管理")
public class RecruitCandidateController {
    @Autowired
    private RecruitCandidateService recruitCandidateService;

    @Operation(summary = "查询候选人", description = "查询候选人")
    @RequestMapping(value = "findByRecruitCandidatePage", method = RequestMethod.POST)
    public JsonResult findByRecruitCandidatePage(@RequestBody RecruitCandidate recruitCandidate) {
        return JsonResult.ok("查询成功", recruitCandidateService.findByRecruitCandidatePage(recruitCandidate));
    }

    @Operation(summary = "更新候选人状态", description = "更新候选人状态")
    @RequestMapping(value = "updateRecruitCandidateStatus", method = RequestMethod.POST)
    public JsonResult updateRecruitCandidateStatus(@RequestBody RecruitCandidate recruitCandidate) {
        try{
            return recruitCandidateService.updateRecruitCandidateStatus(recruitCandidate);
        }catch (Exception e){
            return JsonResult.error("更新失败");
        }
    }

    @Operation(summary = "更新候选人", description = "更新候选人")
    @RequestMapping(value = "updateRecruitCandidate", method = RequestMethod.POST)
    public JsonResult updateRecruitCandidate(@RequestBody RecruitCandidate recruitCandidate) {
        try{
            return recruitCandidateService.updateRecruitCandidate(recruitCandidate);
        }catch (Exception e){
            return JsonResult.error("更新失败");
        }

    }

    @Operation(summary = "添加候选人", description = "添加候选人")
    @RequestMapping(value = "addRecruitCandidate", method = RequestMethod.POST)
    public JsonResult addRecruitCandidate(@RequestBody RecruitCandidate recruitCandidate) {
        try{
            return recruitCandidateService.addRecruitCandidate(recruitCandidate);
        }catch (Exception e){
            return JsonResult.error("添加失败");
        }
    }

    @Operation(summary = "删除候选人", description = "删除候选人")
    @RequestMapping(value = "deleteRecruitCandidate", method = RequestMethod.POST)
    public JsonResult deleteRecruitCandidate(@RequestBody RecruitCandidate recruitCandidate) {
        try{
            return recruitCandidateService.deleteRecruitCandidate(recruitCandidate);
        }catch (Exception e){
            return JsonResult.error("删除失败");
        }
    }

    @Operation(summary = "查询所有候选人", description = "查询所有候选人")
    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    public List<RecruitCandidate> findAll() {
        return recruitCandidateService.findAll();
    }

    @RequestMapping(value = "exportData",method = RequestMethod.POST)
    @Operation(summary = "导出数据", description = "导出数据")
    void exportData(HttpServletResponse response) {
        List<RecruitCandidate> all = recruitCandidateService.findAll();
        EasyPoiUtils.exportExcel(all, "日志导出", "日志导出", RecruitCandidate.class, "日志导出.xlsx", response);
    }
}
