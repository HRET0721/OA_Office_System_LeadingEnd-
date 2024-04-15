package org.hret.mapper.personnel.attendance.shifts.frame;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.hret.entity.personnel.attendance.shifts.elastic.AdShiftsElastic;
import org.hret.entity.personnel.attendance.shifts.frame.AdShiftsFrame;

import java.util.Collection;

public interface AdShiftsFrameMapper extends BaseMapper<AdShiftsFrame> {
    // 批量插入 仅适用于mysql
    Integer insertBatchSomeColumn(Collection<AdShiftsFrame> entityList);
}
