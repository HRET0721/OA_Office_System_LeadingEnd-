package org.hret.service.personnel.assess;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.assess.AssessScore;
import org.hret.pojo.JsonResult;

public interface AssessScoreService extends IService<AssessScore> {
    PageInfo<AssessScore> findAssessScoreListAndPage(AssessScore assessScore);

    JsonResult addAssessScore(AssessScore assessScore);

    JsonResult updateAssessScore(AssessScore assessScore);

    JsonResult deleteAssessScore(Long assessScoreId);

    AssessScore findAssessScoreById(Long assessScoreId);
}
