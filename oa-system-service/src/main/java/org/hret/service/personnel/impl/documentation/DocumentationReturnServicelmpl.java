package org.hret.service.personnel.impl.documentation;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hret.entity.personnel.documentation.DocumentationReturn;
import org.hret.mapper.personnel.documentation.DocumentationReturnMapper;
import org.hret.service.personnel.documentation.DocumentationBorrowingService;
import org.hret.service.personnel.documentation.DocumentationReturnService;
import org.springframework.stereotype.Service;

@Service
public class DocumentationReturnServicelmpl extends ServiceImpl<DocumentationReturnMapper, DocumentationReturn>implements DocumentationReturnService {
}
