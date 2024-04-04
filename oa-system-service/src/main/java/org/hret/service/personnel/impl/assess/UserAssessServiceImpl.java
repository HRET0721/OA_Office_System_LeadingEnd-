package org.hret.service.personnel.impl.assess;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.assess.UserAssess;
import org.hret.mapper.personnel.assess.UserAssessMapper;
import org.hret.service.personnel.assess.UserAssessService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAssessServiceImpl extends ServiceImpl<UserAssessMapper, UserAssess> implements UserAssessService {
}
