package org.hret.controller.personnel.documentation;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.documentation.ArchivesDocumentation;
import org.hret.entity.personnel.documentation.Borrowing;
import org.hret.entity.personnel.leave.Holiday;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.impl.documentation.ArchivesDocumentationServicelmpl;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "documentation")
@Tag(name = "建档方法", description = "建档方法")
public class ArchivesDocumentationController {
    private final ArchivesDocumentationServicelmpl archivesDocumentationServicelmpl;
    @RequestMapping(value = "findDocumentationPaginationList", method = RequestMethod.POST)
    @Operation(summary = "查询考核方法列表和分页",description = "查询考核列表和分页")
    public PageInfo<ArchivesDocumentation> findDocumentationPaginationList(@RequestBody ArchivesDocumentation archivesDocumentation){
        return archivesDocumentationServicelmpl.findDocumentationPaginationList(archivesDocumentation);
    }
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @Operation(summary = "删除案库关联关系表操作方法", description = "删除案库关联关系表操作方法")
    public JsonResult delete(@RequestParam(value = "documentationId") Long documentationId) {
        try {
            return archivesDocumentationServicelmpl.delete(documentationId);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("删除失败");
        }
    }
    @RequestMapping(value = "find", method = RequestMethod.POST)
    @Operation(summary = "查询新增考核全宗方法列表和分页", description = "查询新增考核全宗方法列表和分页")
    public PageInfo<ArchivesDocumentation> find(@RequestBody ArchivesDocumentation archivesDocumentation) {
        return archivesDocumentationServicelmpl.find(archivesDocumentation);
    }
    @RequestMapping(value = "deleteDocumentation", method = RequestMethod.POST)
    @Operation(summary = "删除操作方法", description = "删除操作方法")
    public JsonResult deleteDocumentation(@RequestParam(value = "documentationId") Long documentationId) {
        try {
            return archivesDocumentationServicelmpl.deleteDocumentation(documentationId);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("删除失败");
        }
    }
    @RequestMapping(value = "addDocumentation", method = RequestMethod.POST)
    @Operation(summary = "新增操作方法", description = "新增操作方法")
    public JsonResult addDocumentation(@RequestBody ArchivesDocumentation archivesDocumentation) {
        try {
            archivesDocumentation.setLockerId(1);
            archivesDocumentation.setWholeSectId(1);
            archivesDocumentation.setCreationTime(new Date());
            archivesDocumentation.setUpdated(new Date());
            archivesDocumentation.setDestructionTime(new Date());
            return archivesDocumentationServicelmpl.addDocumentation(archivesDocumentation);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("添加失败");
        }
    }
    @RequestMapping(value = "findDocumentation", method = RequestMethod.POST)
    @Operation(summary = "回显操作方法", description = "回显操作方法")
    public ArchivesDocumentation findDocumentation(@RequestParam(value = "documentationId") Long documentationId) {
        return archivesDocumentationServicelmpl.findDocumentation(documentationId);
    }
    @RequestMapping(value = "updateDocumentation", method = RequestMethod.POST)
    @Operation(summary = "修改假期方法", description = "修改假期方法")
    public JsonResult updateDocumentation(@RequestBody ArchivesDocumentation archivesDocumentation) {
        try {
            return archivesDocumentationServicelmpl.updateDocumentation(archivesDocumentation);
        } catch (Exception e) {
            e.fillInStackTrace();
            return JsonResult.error("修改失败");
        }
    }

    @RequestMapping(value = "findArchivesDocumentationById", method = RequestMethod.POST)
    @Operation(summary = "所属案卷查询", description = "所属案卷查询")
    public JsonResult findArchivesDocumentationById(Long belongssFile){

            return archivesDocumentationServicelmpl.findArchivesDocumentationById(belongssFile);

    }

    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    @Operation(summary = "查询所有", description = "查询所有")
    public List<ArchivesDocumentation> findAll(ArchivesDocumentation archivesDocumentation){
            return archivesDocumentationServicelmpl.findAll(archivesDocumentation);

    }
}
