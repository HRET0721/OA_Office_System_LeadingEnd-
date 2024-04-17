package org.hret.service.personnel.impl.leave;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hret.entity.personnel.leave.OperateDept;
import org.hret.mapper.personnel.leave.OperateDeptMapper;
import org.hret.service.personnel.leave.OperateDeptService;
import org.springframework.stereotype.Service;

@Service
public class OperateDeptServicelmpl extends ServiceImpl<OperateDeptMapper, OperateDept>implements OperateDeptService {
}
