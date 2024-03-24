package org.hret.service.personnel.recruit;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.recruit.RecruitCandidate;
import org.hret.pojo.JsonResult;

import java.util.List;

/**
 * Authar:liujintao
 * Data:2024/3/21
 * jdk:17
 */
public interface RecruitCandidateService extends IService<RecruitCandidate> {
    //    分页加条件查询
    PageInfo<RecruitCandidate> findByRecruitCandidatePage(RecruitCandidate recruitCandidate);

    //修改状态，面试是否通过
    JsonResult updateRecruitCandidateStatus(RecruitCandidate recruitCandidate);

    //    更新数据
    JsonResult updateRecruitCandidate(RecruitCandidate recruitCandidate);

    //    添加数据
    JsonResult addRecruitCandidate(RecruitCandidate recruitCandidate);

    //    删除数据
    JsonResult deleteRecruitCandidate(RecruitCandidate recruitCandidate);

    List<RecruitCandidate> findAll();
}
