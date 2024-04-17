package org.hret.service.personnel.impl.documentation;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hret.entity.personnel.documentation.WholeLocker;
import org.hret.mapper.personnel.documentation.WholeLockerMapper;
import org.hret.service.personnel.documentation.WholeLockerService;
import org.springframework.stereotype.Service;

@Service
public class WholeLockerServicelmpl extends ServiceImpl<WholeLockerMapper, WholeLocker>implements WholeLockerService {
}
