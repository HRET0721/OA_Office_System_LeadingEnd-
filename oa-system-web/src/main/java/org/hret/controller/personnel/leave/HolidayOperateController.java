package org.hret.controller.personnel.leave;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.leave.HolidayOperate;
import org.hret.service.personnel.impl.leave.HolidayOperateServicelmpl;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "operate")
@RequiredArgsConstructor
@Tag(name = "假期操作", description = "假期操作")
public class HolidayOperateController {



    private final HolidayOperateServicelmpl holidayOperateServicelmpl;
    @RequestMapping(value = "findHolidayOperatePaginationList", method = RequestMethod.POST)
    @Operation(summary = "分页查询操作方法", description = "分页查询操作方法")
    public PageInfo<HolidayOperate> findHolidayOperatePaginationList(@RequestBody HolidayOperate holidayOperate) {
        return holidayOperateServicelmpl.findHolidayOperatePaginationList(holidayOperate);
    }
}
