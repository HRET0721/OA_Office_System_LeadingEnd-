package org.hret.service.personnel.impl.assess;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.assess.AssessIndex;
import org.hret.entity.personnel.assess.AssessTemplate;
import org.hret.entity.personnel.assess.IndexTemplate;
import org.hret.mapper.personnel.assess.AssessTemplateMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.assess.AssessIndexService;
import org.hret.service.personnel.assess.AssessTemplateService;
import org.hret.service.personnel.assess.IndexTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssessTemplateServiceImpl extends ServiceImpl<AssessTemplateMapper, AssessTemplate> implements AssessTemplateService {


    private final AssessIndexService assessIndexService;
    private final IndexTemplateService indexTemplateService;

    @Override
    public PageInfo<AssessTemplate> findAssessTemplateListAndPage(AssessTemplate assessTemplate) {
        PageHelper.startPage(assessTemplate.getPageNum(), assessTemplate.getPageSize());
        QueryWrapper<AssessTemplate> queryWrapper = new QueryWrapper<>();
        if (assessTemplate.getTemplateName() != null && !"".equals(assessTemplate.getTemplateName())) {
            queryWrapper.like("Template_name", assessTemplate.getTemplateName());
        }
        List<AssessTemplate> list = this.list(queryWrapper);
        list = getAssessTemplate(list);
        return new PageInfo<>(list);
    }
    public List<AssessTemplate> getAssessTemplate(List<AssessTemplate> list) {
//        遍历考核模板
        for (AssessTemplate assessTemplate: list) {
//            获取考核模板id
            Integer templateId = assessTemplate.getTemplateId();
//            判断模板id
            if (templateId != null ) {
//                根据模板id查询考核指标id
                List<IndexTemplate> assessIndexlist = indexTemplateService.lambdaQuery().eq(IndexTemplate::getTemplateId, templateId).list();
//                遍历考核指标id
                for (IndexTemplate indexTemplate : assessIndexlist) {
//                    根据考核指标id查询考核指标
                    Integer assessIndexId = indexTemplate.getAssessIndexId();
//                    定义考核指标
                    AssessIndex assessIndex = assessIndexService.lambdaQuery().eq(AssessIndex::getAssessIndexId, assessIndexId).one();
//                    判断考核指标
                    if (assessTemplate.getAssessIndex() == null ) {
//                        存入考核指标
                        assessTemplate.setAssessIndex(new ArrayList<>());
                    }
//                    添加到考核指标
                    assessTemplate.getAssessIndex().add(assessIndex);
                }
            }
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
//        int i = this.baseMapper.insert(assessTemplate);
////        判断新增结果
//        if (i > 0) {
//            return new JsonResult(500, "添加失败", null);
//        }
//
//        boolean saveAssessIndex = false;
//
//        // TODO: 判断是否有考核指标的数据
//        if (ObjectUtil.isNotNull(assessTemplate.getAssessIndex()) && ObjectUtil.isNotEmpty(assessTemplate.getAssessIndex())) {
//            //       新增考核指标
//            saveAssessIndex = assessIndexService.save(assessTemplate.getAssessIndex());
//        }
//
//        //        判断新增结果
//        if (!saveAssessIndex ) {
//            return new JsonResult(200, "添加成功", null);
//        } else {
//            return new JsonResult(500, "添加失败", null);
//        }
//    }
        return null;
    }

}
