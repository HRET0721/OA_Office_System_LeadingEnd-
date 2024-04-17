package org.hret.service.personnel.impl.leave;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hret.entity.personnel.leave.OperateUser;
import org.hret.mapper.personnel.leave.OperateUserMapper;
import org.hret.service.personnel.leave.OperateUserService;
import org.springframework.stereotype.Service;

@Service
public class OperateUserServicelmpl extends ServiceImpl<OperateUserMapper, OperateUser>implements OperateUserService {
}
