package org.hret.service.personnel.recruit;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.recruit.po.RecruitJob;

import java.util.List;

/**
 * Authar:liujintao
 * Data:2024/3/18
 * jdk:17
 * @author HRET
 */
public interface RecruitJobService extends IService<RecruitJob> {
    PageInfo<RecruitJob> findPage(RecruitJob recruitJob, int pageNum, int pageSize);

    void updateState(RecruitJob recruitJob);

    void updateJob(RecruitJob recruitJob);

    void addJob(RecruitJob recruitJob);

    void deleteJob(RecruitJob recruitJob);

    List<RecruitJob> findAll();
}
