package org.hret.controller.personnel.assess;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.assess.Assess;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.assess.AssessService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "assess")
@Tag(name = "考核方法", description = "考核方法")
public class AssessController {
    private final AssessService assessService;
    @RequestMapping(value = "findAssessListAndPage", method = RequestMethod.POST)
    @Operation(summary = "查询考核方法列表和分页",description = "查询考核列表和分页")
    public JsonResult findAssessListAndPage(@RequestBody Assess assess, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        return assessService.findAssessListAndPage(assess, pageNum, pageSize);
    }
    @RequestMapping(value = "addAssessMent",method = RequestMethod.POST)
    @Operation(summary = "添加考核方法",description = "添加考核方法")
    public JsonResult addAssessMent(@RequestBody Assess assess) {
        try {
            assessService.addAssessMent(assess);
            return JsonResult.ok("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("添加失败");
        }
    }
    @RequestMapping(value = "deleteAssessMent",method = RequestMethod.GET)
    @Operation(summary = "删除考核方法",description = "删除考核方法")
    public JsonResult deleteAssessMent(@RequestParam(value = "assessId") Long assessId) {
        try {
            assessService.deleteAssessMent(assessId);
            return JsonResult.ok( "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
    }
    @RequestMapping(value = "findAeeseeMentById",method = RequestMethod.GET)
    @Operation(summary = "根据考核方法id查询考核方法",description = "根据考核方法id查询考核方法")
    public Assess findAeeseeMentById(@RequestParam(value = "assessId") Long assessId) {
        return assessService.findAeeseeMentById(assessId);
    }
    @RequestMapping(value = "updateAssessMent",method = RequestMethod.POST)
    @Operation(summary = "修改考核方法",description = "修改考核方法")
    public JsonResult updateAssessMent(@RequestBody Assess assess) {
        try {
            assessService.updateAssessMent(assess);
            return JsonResult.ok( "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("修改失败");
        }
    }
}
