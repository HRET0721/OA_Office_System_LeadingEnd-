package org.hret.service.personnel.assess;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.assess.AssessTemplate;
import org.hret.pojo.JsonResult;

public interface AssessTemplateService extends IService<AssessTemplate> {

    PageInfo<AssessTemplate> findAssessTemplateListAndPage(AssessTemplate assessTemplate);

    JsonResult addAssessTemplate(AssessTemplate assessTemplate);

    AssessTemplate findAssessTemplateById(Long templateId);

    JsonResult deleteAssessTemplate(Long templateId);

    JsonResult updateAssessTemplate(AssessTemplate assessTemplate);

    JsonResult addAssessTemplateAndAssessIndex(AssessTemplate assessTemplate);
}
