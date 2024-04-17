package org.hret.service.personnel.impl.leave;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.leave.Holiday;
import org.hret.entity.personnel.leave.HolidayDalance;
import org.hret.entity.personnel.leave.HolidayOperate;
import org.hret.entity.utils.query.Dept;
import org.hret.entity.utils.query.User;
import org.hret.mapper.personnel.leave.HolidayDalanceMapper;
import org.hret.mapper.personnel.leave.HolidayMapper;
import org.hret.mapper.personnel.leave.HolidayOperateMapper;
import org.hret.mapper.personnel.leave.OperateBalanceMapper;
import org.hret.mapper.util.query.DeptMapper;
import org.hret.mapper.util.query.UserMapper;
import org.hret.service.personnel.leave.HolidayOperateService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HolidayOperateServicelmpl extends ServiceImpl<HolidayOperateMapper, HolidayOperate>implements HolidayOperateService {
    private final UserMapper userMapper;
    private final HolidayOperateMapper holidayOperateMapper;
    private final DeptMapper deptMapper;
    private final HolidayDalanceMapper holidayDalanceMapper;

    @Override
    public PageInfo<HolidayOperate> findHolidayOperatePaginationList(HolidayOperate holidayOperate) {
        PageHelper.startPage(holidayOperate.getPageNum(), holidayOperate.getPageSize());

        LambdaQueryWrapper<HolidayOperate> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (holidayOperate.getUserNames() != null && !"".equals(holidayOperate.getUserNames())) {
            lambdaQueryWrapper.like(HolidayOperate::getOperateId, holidayOperate.getOperateId());
        }
        List<HolidayOperate> operate = holidayOperateMapper.selectList(lambdaQueryWrapper);
        operate.forEach(item -> {
//            查询用户名称
            User user = userMapper.selectById(item.getUserId());
            item.setUserNames(user.getUserName());
//            部门查询

            Dept dept = deptMapper.selectById(item.getDeptId());
             item.setDeptName(dept.getDeptName());
//             假期id
            HolidayDalance holidayDalance = holidayDalanceMapper.selectById(item.getBalanceId());
            item.setBalanceName(holidayDalance.getBalanceName());
        }
        );

        return new PageInfo<>(operate);
    }
}
