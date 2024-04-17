package org.hret.service.personnel.impl.documentation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.documentation.ArchivesDocumentation;
import org.hret.entity.personnel.documentation.CaseLocker;
import org.hret.entity.personnel.documentation.WholeSect;
import org.hret.mapper.personnel.documentation.ArchivesDocumentationMapper;
import org.hret.mapper.personnel.documentation.CaseLockerMapper;
import org.hret.mapper.personnel.documentation.WholeSectMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.documentation.ArchivesDocumentationService;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class ArchivesDocumentationServicelmpl extends ServiceImpl<ArchivesDocumentationMapper, ArchivesDocumentation>implements ArchivesDocumentationService {
    private final ArchivesDocumentationMapper archivesDocumentationMapper;
    private final WholeSectMapper wholeSectMapper;
    private final CaseLockerMapper caseLockerMapper;
    @Override
    public PageInfo<ArchivesDocumentation> find(ArchivesDocumentation archivesDocumentation) {
        PageHelper.startPage(archivesDocumentation.getPageNum(), archivesDocumentation.getPageSize());
        QueryWrapper<ArchivesDocumentation> queryWrapper = new QueryWrapper<>();
        if (archivesDocumentation.getBelongssFile() != null && !"".equals(archivesDocumentation.getBelongssFile())) {
            queryWrapper.like("belongss_file", archivesDocumentation.getBelongssFile());
        }
        LambdaQueryWrapper<ArchivesDocumentation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (archivesDocumentation.getFileName() != null && !"".equals(archivesDocumentation.getFileName())) {
        }
        List<ArchivesDocumentation> archivesDocumentations = archivesDocumentationMapper.selectList(lambdaQueryWrapper);
        archivesDocumentations.forEach(item ->{
            WholeSect wholeSect = wholeSectMapper.selectById(item.getWholeSectId());
            item.setWholeSectName(wholeSect.getWholeSectName());
            CaseLocker caseLocker = caseLockerMapper.selectById(item.getLockerId());
            item.setLockName(caseLocker.getLockName());
        });
        return new PageInfo<>(archivesDocumentations);
    }

    /**
     * 表表查询单个档案文档信息
     * @param belongssFile
     * @return
     */
    @Override
    public JsonResult findArchivesDocumentationById(Long belongssFile) {
        // 1. 根据给定ID查询单个档案文档信息
        ArchivesDocumentation archiveDocumentation = this.baseMapper.selectById(belongssFile);

        if (archiveDocumentation == null) {
            return JsonResult.error("未找到指定ID的档案文档");
        }

        // 2. 查询与档案文档关联的完整章节名称和案件锁名称，并设置到档案文档对象中
        Integer wholeSectId = archiveDocumentation.getWholeSectId();
        WholeSect wholeSect = (wholeSectId != null) ? wholeSectMapper.selectById(wholeSectId) : null;

        if (wholeSect != null) {
            archiveDocumentation.setWholeSectName(wholeSect.getWholeSectName());
        } else {
            // 如果找不到对应的完整章节，可以决定是否抛出异常、返回错误信息，或者使用默认值等
            // 这里仅作为示例，返回一个空字符串
            archiveDocumentation.setWholeSectName("");
            // 或者也可以选择返回一个错误信息：
            // return JsonResult.error("500", "未找到与档案文档关联的完整章节");
        }

        Integer lockerId = archiveDocumentation.getLockerId();
        CaseLocker caseLocker = (lockerId != null) ? caseLockerMapper.selectById(lockerId) : null;

        if (caseLocker != null) {
            archiveDocumentation.setLockName(caseLocker.getLockName());
        } else {
            // 同样，这里可以根据实际情况处理找不到案件锁的情况
            archiveDocumentation.setLockName("");
            // 或者返回错误信息：
            // return JsonResult.error("500", "未找到与档案文档关联的案件锁");
        }

        // 3. 返回包含档案文档信息的JsonResult
        return JsonResult.ok("200", archiveDocumentation);
    }


    @Override
    public List<ArchivesDocumentation> findAll(ArchivesDocumentation archivesDocumentation) {
        List<ArchivesDocumentation> list = archivesDocumentationMapper.selectList(null);
        return list;
    }

    //根据id删除每条数据
    @Override
    public JsonResult delete(Long documentationId) {
        ArchivesDocumentation archivesDocumentation = archivesDocumentationMapper.selectById(documentationId);
        Integer wholeSectId = archivesDocumentation.getWholeSectId();
        Integer lockerId = archivesDocumentation.getLockerId();
//        通过案卷id删除
        archivesDocumentationMapper.deleteById(documentationId);
        wholeSectMapper.deleteById(wholeSectId);
        caseLockerMapper.deleteById(lockerId);
        return JsonResult.ok("删除成功");

    }



    @Override
    public PageInfo<ArchivesDocumentation> findDocumentationPaginationList(ArchivesDocumentation archivesDocumentation) {
        PageHelper.startPage(archivesDocumentation.getPageNum(), archivesDocumentation.getPageSize());

        QueryWrapper<ArchivesDocumentation> queryWrapper = new QueryWrapper<>();
        if ( archivesDocumentation.getDocumentationId() != null && !"".equals(archivesDocumentation.getDocumentationId()) ) {
            queryWrapper.like("documentation_id", archivesDocumentation.getDocumentationId());
        }
        if ( archivesDocumentation.getFileNumber() != null && !"".equals(archivesDocumentation.getFileNumber()) ) {
            queryWrapper.like("file_number", archivesDocumentation.getFileNumber());
        }
        if ( archivesDocumentation.getFileTitle() != null && !"".equals(archivesDocumentation.getFileTitle())) {
            queryWrapper.like("file_title", archivesDocumentation.getFileTitle());
        }
        if ( archivesDocumentation.getStates() != null && !"".equals(archivesDocumentation.getStates())) {
            queryWrapper.like("states", archivesDocumentation.getStates());
        }
        if ( archivesDocumentation.getSmallStates() != null && !"".equals(archivesDocumentation.getSmallStates())) {
            queryWrapper.like("small_states", archivesDocumentation.getSmallStates());
        }
        if ( archivesDocumentation.getCreationTime() != null && !"".equals(archivesDocumentation.getCreationTime()) ) {
            queryWrapper.eq("creation_time", archivesDocumentation.getCreationTime());
        }
        if ( archivesDocumentation.getUpdated() != null && !"".equals(archivesDocumentation.getUpdated()) ) {
            queryWrapper.eq("updated", archivesDocumentation.getUpdated());
        }

        return new PageInfo<>(list(queryWrapper));
    }

    @Override
    public JsonResult deleteDocumentation(Long documentationId) {
        int i = this.baseMapper.deleteById(documentationId);
        if (i > 0) {
            return JsonResult.ok("删除成功");
        } else {
            return JsonResult.error("删除失败");
        }


    }

    @Override
    public JsonResult addDocumentation(ArchivesDocumentation archivesDocumentation) {
        int insert = this.baseMapper.insert(archivesDocumentation);

        if (insert > 0) {
            return JsonResult.ok("添加成功");
        } else {
            return JsonResult.error("添加失败");
        }
    }

    @Override
    public ArchivesDocumentation findDocumentation(Long documentationId) {
        return getById(documentationId);
    }

    @Override
    public JsonResult updateDocumentation(ArchivesDocumentation archivesDocumentation) {
        int i = this.baseMapper.updateById(archivesDocumentation);

        if (i > 0) {
            return JsonResult.ok("修改成功");
        } else {
            return JsonResult.error("修改失败");
        }
    }


}
