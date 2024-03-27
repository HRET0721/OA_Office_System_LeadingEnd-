package org.hret.service.personnel.leave;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.leave.HolidayDalance;
import org.hret.pojo.JsonResult;

import java.util.List;

public interface HolidayDalanceService extends IService<HolidayDalance> {
    PageInfo<HolidayDalance> findHolidayDalancePaginationList(HolidayDalance holidayDalance);

    JsonResult deleteDalance(long balanceId);

    List<HolidayDalance> excel();
}
