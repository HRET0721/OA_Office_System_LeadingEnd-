package org.hret.service.personnel.leave;

import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.leave.Holiday;
import org.hret.entity.personnel.leave.Recording;
import org.hret.entity.personnel.recruit.po.RecruitJob;
import org.hret.pojo.JsonResult;

import java.util.List;

public interface RecordingService {
    PageInfo<Recording> findRecordingPaginationList(Recording recording);

   public JsonResult deleteRecording(Long approvalId);

    List<Recording> excel();
}
