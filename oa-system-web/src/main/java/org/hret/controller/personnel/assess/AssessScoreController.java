package org.hret.controller.personnel.assess;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.assess.AssessScore;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.assess.AssessScoreService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "assessScore")
@Tag(name = "考核评分方法", description = "考核评分方法")
public class AssessScoreController {
    private final AssessScoreService assessScoreService;
    @RequestMapping(value = "findAssessScoreListAndPage", method = RequestMethod.POST)
    @Operation(summary = "查询考核评分列表和分页",description = "查询考核评分列表和分页")
    public PageInfo<AssessScore> findAssessScoreListAndPage(@RequestBody AssessScore assessScore)
    {
        return assessScoreService.findAssessScoreListAndPage(assessScore);
    }
    @RequestMapping(value = "addAssessScore",method = RequestMethod.POST)
    @Operation(summary = "添加考核评分",description = "添加考核评分")
    public JsonResult addAssessScore(@RequestBody AssessScore assessScore)
    {
        try {
            return assessScoreService.addAssessScore(assessScore);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("添加失败");
        }
    }
    @RequestMapping(value = "updateAssessScore",method = RequestMethod.POST)
    @Operation(summary = "修改考核评分",description = "修改考核评分")
    public JsonResult updateAssessScore(@RequestBody AssessScore assessScore)
    {
        try {
            return assessScoreService.updateAssessScore(assessScore);
        } catch (Exception e) {
            e.printStackTrace();
           return JsonResult.error("修改失败");
       }
   }
    @RequestMapping(value = "deleteAssessScore",method = RequestMethod.GET)
    @Operation(summary = "删除考核评分",description = "删除考核评分")
    public JsonResult deleteAssessScore(@RequestParam(value = "assessScoreId") Long assessScoreId)
    {
        try {
            return assessScoreService.deleteAssessScore(assessScoreId);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
    }
    @RequestMapping(value = "findAssessScoreById",method = RequestMethod.GET)
    @Operation(summary = "根据考核评分id查询考核评分",description = "根据考核评分id查询考核评分")
    public AssessScore findAssessScoreById(@RequestParam(value = "assessScoreId") Long assessScoreId)
    {
        return assessScoreService.findAssessScoreById(assessScoreId);
    }
}
