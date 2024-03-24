package org.hret.service.personnel.impl.leave;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.leave.Recording;
import org.hret.mapper.personnel.leave.RecordingMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.leave.RecordingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Recordingservicelmpl extends ServiceImpl<RecordingMapper, Recording>implements RecordingService {
    @Override
    public PageInfo<Recording> findRecordingPaginationList(Recording recording) {
        PageHelper.startPage(recording.getPageNum(), recording.getPageSize());

        QueryWrapper<Recording> queryWrapper = new QueryWrapper<>();
        if ( recording.getHolidayName() != null && !"".equals(recording.getHolidayName()) ) {
            queryWrapper.like("holiday_name", recording.getHolidayName());
        }
        if ( recording.getHolidayType() != null && !"".equals(recording.getHolidayType())){
            queryWrapper.like("holiday_type", recording.getHolidayType());
        }
        if ( recording.getEndTime() != null && !"".equals(recording.getEndTime()) ) {
            queryWrapper.eq("end_time", recording.getEndTime());
        }
        if ( recording.getLnitiationTime() != null && !"".equals(recording.getLnitiationTime()) ) {
            queryWrapper.eq("lnitiation_time", recording.getLnitiationTime());
        }

        return new PageInfo<>(list(queryWrapper));
    }

    @Override
    public JsonResult deleteRecording(Long approvalId) {
        int i = this.baseMapper.deleteById(approvalId);
        if (i > 0) {
            return JsonResult.ok("删除成功");
        } else {
            return JsonResult.error("删除失败");
        }
    }

    @Override
    public List<Recording> excel() {
        return list();
    }
}
