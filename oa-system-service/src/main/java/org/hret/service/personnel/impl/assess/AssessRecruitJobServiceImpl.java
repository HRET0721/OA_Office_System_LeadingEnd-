package org.hret.service.personnel.impl.assess;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.assess.AssessRecruitJob;
import org.hret.mapper.personnel.assess.AssessRecruitJobMapper;
import org.hret.service.personnel.assess.AssessRecruitJobService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssessRecruitJobServiceImpl extends ServiceImpl<AssessRecruitJobMapper, AssessRecruitJob> implements AssessRecruitJobService {
}
