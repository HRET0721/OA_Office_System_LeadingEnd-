package org.hret.service.personnel.impl.recruit;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.recruit.po.RecruitJob;
import org.hret.mapper.personnel.recruit.RecruitJobMapper;
import org.hret.service.personnel.recruit.RecruitJobService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Authar:liujintao
 * Data:2024/3/18
 * jdk:17
 */
@Service
public class RecruitJobServiceImpl extends ServiceImpl<RecruitJobMapper, RecruitJob> implements RecruitJobService {

    @Override
    public PageInfo<RecruitJob> findPage(RecruitJob recruitJob, int pageNum, int pageSize) {
        // 开启分页插件
        PageHelper.startPage(pageNum, pageSize);

        LambdaQueryWrapper<RecruitJob> wrapper = Wrappers.lambdaQuery(RecruitJob.class);

        if (recruitJob.getJobPrincipal() != null && !recruitJob.getJobPrincipal().isEmpty()) {
            wrapper.eq(RecruitJob::getJobPrincipal, recruitJob.getJobPrincipal());
        }

        if (recruitJob.getJobDept() != null && !recruitJob.getJobDept().isEmpty()) {
            wrapper.eq(RecruitJob::getJobDept, recruitJob.getJobDept());
        }
        if (recruitJob.getJobNature() != null && !recruitJob.getJobNature().isEmpty()) {
            wrapper.eq(RecruitJob::getJobNature, recruitJob.getJobNature());
        }
        if (recruitJob.getJobEducation() != null && !recruitJob.getJobEducation().isEmpty()) {
            wrapper.eq(RecruitJob::getJobEducation, recruitJob.getJobEducation());
        }
        if (recruitJob.getJobStartTime() != null && !recruitJob.getJobStartTime().isEmpty()) {
            wrapper.ge(RecruitJob::getJobTime, recruitJob.getJobStartTime());
        }
        if (recruitJob.getJobEndTime() != null && !recruitJob.getJobEndTime().isEmpty()) {
            wrapper.le(RecruitJob::getJobTime, recruitJob.getJobEndTime());
        }

        // 执行查询并返回结果
        List<RecruitJob> resultList = this.list(wrapper);

        // 使用PageHelper自动包装的Page对象
        return new PageInfo<>(resultList);
    }

    @Override
    public void updateState(RecruitJob recruitJob) {
        this.baseMapper.update(null,Wrappers.lambdaUpdate(RecruitJob.class).eq(RecruitJob::getJobId,recruitJob.getJobId()).set(RecruitJob::getJobStatus,recruitJob.getJobStatus()));
    }

    @Override
    public void updateJob(RecruitJob recruitJob) {
        this.updateById(recruitJob);
    }

    @Override
    public void addJob(RecruitJob recruitJob) {
        recruitJob.setJobStatus("1");
        Date date = new Date();
        String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
        recruitJob.setJobTime(format);
        this.baseMapper.insert(recruitJob);
    }

    @Override
    public void deleteJob(RecruitJob recruitJob) {
        this.removeById(recruitJob);
    }

    @Override
    public List<RecruitJob> findAll() {
        return this.baseMapper.selectList(null);
    }
}
