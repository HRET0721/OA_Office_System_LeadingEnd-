package org.hret.service.personnel.documentation;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.documentation.Borrowing;
import org.hret.pojo.JsonResult;

public interface BorrowingService extends IService<Borrowing> {
    PageInfo<Borrowing> findBorrowingPaginationList(Borrowing borrowing);

    PageInfo<Borrowing> findHolidayDalance(Borrowing borrowing);

    JsonResult deleteBorrowing(Long borrowingId);

    JsonResult addBorrowing(Borrowing borrowing);

    JsonResult updateStatus(Borrowing borrowing);
}
