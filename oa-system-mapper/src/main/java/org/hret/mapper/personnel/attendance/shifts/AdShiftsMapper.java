package org.hret.mapper.personnel.attendance.shifts;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.hret.entity.personnel.attendance.shifts.AdShifts;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Configuration
@Repository
@Mapper
public interface AdShiftsMapper extends BaseMapper<AdShifts> {


}
