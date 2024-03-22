package org.hret.service.personnel.recruit;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.recruit.RecruitCandidate;

/**
 * Authar:liujintao
 * Data:2024/3/21
 * jdk:17
 */
public interface RecruitCandidateService extends IService<RecruitCandidate> {
    PageInfo<RecruitCandidate> findByRecruitCandidatePage(RecruitCandidate recruitCandidate);
}
