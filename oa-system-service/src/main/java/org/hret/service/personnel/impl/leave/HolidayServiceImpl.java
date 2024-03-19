package org.hret.service.personnel.impl.leave;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.leave.Holiday;
import org.hret.mapper.personnel.leave.HolidayMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.leave.HolidayService;
import org.springframework.stereotype.Service;


@Service
public class HolidayServiceImpl extends ServiceImpl<HolidayMapper, Holiday> implements HolidayService {

    @Override
    public PageInfo<Holiday> findHolidayPaginationList(Holiday holiday) {
        PageHelper.startPage(holiday.getPageNum(), holiday.getPageSize());

        QueryWrapper<Holiday> queryWrapper = new QueryWrapper<>();
        if ( holiday.getHolidayType() != null && !"".equals(holiday.getHolidayType()) ) {
            queryWrapper.like("holiday_type", holiday.getHolidayType());
        }

        return new PageInfo<>(list(queryWrapper));
    }

    @Override
    public JsonResult deleteHoliday(Long holidayId) {
        int i = this.baseMapper.deleteById(holidayId);
        if (i > 0) {
            return JsonResult.ok("删除成功");
        } else {
            return JsonResult.error("删除失败");
        }

    }

    @Override
    public JsonResult addHoliday(Holiday holiday) {
        int insert = this.baseMapper.insert(holiday);

        if (insert > 0) {
            return JsonResult.ok("添加成功");
        } else {
            return JsonResult.error("添加失败");
        }

    }

    @Override
    public Holiday findHoliday(Long holidayId) {
        return this.baseMapper.selectById(holidayId);
    }

    @Override
    public JsonResult updateHoliday(Holiday holiday) {
        int i = this.baseMapper.updateById(holiday);

        if (i > 0) {
            return JsonResult.ok("修改成功");
        } else {
            return JsonResult.error("修改失败");
        }
    }
}
