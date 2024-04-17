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
import org.hret.mapper.personnel.documentation.WholeSectMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.documentation.WholeSectService;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class WholeSectServicelmpl extends ServiceImpl<WholeSectMapper, WholeSect>implements WholeSectService {
    private final ArchivesDocumentationMapper archivesDocumentationMapper;
    private final WholeSectMapper wholeSectMapper;

    @Override
    public PageInfo<WholeSect> findWholePaginationList(WholeSect wholeSect) {
        PageHelper.startPage(wholeSect.getPageNum(), wholeSect.getPageSize());
        LambdaQueryWrapper<WholeSect> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (wholeSect.getWholeSectName() != null && !"".equals(wholeSect.getWholeSectName())) {
        }
        lambdaQueryWrapper.like(WholeSect::getWholeSectName, wholeSect.getWholeSectName());

        if (wholeSect.getAffiliation() != null && !"creation_time".equals(wholeSect.getAffiliation())) {
        }
        lambdaQueryWrapper.like(WholeSect::getAffiliation, wholeSect.getAffiliation());
        List<WholeSect> wholeSects = wholeSectMapper.selectList(lambdaQueryWrapper);
        wholeSects.forEach(item -> {
            ArchivesDocumentation archivesDocumentation = archivesDocumentationMapper.selectById(item.getDocumentationId());
//            item.setDocumentationId(archivesDocumentation.getDocumentationId());
            item.setBelongssFile(archivesDocumentation.getBelongssFile());

        });
        return new PageInfo<>(wholeSects);

    }

    @Override
    public JsonResult addWhole(WholeSect wholeSect) {
        int insert = this.baseMapper.insert(wholeSect);
        if (insert > 0) {
            return JsonResult.ok("添加成功");
        } else {
            return JsonResult.error("添加失败");
        }
    }

    @Override
    public JsonResult deleteWhole(Long wholeSectId) {
        WholeSect wholeSect = wholeSectMapper.selectById(wholeSectId);
        Integer documentationId = wholeSect.getWholeSectId();
        wholeSectMapper.deleteById(wholeSectId);
        archivesDocumentationMapper.deleteById(documentationId);
        return JsonResult.ok("删除成功");
    }

    @Override
    public WholeSect findWhole(Long wholeSectId) {
        return getById(wholeSectId);
    }

    @Override
    public JsonResult updateWhole(WholeSect wholeSect) {
        int i = this.baseMapper.updateById(wholeSect);
        if (i > 0){
            return JsonResult.ok("修改成功");
        }else {
            return JsonResult.ok("修改失败");
        }
    }
}

