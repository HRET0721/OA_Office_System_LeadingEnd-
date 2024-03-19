package org.hret.service.personnel.impl.assess;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hret.entity.personnel.assess.Assess;
import org.hret.mapper.personnel.assess.AssessMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.assess.AssessService;
import org.springframework.stereotype.Service;

@Service
public class AssessServiceImpl extends ServiceImpl<AssessMapper, Assess> implements AssessService {
    @Override
    public JsonResult findAssessListAndPage(Assess assess, Integer pageNum, Integer pageSize) {
        IPage<Assess> page = new Page(pageNum,pageSize);
        QueryWrapper<Assess> queryWrapper = new QueryWrapper();
        if (assess != null && assess.getAssessName() != null && !assess.getAssessName().equals("")) {
            queryWrapper.like("assess_name", assess.getAssessName());
        }
        if (assess != null && assess.getStatus() != null && !assess.getStatus().equals("")){
            queryWrapper.like("assess_status", assess.getStatus());
        }
        if (assess != null && assess.getAssessType() != null && !assess.getAssessType().equals("")) {
            queryWrapper.eq("assess_type", assess.getAssessType());
        }
        if (assess != null && assess.getAssessTime() != null && !assess.getAssessTime().equals("")) {
            queryWrapper.eq("assess_time", assess.getAssessTime());
        }
        IPage<Assess> page1 = this.baseMapper.selectPage(page, queryWrapper);
        return JsonResult.ok(String.valueOf(page1));
    }

    @Override
    public void addAssessMent(Assess assess) {
        this.baseMapper.insert(assess);
    }

    @Override
    public void deleteAssessMent(Long assessId) {
        this.baseMapper.deleteById(assessId);
    }

    @Override
    public Assess findAeeseeMentById(Long assessId) {
        return this.baseMapper.selectById(assessId);
    }

    @Override
    public void updateAssessMent(Assess assess) {
        this.baseMapper.updateById(assess);
    }
}
