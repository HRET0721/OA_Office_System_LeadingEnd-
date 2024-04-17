package org.hret.service.personnel.impl.leave;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hret.entity.personnel.leave.OperateBalance;
import org.hret.mapper.personnel.leave.OperateBalanceMapper;
import org.hret.service.personnel.leave.OperateBalanceService;
import org.springframework.stereotype.Service;

@Service
public class OperateBalanceServicelmpl extends ServiceImpl<OperateBalanceMapper, OperateBalance>implements OperateBalanceService {
}
