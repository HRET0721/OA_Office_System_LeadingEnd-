package org.hret.controller.personnel.leave;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.assess.Assess;
import org.hret.entity.personnel.leave.Holiday;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.impl.leave.HolidayServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "holiday")
@Tag(name = "假期方法", description = "假期方法")
public class HolidayController {
    private final HolidayServiceImpl holidayServiceImpl;

    @RequestMapping(value = "findHolidayPaginationList", method = RequestMethod.POST)
    @Operation(summary = "查询考核方法列表和分页",description = "查询考核列表和分页")
    public PageInfo<Holiday> findHolidayPaginationList(@RequestBody Holiday holiday) {
        return holidayServiceImpl.findHolidayPaginationList(holiday);
    }
    @RequestMapping(value = "deleteHoliday", method = RequestMethod.POST)
    @Operation(summary = "删除假期方法", description = "删除假期方法")
    public JsonResult deleteHoliday(@RequestParam(value = "holidayId") Long holidayId) {
        try {
            return holidayServiceImpl.deleteHoliday(holidayId);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
    }
    @RequestMapping(value = "addHoliday", method = RequestMethod.POST)
    @Operation(summary = "新增假期方法", description = "新增假期方法")
    public JsonResult addHoliday(@RequestBody Holiday holiday) {
        try {
            return holidayServiceImpl.addHoliday(holiday);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("添加失败");
        }
    }
    @RequestMapping(value = "findHoliday", method = RequestMethod.POST)
    @Operation(summary = "回显假期方法", description = "回显假期方法")
    public Holiday findHoliday(@RequestParam(value = "holidayId") Long holidayId) {
        return holidayServiceImpl.findHoliday(holidayId);
    }
    @RequestMapping(value = "updateHoliday", method = RequestMethod.POST)
    @Operation(summary = "修改假期方法", description = "修改假期方法")
    public JsonResult updateHoliday(@RequestBody Holiday holiday) {
        try {
            return holidayServiceImpl.updateHoliday(holiday);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("修改失败");
        }
    }
}
