package org.hret.service.personnel.recruit;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.recruit.RecruitCandidate;
import org.hret.pojo.JsonResult;

import java.util.List;
import java.util.Map;

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
    JsonResult deleteRecruitCandidate(Long candidateId);

    List<RecruitCandidate> findAll();

//    查询条件
    Map<Object, Object> findConditionByNumber();

//    修改人才库状态
    JsonResult updateRecruitcandidateTalentPoolStatus(RecruitCandidate recruitCandidate);
//    查询人才库状态
    Map<Object,Object> findcandidateTalentPoolStatusByNumber();

    JsonResult updateRecruitcandidateState(RecruitCandidate recruitCandidate);
}
