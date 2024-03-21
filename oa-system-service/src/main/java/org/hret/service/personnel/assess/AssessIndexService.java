package org.hret.service.personnel.assess;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.assess.AssessIndex;
import org.hret.pojo.JsonResult;

public interface AssessIndexService extends IService<AssessIndex> {

    PageInfo<AssessIndex> findAssessIndexListAndPage(AssessIndex assessIndex);

    JsonResult addAssessIndex(AssessIndex assessIndex);

    JsonResult updateAssessIndex(AssessIndex assessIndex);

    JsonResult deleteAssessIndex(Long assessIndexId);

    AssessIndex findAssessIndexById(Long assessIndexId);
}
