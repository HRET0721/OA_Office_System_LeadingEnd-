package org.hret.service.personnel.impl.documentation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.documentation.ArchivesDocumentation;
import org.hret.entity.personnel.documentation.Borrowing;
import org.hret.mapper.personnel.documentation.ArchivesDocumentationMapper;
import org.hret.mapper.personnel.documentation.BorrowingMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.documentation.BorrowingService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BorrowingServicelmpl extends ServiceImpl<BorrowingMapper, Borrowing> implements BorrowingService {
    private final BorrowingMapper borrowingMapper;
    private final ArchivesDocumentationMapper archivesDocumentationMapper;

    @Override
    public PageInfo<Borrowing> findBorrowingPaginationList(Borrowing borrowing) {
        PageHelper.startPage(borrowing.getPageNum(), borrowing.getPageSize());

        QueryWrapper<Borrowing> queryWrapper = new QueryWrapper<>();
        if (borrowing.getBorrowingName() != null && !"".equals(borrowing.getBorrowingName())) {
            queryWrapper.like("borrowing_name", borrowing.getBorrowingName());
        }
        if (borrowing.getStates() != null && !"".equals(borrowing.getStates())) {
            queryWrapper.like("states", borrowing.getStates());
        }
        if (borrowing.getDateApplication() != null && !"".equals(borrowing.getDateApplication())) {
            queryWrapper.like("date_application", borrowing.getDateApplication());
        }
        if (borrowing.getReturnDate() != null && !"".equals(borrowing.getReturnDate())) {
            queryWrapper.like("return_date", borrowing.getReturnDate());
        }

        return new PageInfo<>(list(queryWrapper));
    }

    @Override
    public PageInfo<Borrowing> findHolidayDalance(Borrowing borrowing) {
         PageHelper.startPage(borrowing.getPageNum(), borrowing.getPageSize());
        LambdaQueryWrapper<Borrowing> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (borrowing.getBorrowingName() != null && !"".equals(borrowing.getBorrowingName())) {
            lambdaQueryWrapper.like(Borrowing::getBorrowingName, borrowing.getBorrowingName());
        }
        List<Borrowing> borrowings = borrowingMapper.selectList(lambdaQueryWrapper);
        borrowings.forEach(item ->{
            ArchivesDocumentation archivesDocumentation = archivesDocumentationMapper.selectById(item.getDocumentationId());
            item.setFileTitle(archivesDocumentation.getFileTitle());
            item.setFileNumber(archivesDocumentation.getFileNumber());
        });
        return new PageInfo<>(borrowings);
    }

    @Override
    public JsonResult deleteBorrowing(Long borrowingId) {
        int i = this.baseMapper.deleteById(borrowingId);
        if (i > 0) {
            return JsonResult.ok("删除成功");
        } else {
            return JsonResult.error("删除失败");
        }
    }

    @Override
    public JsonResult addBorrowing(Borrowing borrowing) {
//        Borrowing borrowing1 = borrowingMapper.selectList(borrowing);
//        ArchivesDocumentation archivesDocumentation = archivesDocumentationMapper.insert(borrowing1.getFileNumber());
        int insert = borrowingMapper.insert(borrowing);
        if (insert > 0) {
            return JsonResult.ok("添加成功");
        } else {
            return JsonResult.error("添加失败");
        }
    }

    @Override
    public JsonResult updateStatus(Borrowing borrowing) {
        int i = this.baseMapper.updateById(borrowing);
        if (i > 0) {
            return JsonResult.ok("修改状态成功");
        } else {
            return JsonResult.error("修改状态失败");
        }
    }
}
