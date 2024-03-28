package org.hret.service.personnel.impl.assess;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.assess.DeptAssess;
import org.hret.mapper.personnel.assess.DeptAssessMapper;
import org.hret.service.personnel.assess.DeptAssessService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeptAssessServiceImpl extends ServiceImpl<DeptAssessMapper, DeptAssess> implements DeptAssessService {
}
