package org.hret.controller.personnel.recruit;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hret.entity.personnel.recruit.RecruitCandidate;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.recruit.RecruitCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authar:liujintao
 * Data:2024/3/21
 * jdk:17
 */
@CrossOrigin
@RestController
@RequestMapping(value = "recruitCandidate")
@Tag(name = "候选人管理", description = "候选人管理")
public class RecruitCandidateController {
    @Autowired
    private RecruitCandidateService recruitCandidateService;

    @Operation(summary = "查询候选人", description = "查询候选人")
    @RequestMapping(value = "findByRecruitCandidatePage", method = RequestMethod.POST)
    public JsonResult findByRecruitCandidatePage(RecruitCandidate recruitCandidate) {
        return JsonResult.ok("查询成功", recruitCandidateService.findByRecruitCandidatePage(recruitCandidate));
    }
}
