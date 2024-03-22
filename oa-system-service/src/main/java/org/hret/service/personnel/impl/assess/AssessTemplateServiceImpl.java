package org.hret.service.personnel.impl.assess;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.assess.Assess;
import org.hret.entity.personnel.assess.AssessTemplate;
import org.hret.mapper.personnel.assess.AssessTemplateMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.assess.AssessTemplateService;
import org.springframework.stereotype.Service;

@Service
public class AssessTemplateServiceImpl extends ServiceImpl<AssessTemplateMapper, AssessTemplate> implements AssessTemplateService {
    @Override
    public PageInfo<AssessTemplate> findAssessTemplateListAndPage(AssessTemplate assessTemplate) {
        PageHelper.startPage(assessTemplate.getPageNum(), assessTemplate.getPageSize());
        QueryWrapper<AssessTemplate> queryWrapper = new QueryWrapper<>();
        if ( assessTemplate.getTemplateName() != null && !"".equals(assessTemplate.getTemplateName()) ) {
            queryWrapper.like("Template_name", assessTemplate.getTemplateName());
        }
        return new PageInfo<>(list(queryWrapper));
    }

    @Override
    public JsonResult addAssessTemplate(AssessTemplate assessTemplate) {
        int insert = this.baseMapper.insert(assessTemplate);

        if (insert > 0) {
            return JsonResult.ok("添加成功");
        } else {
            return JsonResult.error("添加失败");
        }
    }

    @Override
    public AssessTemplate findAssessTemplateById(Long templateId) {
        return getById(templateId);
    }

    @Override
    public JsonResult deleteAssessTemplate(Long templateId) {
        int i = this.baseMapper.deleteById(templateId);

        if (i > 0) {
            return JsonResult.ok("删除成功");
        } else {
            return JsonResult.error("删除失败");
        }
    }

    @Override
    public JsonResult updateAssessTemplate(AssessTemplate assessTemplate) {
        int i = this.baseMapper.updateById(assessTemplate);

        if (i > 0) {
            return JsonResult.ok("修改成功");
        } else {
            return JsonResult.error("修改失败");
        }
    }
}
