package org.hret.service.personnel.impl.recruit;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.recruit.RecruitCandidate;
import org.hret.mapper.personnel.recruit.RecruitCandidateMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.recruit.RecruitCandidateService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Authar:liujintao
 * Data:2024/3/21
 * jdk:17
 */
@Service
public class RecruitCandidateServiceImpl extends ServiceImpl<RecruitCandidateMapper, RecruitCandidate> implements RecruitCandidateService {
    @Override
    public PageInfo<RecruitCandidate> findByRecruitCandidatePage(RecruitCandidate recruitCandidate) {
//        开启分页插件
        PageHelper.startPage(recruitCandidate.getPageNum(), recruitCandidate.getPageSize());
//        条件查询
        LambdaQueryWrapper<RecruitCandidate> wrapper = Wrappers.lambdaQuery(RecruitCandidate.class);
//        关键字条查
        if (recruitCandidate.getCandidateName() != null && !recruitCandidate.getCandidateName().isEmpty()) {
            wrapper.like(RecruitCandidate::getCandidateName, recruitCandidate.getCandidateName());
        }
//          部门条查
        if (recruitCandidate.getCandidateDepartment() != null && !recruitCandidate.getCandidateDepartment().isEmpty()) {
            wrapper.eq(RecruitCandidate::getCandidateDepartment, recruitCandidate.getCandidateDepartment());
        }
//          面试官
        if (recruitCandidate.getCandidateNature() != null && !recruitCandidate.getCandidateNature().isEmpty()) {
            wrapper.eq(RecruitCandidate::getCandidateNature, recruitCandidate.getCandidateNature());
        }
//         学历条查
        if (recruitCandidate.getCandidateEducation() != null && !recruitCandidate.getCandidateEducation().isEmpty()) {
            wrapper.eq(RecruitCandidate::getCandidateEducation, recruitCandidate.getCandidateEducation());
        }
//        申请时间查询
        if (recruitCandidate.getCandidateStartTime() != null && !recruitCandidate.getCandidateStartTime().isEmpty()){
            wrapper.between(RecruitCandidate::getCandidateStartTime, recruitCandidate.getCandidateStartTime(), recruitCandidate.getCandidateTime());
        }
        if (recruitCandidate.getCandidateEndTime() != null && !recruitCandidate.getCandidateEndTime().isEmpty()){
            wrapper.between(RecruitCandidate::getCandidateEndTime, recruitCandidate.getCandidateEndTime(), recruitCandidate.getCandidateTime());
        }

        List<RecruitCandidate> list = this.list(wrapper);
        return new PageInfo<>(list);
    }

    @Override
    public JsonResult updateRecruitCandidateStatus(RecruitCandidate recruitCandidate) {
        int update = this.baseMapper.update(recruitCandidate, Wrappers.lambdaUpdate(RecruitCandidate.class).eq(RecruitCandidate::getCandidateId, recruitCandidate.getCandidateId()).set(RecruitCandidate::getCandidateStatus, recruitCandidate.getCandidateStatus()));
        if (update > 0){
            return JsonResult.ok("更新成功");
        }else {
            return JsonResult.error("更新失败");
        }
    }

    @Override
    public JsonResult updateRecruitCandidate(RecruitCandidate recruitCandidate) {
        boolean update = this.updateById(recruitCandidate);
        if (update){
            return JsonResult.ok("更新成功");
        }else {
            return JsonResult.error("更新失败");
        }
    }

    @Override
    public JsonResult addRecruitCandidate(RecruitCandidate recruitCandidate) {
        recruitCandidate.setCandidateStatus("1");
        Date date = new Date();
        String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
        recruitCandidate.setCandidateTime(format);
        int insert = this.baseMapper.insert(recruitCandidate);
        if (insert>0){
            return JsonResult.ok("添加成功");
        }else {
            return JsonResult.error("添加失败");
        }
    }

    @Override
    public JsonResult deleteRecruitCandidate(RecruitCandidate recruitCandidate) {
        int delete = this.baseMapper.delete(Wrappers.lambdaQuery(RecruitCandidate.class).eq(RecruitCandidate::getCandidateId, recruitCandidate.getCandidateId()));
        if (delete>0){
            return JsonResult.ok("删除成功");
        }else {
            return JsonResult.error("删除失败");
        }
    }

    @Override
    public List<RecruitCandidate> findAll() {
        return this.list();
    }

    @Override
    public Map<Object,Object> findConditionByNumber() {
        HashMap<Object,Object> map = new HashMap<>();
        map.put("job",this.baseMapper.selectCount(null));
        map.put("job1",this.baseMapper.selectCount(Wrappers.lambdaQuery(RecruitCandidate.class).eq(RecruitCandidate::getCandidateStatus,"1")));
        map.put("job2",this.baseMapper.selectCount(Wrappers.lambdaQuery(RecruitCandidate.class).eq(RecruitCandidate::getCandidateStatus,"2")));
        map.put("job3",this.baseMapper.selectCount(Wrappers.lambdaQuery(RecruitCandidate.class).eq(RecruitCandidate::getCandidateStatus,"3")));
        map.put("job4",this.baseMapper.selectCount(Wrappers.lambdaQuery(RecruitCandidate.class).eq(RecruitCandidate::getCandidateStatus,"4")));
        map.put("job5",this.baseMapper.selectCount(Wrappers.lambdaQuery(RecruitCandidate.class).eq(RecruitCandidate::getCandidateStatus,"5")));
        map.put("job6",this.baseMapper.selectCount(Wrappers.lambdaQuery(RecruitCandidate.class).eq(RecruitCandidate::getCandidateStatus,"6")));
        return map;
    }
}