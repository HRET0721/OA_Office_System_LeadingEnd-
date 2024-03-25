package org.hret.controller.personnel.leave;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.leave.HolidayDalance;
import org.hret.entity.personnel.leave.Recording;
import org.hret.pojo.EasyPoiUtils;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.impl.leave.HolidayDalanceServicelmpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "dalance")
@RequiredArgsConstructor
@Tag(name = "假期余额", description = "假期余额")
public class HolidayDalanceController {
    private final HolidayDalanceServicelmpl holidayDalanceServicelmpl;
    @RequestMapping(value = "findHolidayDalancePaginationList", method = RequestMethod.POST)
    @Operation(summary = "分页查询假期余额方法", description = "分页查询假期余额方法")
    public PageInfo<HolidayDalance> findHolidayDalancePaginationList(@RequestBody HolidayDalance holidayDalance) {
        return holidayDalanceServicelmpl.findHolidayDalancePaginationList(holidayDalance);
    }
    @RequestMapping(value = "deleteDalance", method = RequestMethod.POST)
    @Operation(summary = "删除请假记录方法", description = "删除请假记录方法")
    public JsonResult deleteDalance(@RequestParam(value = "balanceId")long balanceId){
        try {
            return holidayDalanceServicelmpl.deleteDalance(balanceId);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("删除失败");
        }
    }
    @GetMapping(value = "exportData")
    @Operation(summary = "导出数据", description = "导出数据")
    void exportData(HttpServletResponse response){
        List<HolidayDalance> all = holidayDalanceServicelmpl.excel();
        EasyPoiUtils.exportExcel(all, "日志导出", "日志导出", HolidayDalance.class, "日志导出.xlsx", response);

}
}
