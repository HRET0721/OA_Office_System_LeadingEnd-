package org.hret.service.personnel.leave;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.leave.HolidayOperate;

public interface HolidayOperateService extends IService<HolidayOperate> {
    PageInfo<HolidayOperate> findHolidayOperatePaginationList(HolidayOperate holidayOperate);
}
