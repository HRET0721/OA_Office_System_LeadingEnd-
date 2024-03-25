package org.hret.service.personnel.impl.assess;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.assess.IndexTemplate;
import org.hret.mapper.personnel.assess.IndexTemplateMapper;
import org.hret.service.personnel.assess.IndexTemplateService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndexTemplateServiceImpl extends ServiceImpl<IndexTemplateMapper, IndexTemplate> implements IndexTemplateService {
}
