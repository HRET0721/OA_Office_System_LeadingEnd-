package org.hret.controller.personnel.assess;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.assess.AssessIndex;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.assess.AssessIndexService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "assessIndex")
@Tag(name = "考核指标方法", description = "考核指标方法")
public class AssessIndexController {
    public final AssessIndexService assessIndexService;
    @RequestMapping(value = "findAssessIndexListAndPage", method = RequestMethod.POST)
    @Operation(summary = "查询考核指标列表和分页",description = "查询考核指标列表和分页")
    public PageInfo<AssessIndex> findAssessIndexListAndPage(@RequestBody AssessIndex assessIndex)
    {
        return assessIndexService.findAssessIndexListAndPage(assessIndex);
    }
    @RequestMapping(value = "addAssessIndex",method = RequestMethod.POST)
    @Operation(summary = "添加考核指标",description = "添加考核指标")
    public JsonResult addAssessIndex(@RequestBody AssessIndex assessIndex)
    {
        try {
            return assessIndexService.addAssessIndex(assessIndex);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("添加失败");
        }
    }
    @RequestMapping(value = "updateAssessIndex",method = RequestMethod.POST)
    @Operation(summary = "修改考核指标",description = "修改考核指标")
    public JsonResult updateAssessIndex(@RequestBody AssessIndex assessIndex)
    {
        try {
            return assessIndexService.updateAssessIndex(assessIndex);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("修改失败");
        }
    }
    @RequestMapping(value = "deleteAssessIndex",method = RequestMethod.GET)
    @Operation(summary = "删除考核指标",description = "删除考核指标")
    public JsonResult deleteAssessIndex(@RequestParam(value = "assessIndexId") Long assessIndexId)
    {
        try {
            return assessIndexService.deleteAssessIndex(assessIndexId);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
    }
    @RequestMapping(value = "findAssessIndexById",method = RequestMethod.GET)
    @Operation(summary = "根据考核指标id查询考核指标",description = "根据考核指标id查询考核指标")
    public AssessIndex findAssessIndexById(@RequestParam(value = "assessIndexId") Long assessIndexId)
    {
        return assessIndexService.findAssessIndexById(assessIndexId);
   }
}
