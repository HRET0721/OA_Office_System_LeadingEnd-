package org.hret.controller.administrative.seal;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.administrative.seal.SManagement;
import org.hret.entity.personnel.assess.AssessIndex;
import org.hret.pojo.JsonResult;
import org.hret.service.administrative.seal.SealService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "seal")
@Tag(name = "印章", description = "印章")
public class SealController {
    private final SealService sealService;

    @RequestMapping(value = "findall", method = RequestMethod.POST)
    @Operation(summary = "查询用章列表和分页",description = "查询用章列表和分页")
    public PageInfo<SManagement> findall(@RequestBody SManagement sManagementDO ){
        return sealService.findall(sManagementDO);
    }
    @RequestMapping(value = "findal", method = RequestMethod.GET)
    @Operation(summary = "根据id查询用章",description = "根据id查询用章")
    public SManagement findal(Long id){
        return sealService.findal(id);
    }

    /**
     * 添加
     * @param sManagementDO
     * @return
     */
    @RequestMapping(value = "add",method = RequestMethod.POST)
    @Operation(summary = "添加用章",description = "添加用章")
    public JsonResult add(@RequestBody SManagement sManagementDO){
        return sealService.add(sManagementDO);
    }

    /**
     * 同意
     * @param
     * @return
     */
    @RequestMapping(value = "updatebyida",method = RequestMethod.GET)
    @Operation(summary = "修改用章",description = "修改用章")
    public int updatebyida(Integer id,Integer state){
        return sealService.updatebyida(id,state);
    }

    /**
     * 拒绝
     * @param
     * @return
     */
    @RequestMapping(value = "updatebyidr",method = RequestMethod.GET)
    @Operation(summary = "修改用章",description = "修改用章")
    public int updatebyidr(Integer id,Integer state){
        return sealService.updatebyidr(id,state);
    }
    @RequestMapping(value = "radd",method = RequestMethod.POST)
    @Operation(summary = "添加用章",description = "添加用章")
    public JsonResult radd(@RequestBody SManagement sManagementDO){
        return sealService.radd(sManagementDO);
    }

}
