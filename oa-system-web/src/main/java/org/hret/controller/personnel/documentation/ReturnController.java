package org.hret.controller.personnel.documentation;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.documentation.Borrowing;
import org.hret.entity.personnel.documentation.Returns;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.impl.documentation.ReturnServicelmpl;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
//private final注解
@RequiredArgsConstructor
@RequestMapping(value = "returns")
@Tag(name = "归还方法", description = "归还方法")
public class ReturnController {
    private final ReturnServicelmpl returnServicelmpl;

    @RequestMapping(value = "findReturnPaginationList", method = RequestMethod.POST)
    @Operation(summary = "查询归还链表方法列表和分页", description = "查询归还链表方法列表和分页")
    public PageInfo<Returns> findReturnPaginationList(@RequestBody Returns returns) {
        return returnServicelmpl.findReturnPaginationList(returns);
    }

    @RequestMapping(value = "addReturns", method = RequestMethod.POST)
    @Operation(summary = "添加归还方法", description = "添加归还方法")
    public JsonResult addReturns(@RequestBody Returns returns) {
        try {
            return returnServicelmpl.addReturns(returns);
        } catch (Exception e) {
            e.fillInStackTrace();
           return JsonResult.error("添加失败");
        }
    }

    @RequestMapping(value = "deleteReturns", method = RequestMethod.POST)
    @Operation(summary = "删除归还方法", description = "删除归还方法")
    public JsonResult deleteReturns(@RequestParam(value = "returnId") Long returnId) {
        try {
            return returnServicelmpl.deleteReturns(returnId);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("删除失败");
        }
    }
    @RequestMapping(value = "updateStatuss", method = RequestMethod.POST)
    @Operation(summary = "修改归还状态方法", description = "修改归还状态方法")
    public JsonResult updateStatus(@RequestBody Returns returns) {
        try {
            return returnServicelmpl.updateStatus(returns);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("修改状态失败");
        }
    }

}
