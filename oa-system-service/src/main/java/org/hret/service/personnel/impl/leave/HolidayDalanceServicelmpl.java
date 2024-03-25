package org.hret.service.personnel.impl.leave;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.leave.HolidayDalance;
import org.hret.entity.utils.query.Dept;
import org.hret.entity.utils.query.User;
import org.hret.mapper.personnel.leave.HolidayDalanceMapper;
import org.hret.mapper.util.query.DeptMapper;
import org.hret.mapper.util.query.UserMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.leave.DeptHoliddayService;
import org.hret.service.personnel.leave.HolidayDalanceService;
import org.hret.service.util.query.impl.DeptServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayDalanceServicelmpl extends ServiceImpl<HolidayDalanceMapper, HolidayDalance>implements HolidayDalanceService {
    private final UserMapper userMapper;
    private final HolidayDalanceMapper holidayDalanceMapper;
    private final DeptMapper deptMapper;


    @Override
    public PageInfo<HolidayDalance> findHolidayDalancePaginationList(HolidayDalance holidayDalance) {
        PageHelper.startPage(holidayDalance.getPageNum(), holidayDalance.getPageSize());

        LambdaQueryWrapper<HolidayDalance> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (holidayDalance.getUserNames() != null && !"".equals(holidayDalance.getUserNames())) {
            lambdaQueryWrapper.like(HolidayDalance::getBalanceName, holidayDalance.getBalanceName());
        }
        List<HolidayDalance> holiday = holidayDalanceMapper.selectList(lambdaQueryWrapper);
        holiday.forEach(item ->{
//            查询用户名称
            User user = userMapper.selectById(item.getUserId());
            item.setUserNames(user.getUserName());
//            查询部门名称
            Dept dept = deptMapper.selectById(item.getDeptId());
            item.setDeptName(dept.getDeptName());
        });
        return new PageInfo<>(holiday);
    }

    @Override
    public JsonResult deleteDalance(long balanceId) {
        int i = this.baseMapper.deleteById(balanceId);
        if (i > 0) {
            return JsonResult.ok("删除成功");
        } else {
            return JsonResult.error("删除失败");
        }
    }

    @Override
    public List<HolidayDalance> excel() {
        return list();
    }


}