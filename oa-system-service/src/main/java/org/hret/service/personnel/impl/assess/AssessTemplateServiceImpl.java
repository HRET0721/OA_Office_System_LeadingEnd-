package org.hret.service.personnel.impl.assess;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.assess.AssessTemplate;
import org.hret.mapper.personnel.assess.AssessTemplateMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.assess.AssessIndexService;
import org.hret.service.personnel.assess.AssessTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssessTemplateServiceImpl extends ServiceImpl<AssessTemplateMapper, AssessTemplate> implements AssessTemplateService {

    @Autowired
    private AssessIndexService assessIndexService;

    @Override
    public PageInfo<AssessTemplate> findAssessTemplateListAndPage(AssessTemplate assessTemplate) {
        PageHelper.startPage(assessTemplate.getPageNum(), assessTemplate.getPageSize());
        QueryWrapper<AssessTemplate> queryWrapper = new QueryWrapper<>();
        if (assessTemplate.getTemplateName() != null && !"".equals(assessTemplate.getTemplateName())) {
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
        return this.baseMapper.selectById(templateId);
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
    @Transactional(rollbackFor = Exception.class)
    public JsonResult updateAssessTemplate(AssessTemplate assessTemplate) {
        int i = this.baseMapper.updateById(assessTemplate);

        if (i > 0) {
            return JsonResult.ok("修改成功");
        } else {
            return JsonResult.error("修改失败");
        }
    }

    @Override
    public JsonResult addAssessTemplateAndAssessIndex(AssessTemplate assessTemplate) {
//        新增考核模板
        int i = this.baseMapper.insert(assessTemplate);
//        判断新增结果
        if (i > 0) {
            return new JsonResult(500, "添加失败", null);
        }

        boolean saveAssessIndex = false;

        // TODO: 判断是否有考核指标的数据
        if (ObjectUtil.isNotNull(assessTemplate.getAssessIndex()) && ObjectUtil.isNotEmpty(assessTemplate.getAssessIndex())) {
            //       新增考核指标
            saveAssessIndex = assessIndexService.save(assessTemplate.getAssessIndex());
        }

        //        判断新增结果
        if (!saveAssessIndex ) {
            return new JsonResult(200, "添加成功", null);
        } else {
            return new JsonResult(500, "添加失败", null);
        }
    }
}
