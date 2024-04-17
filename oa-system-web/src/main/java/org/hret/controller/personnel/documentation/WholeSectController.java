package org.hret.controller.personnel.documentation;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.documentation.Returns;
import org.hret.entity.personnel.documentation.WholeSect;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.impl.documentation.WholeSectServicelmpl;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "whole")
@Tag(name = "全宗管理", description = "全宗管理")
public class WholeSectController {
    private final WholeSectServicelmpl wholeSectServicelmpl;
    @RequestMapping(value = "findWholePaginationList", method = RequestMethod.POST)
    @Operation(summary = "查询全宗列表和分页", description = "查询归还链表方法列表和分页")
    public PageInfo<WholeSect> findWholePaginationList(@RequestBody WholeSect wholeSect) {
        return wholeSectServicelmpl.findWholePaginationList(wholeSect);
    }
    @RequestMapping(value = "addWhole", method = RequestMethod.POST)
    @Operation(summary = "新增全宗", description = "新增全宗")
    public JsonResult addWhole(@RequestBody WholeSect wholeSect){
        try {
//            自动新增id
            wholeSect.setDocumentationId(1);
//            自动新增时间
            wholeSect.setCreationTime(new Date());
            wholeSect.setUpdated(new Date());
            return wholeSectServicelmpl.addWhole(wholeSect);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("新增失败");

        }
    }
    @RequestMapping(value = "deleteWhole", method = RequestMethod.POST)
    @Operation(summary = "删除全宗方法", description = "删除全宗方法")
    public JsonResult deleteWhole(@RequestParam(value = "wholeSectId")Long wholeSectId){
        try {
            return wholeSectServicelmpl.deleteWhole(wholeSectId);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("删除失败");
        }
    }
    @RequestMapping(value = "findWhole", method = RequestMethod.POST)
    @Operation(summary = "回显全宗方法", description = "回显全宗方法")
    public WholeSect findWhole(@RequestParam(value ="wholeSectId" )Long wholeSectId){
        return wholeSectServicelmpl.findWhole(wholeSectId);
    }
    @RequestMapping(value = "updateWhole", method = RequestMethod.POST)
    @Operation(summary = "修改全宗方法", description = "修改全宗方法")
    public JsonResult updateWhole(@RequestBody WholeSect wholeSect){
        try {
            return wholeSectServicelmpl.updateWhole(wholeSect);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("修改失败");
        }
    }

}
