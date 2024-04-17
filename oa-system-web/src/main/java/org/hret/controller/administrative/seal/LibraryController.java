package org.hret.controller.administrative.seal;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.administrative.seal.SLibrary;
import org.hret.pojo.JsonResult;
import org.hret.service.administrative.seal.LibraryService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "library")
@Tag(name = "印章", description = "印章")
public class LibraryController {
    private final LibraryService libraryService;
    @RequestMapping(value = "findall", method = RequestMethod.POST)
    @Operation(summary = "查询用章列表和分页",description = "查询用章列表和分页")
    public PageInfo<SLibrary> findall(@RequestBody SLibrary SLibraryDO ){
        return libraryService.findall(SLibraryDO);
    }
    @RequestMapping(value = "findal", method = RequestMethod.GET)
    @Operation(summary = "根据id查询用章",description = "根据id查询用章")
    public SLibrary findal(Long id){
        return libraryService.findal(id);
    }

    /**
     * 添加
     * @param SLibraryDO
     * @return
     */
    @RequestMapping(value = "add",method = RequestMethod.POST)
    @Operation(summary = "添加用章",description = "添加用章")
    public JsonResult add(@RequestBody SLibrary SLibraryDO){
        return libraryService.add(SLibraryDO);
    }

    /**
     * 同意
     * @param
     * @return
     */
    @RequestMapping(value = "updatebyida",method = RequestMethod.GET)
    @Operation(summary = "修改用章",description = "修改用章")
    public int updatebyida(Integer id,Integer state){
        return libraryService.updatebyida(id,state);
    }
    @RequestMapping(value = "del" ,method = RequestMethod.GET)
    @Operation(summary = "删除用章",description = "删除用章")
    public JsonResult del(Long id){
        return libraryService.del(id);
    }

    /**
     * 拒绝
     * @param
     * @return
     */
    @RequestMapping(value = "updatebyidr",method = RequestMethod.GET)
    @Operation(summary = "修改用章",description = "修改用章")
    public int updatebyidr(Integer id,Integer state){
        return libraryService.updatebyidr(id,state);
    }
    @RequestMapping(value = "radd",method = RequestMethod.POST)
    @Operation(summary = "添加用章",description = "添加用章")
    public JsonResult radd(@RequestBody SLibrary SLibraryDO){
        return libraryService.radd(SLibraryDO);
    }
}
