package org.hret.mapper.personnel.attendance.shifts.elastic;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;
import org.hret.entity.personnel.attendance.shifts.elastic.AdShiftsElastic;

import java.util.Collection;

@Mapper
public interface AdShiftsElasticMapper extends BaseMapper<AdShiftsElastic>{

    // 批量插入 仅适用于mysql
    Integer insertBatchSomeColumn(Collection<AdShiftsElastic> entityList);
}
