package org.hret.service.personnel.impl.leave;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hret.entity.personnel.leave.DeptHoliday;
import org.hret.entity.personnel.leave.UserHoliday;
import org.hret.mapper.personnel.leave.DeptHoliddayMapper;
import org.hret.mapper.personnel.leave.UserHolidayMapper;
import org.hret.service.personnel.leave.DeptHoliddayService;
import org.hret.service.personnel.leave.UserHolidayService;
import org.springframework.stereotype.Service;

@Service
public class UserHolidayServicelmpl extends ServiceImpl<UserHolidayMapper, UserHoliday> implements UserHolidayService {
}
