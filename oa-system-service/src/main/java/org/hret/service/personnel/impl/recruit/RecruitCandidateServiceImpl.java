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
            wrapper.ge(RecruitCandidate::getCandidateTime, recruitCandidate.getCandidateStartTime());
        }
        if (recruitCandidate.getCandidateEndTime() != null && !recruitCandidate.getCandidateEndTime().isEmpty()){
            wrapper.le(RecruitCandidate::getCandidateTime, recruitCandidate.getCandidateEndTime());
        }
//        状态条件查询
        if (recruitCandidate.getCandidateStatus() != null && !recruitCandidate.getCandidateStatus().isEmpty()){
            wrapper.eq(RecruitCandidate::getCandidateStatus, recruitCandidate.getCandidateStatus());
        }

        if (recruitCandidate.getCandidateTalentPoolStatus() != null && !recruitCandidate.getCandidateTalentPoolStatus().isEmpty()) {
            wrapper.eq(RecruitCandidate::getCandidateTalentPoolStatus, recruitCandidate.getCandidateTalentPoolStatus());
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
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        recruitCandidate.setCandidateTime(format);
        System.out.println(1);
        int insert = this.baseMapper.insert(recruitCandidate);
        System.out.println(insert);
        if (insert>0){
            System.out.println("1");
            return JsonResult.ok("添加成功");
        }else {
            System.out.println("2");
            return JsonResult.error("添加失败");
        }
    }

    @Override
    public JsonResult deleteRecruitCandidate(Long candidateId) {
        int i = this.baseMapper.deleteById(candidateId);
        if (i>0){
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

    @Override
    public JsonResult updateRecruitcandidateTalentPoolStatus(RecruitCandidate recruitCandidate) {
        int update = this.baseMapper.update(recruitCandidate, Wrappers.lambdaUpdate(RecruitCandidate.class).eq(RecruitCandidate::getCandidateId, recruitCandidate.getCandidateId()).set(RecruitCandidate::getCandidateTalentPoolStatus, recruitCandidate.getCandidateTalentPoolStatus()));
        if (update > 0){
            return JsonResult.ok("更新成功");
        }else {
            return JsonResult.error("更新失败");
        }
    }

    @Override
    public Map<Object, Object> findcandidateTalentPoolStatusByNumber() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("sum",this.baseMapper.selectCount(null));
        map.put("sum1",this.baseMapper.selectCount(Wrappers.lambdaQuery(RecruitCandidate.class).eq(RecruitCandidate::getCandidateTalentPoolStatus,"1")));
        map.put("sum2",this.baseMapper.selectCount(Wrappers.lambdaQuery(RecruitCandidate.class).eq(RecruitCandidate::getCandidateTalentPoolStatus,"2")));
        map.put("sum3",this.baseMapper.selectCount(Wrappers.lambdaQuery(RecruitCandidate.class).eq(RecruitCandidate::getCandidateTalentPoolStatus,"3")));
        map.put("sum4",this.baseMapper.selectCount(Wrappers.lambdaQuery(RecruitCandidate.class).eq(RecruitCandidate::getCandidateTalentPoolStatus,"4")));
        map.put("sum5",this.baseMapper.selectCount(Wrappers.lambdaQuery(RecruitCandidate.class).eq(RecruitCandidate::getCandidateTalentPoolStatus,"5")));
        return map;
    }

    @Override
    public JsonResult updateRecruitcandidateState(RecruitCandidate recruitCandidate) {
        int update = this.baseMapper.update(recruitCandidate, Wrappers.lambdaUpdate(RecruitCandidate.class).eq(RecruitCandidate::getCandidateId, recruitCandidate.getCandidateId()).set(RecruitCandidate::getCandidateState, recruitCandidate.getCandidateState()));
        if (update > 0){
            return JsonResult.ok("更新成功");
        }else {
            return JsonResult.error("更新失败");
        }
    }
}