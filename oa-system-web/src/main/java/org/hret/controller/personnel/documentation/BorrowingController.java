package org.hret.controller.personnel.documentation;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.documentation.Borrowing;
import org.hret.entity.personnel.leave.Holiday;
import org.hret.entity.personnel.leave.HolidayDalance;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.impl.documentation.BorrowingServicelmpl;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
//private final注解
@RequiredArgsConstructor
@RequestMapping(value = "borrowing")
@Tag(name = "借阅方法", description = "借阅方法")
public class BorrowingController {
    private final BorrowingServicelmpl borrowingServicelmpl;
    @RequestMapping(value = "findBorrowingPaginationList", method = RequestMethod.POST)
    @Operation(summary = "查询借阅方法列表和分页",description = "查询借阅方法列表和分页")
    public PageInfo<Borrowing> findBorrowingPaginationList(@RequestBody Borrowing borrowing) {
        return borrowingServicelmpl.findBorrowingPaginationList(borrowing);
    }
    @RequestMapping(value = "findHolidayDalance", method = RequestMethod.POST)
    @Operation(summary = "查询借阅链表方法列表和分页", description = "查询借阅链表方法列表和分页")
    public PageInfo<Borrowing> findHolidayDalance(@RequestBody Borrowing borrowing) {
        return borrowingServicelmpl.findHolidayDalance(borrowing);
    }
    @RequestMapping(value = "deleteBorrowing", method = RequestMethod.POST)
    @Operation(summary = "删除借阅方法", description = "删除借阅方法")
    public JsonResult deleteBorrowing(@RequestParam(value = "borrowingId") Long borrowingId) {
        try {
            return borrowingServicelmpl.deleteBorrowing(borrowingId);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("删除失败");
        }
    }
    @RequestMapping(value = "addBorrowing", method = RequestMethod.POST)
    @Operation(summary = "新增借阅方法", description = "新增借阅方法")
    public JsonResult addBorrowing(@RequestBody Borrowing borrowing){
        try {
            return borrowingServicelmpl.addBorrowing(borrowing);
        } catch (Exception e) {
           e.fillInStackTrace();
           return JsonResult.error("新增失败");
        }
    }

    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    @Operation(summary = "修改借阅状态方法", description = "修改借阅状态方法")
    public JsonResult updateStatus(@RequestBody Borrowing borrowing) {
        try {
            return borrowingServicelmpl.updateStatus(borrowing);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("修改状态失败");
        }
    }

}
