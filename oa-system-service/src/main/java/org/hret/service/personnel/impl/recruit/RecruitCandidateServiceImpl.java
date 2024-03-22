package org.hret.service.personnel.impl.recruit;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.recruit.RecruitCandidate;
import org.hret.mapper.personnel.recruit.RecruitCandidateMapper;
import org.hret.service.personnel.recruit.RecruitCandidateService;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
