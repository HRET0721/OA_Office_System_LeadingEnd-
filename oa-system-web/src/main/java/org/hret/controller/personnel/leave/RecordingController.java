package org.hret.controller.personnel.leave;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.leave.Holiday;
import org.hret.entity.personnel.leave.Recording;
import org.hret.entity.personnel.recruit.po.RecruitJob;
import org.hret.pojo.EasyPoiUtils;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.impl.leave.Recordingservicelmpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HRET
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "recording")
@Tag(name = "请假方法", description = "请假方法")
public class RecordingController {
    private final Recordingservicelmpl recordingservicelmpl;
    @RequestMapping(value = "findRecordingPaginationList", method = RequestMethod.POST)
    @Operation(summary = "分页查询请假记录方法", description = "分页查询请假记录方法")
    public PageInfo<Recording> findRecordingPaginationList(@RequestBody Recording recording) {
        return recordingservicelmpl.findRecordingPaginationList(recording);
    }
    @RequestMapping(value = "deleteRecording", method = RequestMethod.POST)
    @Operation(summary = "删除请假记录方法", description = "删除请假记录方法")
    public JsonResult deleteRecording(@RequestParam(value = "approvalId") Long approvalId) {
        try {
            return recordingservicelmpl.deleteRecording(approvalId);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("删除失败");
        }
    }
    @GetMapping(value = "exportData")
    @Operation(summary = "导出数据", description = "导出数据")
    void exportData(HttpServletResponse response){
        List<Recording> all = recordingservicelmpl.excel();
        EasyPoiUtils.exportExcel(all, "日志导出", "日志导出", Recording.class, "日志导出.xlsx", response);
    }
}
