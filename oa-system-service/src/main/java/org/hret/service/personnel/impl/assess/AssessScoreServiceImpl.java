package org.hret.service.personnel.impl.assess;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.assess.AssessScore;
import org.hret.mapper.personnel.assess.AssessScoreMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.assess.AssessScoreService;
import org.springframework.stereotype.Service;

@Service
public class AssessScoreServiceImpl extends ServiceImpl<AssessScoreMapper, AssessScore> implements AssessScoreService {
    @Override
    public PageInfo<AssessScore> findAssessScoreListAndPage(AssessScore assessScore) {

        PageHelper.startPage(assessScore.getPageNum(), assessScore.getPageSize());

        QueryWrapper<AssessScore> queryWrapper = new QueryWrapper<>();
        if ( assessScore.getScoreName() != null && !"".equals(assessScore.getScoreName()) ) {
            queryWrapper.like("score_name", assessScore.getScoreName());
        }
        return new PageInfo<>(list(queryWrapper));
    }

    @Override
    public JsonResult addAssessScore(AssessScore assessScore) {
        assessScore.setCreateBy("周杰伦");
        assessScore.setStatus("0");
        int i = baseMapper.insert(assessScore);
        if (i > 0) {
            return JsonResult.ok("添加成功");
        }else {
            return JsonResult.error("添加失败");
        }
    }

    @Override
    public JsonResult updateAssessScore(AssessScore assessScore) {
        int i = baseMapper.updateById(assessScore);
        if (i > 0) {
            return JsonResult.ok("修改成功");
        }else {
            return JsonResult.error("修改失败");
        }
    }

    @Override
    public JsonResult deleteAssessScore(Long assessScoreId) {
        int i = baseMapper.deleteById(assessScoreId);
        if (i > 0) {
            return JsonResult.ok("删除成功");
        }else {
            return JsonResult.error("删除失败");
        }
    }

    @Override
    public AssessScore findAssessScoreById(Long assessScoreId) {
        return this.baseMapper.selectById(assessScoreId);
    }
}
