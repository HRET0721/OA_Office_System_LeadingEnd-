package org.hret.service.personnel.recruit;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.recruit.RecruitJob;
import org.hret.pojo.JsonResult;

import java.util.List;

/**
 * Authar:liujintao
 * Data:2024/3/18
 * jdk:17
 */
public interface RecruitJobService extends IService<RecruitJob> {
    PageInfo<RecruitJob> findPage(RecruitJob recruitJob);

    JsonResult updateState(RecruitJob recruitJob);

    JsonResult updateJob(RecruitJob recruitJob);

    JsonResult addJob(RecruitJob recruitJob);

    JsonResult deleteJob(Long JobId);

    List<RecruitJob> findAll();
}
