package org.hret.service.personnel.impl.documentation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.documentation.ArchivesDocumentation;
import org.hret.entity.personnel.documentation.Borrowing;
import org.hret.entity.personnel.documentation.CaseLocker;
import org.hret.entity.personnel.documentation.WholeSect;
import org.hret.mapper.personnel.documentation.ArchivesDocumentationMapper;
import org.hret.mapper.personnel.documentation.CaseLockerMapper;
import org.hret.mapper.personnel.documentation.WholeSectMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.documentation.CaseLockerService;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class CaseLockerServicelmpl extends ServiceImpl<CaseLockerMapper, CaseLocker>implements CaseLockerService {
    private final CaseLockerMapper caseLockerMapper;
    private final ArchivesDocumentationMapper archivesDocumentationMapper;
    private final WholeSectMapper wholeSectMapper;

    @Override
    public PageInfo<CaseLocker> findLockerPaginationList(CaseLocker caseLocker) {
        PageHelper.startPage(caseLocker.getPageNum(), caseLocker.getPageSize());
        QueryWrapper<CaseLocker> queryWrapper = new QueryWrapper<>();
        if (caseLocker.getLockName() != null && !"".equals(caseLocker.getLockName())) {
            queryWrapper.like("lock_name", caseLocker.getLockName());
        }
        LambdaQueryWrapper<CaseLocker> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (caseLocker.getLockName() != null && !"".equals(caseLocker.getLockName())) {
            lambdaQueryWrapper.like(CaseLocker::getLockName, caseLocker.getLockName());
        }
        List<CaseLocker> caseLockers = caseLockerMapper.selectList(lambdaQueryWrapper);
        caseLockers.forEach(item -> {
            ArchivesDocumentation archivesDocumentation = archivesDocumentationMapper.selectById(item.getDocumentationId());
            item.setAscriptionSect(archivesDocumentation.getAscriptionSect());
            WholeSect wholeSect = wholeSectMapper.selectById(item.getWholeSectId());
            item.setAffiliation(wholeSect.getAffiliation());
        });
        return new PageInfo<>(caseLockers);
    }

    @Override
    public JsonResult deleteLocker(Long lockerId) {
        int i = this.baseMapper.deleteById(lockerId);
        if (i > 0) {
            return JsonResult.ok("删除成功");
        } else {
            return JsonResult.error("删除失败");
        }
    }

    @Override
    public JsonResult addLocker(CaseLocker caseLocker) {
        int insert = this.baseMapper.insert(caseLocker);
        if (insert > 0) {
            return JsonResult.ok("添加成功");
        } else {
            return JsonResult.error("添加失败");
        }
    }

    @Override
    public CaseLocker findLock(Long lockerId) {
        return getById(lockerId);
    }

    @Override
    public JsonResult updateLocker(CaseLocker caseLocker) {
        int i = this.baseMapper.updateById(caseLocker);
        if (i > 0){
            return JsonResult.ok("修改成功");
        }else {
            return JsonResult.ok("修改失败");
        }
    }

    @Override
    public JsonResult findLockerPaginationListById(Long affiliationss) {
        // 1. 根据给定ID查询单个档案文档信息
        CaseLocker caseLocker = this.baseMapper.selectById(affiliationss);

        if (caseLocker == null) {
            return JsonResult.error("未找到指定ID的档案文档");
        }
        // 2. 查询与档案文档关联的完整章节名称和案件锁名称，并设置到档案文档对象中
        Integer wholeSectId = caseLocker.getWholeSectId();
        WholeSect wholeSect = (wholeSectId != null) ? wholeSectMapper.selectById(wholeSectId) : null;
        if (wholeSect != null) {
            caseLocker.setWholeSectName(wholeSect.getWholeSectName());
        } else {
            // 如果找不到对应的完整章节，可以决定是否抛出异常、返回错误信息，或者使用默认值等
            // 这里仅作为示例，返回一个空字符串
            caseLocker.setWholeSectName("");
            // 或者也可以选择返回一个错误信息：
            // return JsonResult.error("500", "未找到与档案文档关联的完整章节");
        }
        return JsonResult.ok("200", caseLocker);
    }

    @Override
    public List<CaseLocker> findAlll(CaseLocker caseLocker) {
        List<CaseLocker> list = caseLockerMapper.selectList(null);
        return list;
    }
}
