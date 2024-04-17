package org.hret.service.personnel.impl.documentation;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hret.entity.personnel.documentation.DocumentationLocker;
import org.hret.mapper.personnel.documentation.DocumentationLockerMapper;
import org.hret.service.personnel.documentation.DocumentationLockerService;
import org.springframework.stereotype.Service;

@Service
public class DocumentationLockerServicelmpl extends ServiceImpl<DocumentationLockerMapper, DocumentationLocker>implements DocumentationLockerService {
}
