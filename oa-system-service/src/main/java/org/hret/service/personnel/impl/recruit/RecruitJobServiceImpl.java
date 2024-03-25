package org.hret.service.personnel.impl.recruit;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.recruit.RecruitJob;
import org.hret.mapper.personnel.recruit.RecruitJobMapper;
import org.hret.pojo.JsonResult;
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
    public PageInfo<RecruitJob> findPage(RecruitJob recruitJob) {
        // 开启分页插件
        PageHelper.startPage(recruitJob.getPageNum(),recruitJob.getPageSize());
//        lambda查询
        LambdaQueryWrapper<RecruitJob> wrapper = Wrappers.lambdaQuery(RecruitJob.class);

//        条查
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
    public JsonResult updateState(RecruitJob recruitJob) {
        int update = this.baseMapper.update(null, Wrappers.lambdaUpdate(RecruitJob.class).eq(RecruitJob::getJobId, recruitJob.getJobId()).set(RecruitJob::getJobStatus, recruitJob.getJobStatus()));
        if (update > 0){
            return JsonResult.ok("更新成功");
        }else {
            return JsonResult.error("更新失败");
        }
    }

    @Override
    public JsonResult updateJob(RecruitJob recruitJob) {
        boolean b = this.updateById(recruitJob);
        if (b){
            return JsonResult.ok("更新成功");
        }else {
            return JsonResult.error("更新失败");
        }
    }

    @Override
    public JsonResult addJob(RecruitJob recruitJob) {
        recruitJob.setJobStatus("1");
        Date date = new Date();
        String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
        recruitJob.setJobTime(format);
        int insert = this.baseMapper.insert(recruitJob);
        if (insert > 0){
            return JsonResult.ok("添加成功");
        }else {
            return JsonResult.error("添加失败");
        }
    }

    @Override
    public JsonResult deleteJob(RecruitJob recruitJob) {
        boolean b = this.removeById(recruitJob);
        if (b){
            return JsonResult.ok("删除成功");
        }else {
            return JsonResult.error("删除失败");
        }
    }

    @Override
    public List<RecruitJob> findAll() {
        return list();
    }
}
