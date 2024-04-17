package org.hret.controller.personnel.assess;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.hret.entity.personnel.assess.Assess;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.assess.AssessService;
import org.springframework.web.bind.annotation.*;

/**
 * @author HRET
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "assess")
@Tag(name = "考核方法", description = "考核方法")
public class AssessController {
    private final AssessService assessService;
    @RequestMapping(value = "findAssessListAndPage", method = RequestMethod.POST)
    @Operation(summary = "查询考核方法列表和分页",description = "查询考核列表和分页")
    public PageInfo<Assess> findAssessListAndPage(@RequestBody Assess assess) {
        return assessService.findAssessListAndPage(assess);
    }
    @RequestMapping(value = "findAssessAndUserListAndPage",method = RequestMethod.POST)
    @Operation(summary = "查询考核方法和用户列表和分页",description = "查询考核列表和分页")
    public PageInfo<Assess> findAssessAndUserListAndPage(@RequestBody Assess assess) {
        return assessService.findAssessAndUserListAndPage(assess);
    }
    @RequestMapping(value = "addAssessMent",method = RequestMethod.POST)
    @Operation(summary = "添加考核方法",description = "添加考核方法")
    public JsonResult addAssessMent(@RequestBody Assess assess) {
        try {
            return assessService.addAssessMent(assess);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("添加失败");
        }
    }
    @RequestMapping(value = "deleteAssessMent",method = RequestMethod.GET)
    @Operation(summary = "删除考核方法",description = "删除考核方法")
    public JsonResult deleteAssessMent(@RequestParam(value = "assessId") Long assessId) {
        try {
            return assessService.deleteAssessMent(assessId);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("删除失败");
        }
    }
    @RequestMapping(value = "findAssessMentById",method = RequestMethod.GET)
    @Operation(summary = "根据考核方法id查询考核方法",description = "根据考核方法id查询考核方法")
    public Assess findAssessMentById(@RequestParam(value = "assessId") Long assessId) {
        return assessService.findAeeseeMentById(assessId);
    }
    @RequestMapping(value = "findUserIdAndAssessId",method = RequestMethod.POST)
    @Operation(summary = "根据考核方法id查询考核方法",description = "根据考核方法id查询考核方法")
    public Assess findUserIdAndAssess(@RequestParam(value = "userId") Integer userId) {
        return assessService.findUserIdAndAssess(userId);
    }
    @RequestMapping(value = "updateAssessMent",method = RequestMethod.POST)
    @Operation(summary = "修改考核方法",description = "修改考核方法")
    public JsonResult updateAssessMent(@RequestBody Assess assess) {
        try {
            return assessService.updateAssessMent(assess);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("修改失败");
        }
    }
}
