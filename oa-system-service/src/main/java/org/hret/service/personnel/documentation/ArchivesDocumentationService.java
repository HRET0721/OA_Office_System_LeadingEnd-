package org.hret.service.personnel.documentation;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.documentation.ArchivesDocumentation;
import org.hret.pojo.JsonResult;

import java.util.List;

public interface ArchivesDocumentationService extends IService<ArchivesDocumentation> {
    PageInfo<ArchivesDocumentation> findDocumentationPaginationList(ArchivesDocumentation archivesDocumentation);

    JsonResult deleteDocumentation(Long documentationId);

    JsonResult addDocumentation(ArchivesDocumentation archivesDocumentation);

    //查询案库方法
    ArchivesDocumentation findDocumentation(Long documentationId);

    JsonResult updateDocumentation(ArchivesDocumentation archivesDocumentation);

    PageInfo<ArchivesDocumentation> find(ArchivesDocumentation archivesDocumentation);

    JsonResult delete(Long documentationId);

    JsonResult findArchivesDocumentationById(Long belongssFile);

    List<ArchivesDocumentation> findAll(ArchivesDocumentation archivesDocumentation);
}
