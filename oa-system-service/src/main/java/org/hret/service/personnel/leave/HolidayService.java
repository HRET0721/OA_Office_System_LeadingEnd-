package org.hret.service.personnel.leave;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.leave.Holiday;
import org.hret.pojo.JsonResult;


import java.util.List;

public interface HolidayService extends IService<Holiday> {


    PageInfo<Holiday> findHolidayPaginationList(Holiday holiday);

    JsonResult deleteHoliday(Long holidayId);

    JsonResult addHoliday(Holiday holiday);

    Holiday findHoliday(Long holidayId);

    JsonResult updateHoliday(Holiday holiday);
}
