package org.hret.service.personnel.impl.documentation;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hret.entity.personnel.documentation.DocumentationBorrowing;
import org.hret.mapper.personnel.documentation.DocumentationBorrowingMapper;
import org.hret.service.personnel.documentation.DocumentationBorrowingService;
import org.springframework.stereotype.Service;

@Service
public class DocumentationBorrowingServicelmpl extends ServiceImpl<DocumentationBorrowingMapper, DocumentationBorrowing>implements DocumentationBorrowingService {
}
