package org.hret.service.personnel.impl.documentation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.documentation.ArchivesDocumentation;
import org.hret.entity.personnel.documentation.Borrowing;
import org.hret.entity.personnel.documentation.Returns;
import org.hret.mapper.personnel.documentation.ArchivesDocumentationMapper;
import org.hret.mapper.personnel.documentation.ReturnMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.documentation.ReturnService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReturnServicelmpl extends ServiceImpl<ReturnMapper, Returns>implements ReturnService {
    private final ReturnMapper returnMapper;
    private final ArchivesDocumentationMapper archivesDocumentationMapper;
    @Override
    public PageInfo<Returns> findReturnPaginationList(Returns returns) {
        PageHelper.startPage(returns.getPageNum(), returns.getPageSize());

        LambdaQueryWrapper<Returns> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (returns.getFileTitle() != null && !"".equals(returns.getFileTitle())) {
            lambdaQueryWrapper.like(Returns::getReturnName, returns.getReturnName());
        }
        List<Returns> returnss = returnMapper.selectList(lambdaQueryWrapper);
        returnss.forEach(item ->{
            ArchivesDocumentation archivesDocumentation = archivesDocumentationMapper.selectById(item.getDocumentationId());
            item.setFileTitle(archivesDocumentation.getFileTitle());
            item.setFileNumber(archivesDocumentation.getFileNumber());
        });
        return new PageInfo<>(returnss);
    }

    @Override
    public JsonResult addReturns(Returns returns) {
        int insert = this.baseMapper.insert(returns);

        if (insert > 0) {
            return JsonResult.ok("添加成功");
        } else {
            return JsonResult.error("添加失败");
        }
    }


    @Override
    public JsonResult updateStatus(Returns returns) {
        int i = this.baseMapper.updateById(returns);

        if (i > 0) {
            return JsonResult.ok("修改状态成功");
        } else {
            return JsonResult.error("修改状态失败");
        }
    }

    @Override
    public JsonResult deleteReturns(Long returnId) {
        int i = this.baseMapper.deleteById(returnId);
        if (i > 0) {
            return JsonResult.ok("删除成功");
        } else {
            return JsonResult.error("删除失败");
        }
    }
}
