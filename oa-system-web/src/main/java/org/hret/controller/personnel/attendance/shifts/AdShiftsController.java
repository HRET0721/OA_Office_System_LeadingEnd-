package org.hret.controller.personnel.attendance.shifts;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hret.entity.personnel.attendance.dto.AdShiftsDTO;
import org.hret.entity.personnel.attendance.shifts.AdShifts;
import org.hret.entity.personnel.attendance.vo.AdShiftsVO;
import org.hret.service.personnel.attendance.shifts.IAdShiftsService;
import org.hret.service.personnel.impl.attendance.shifts.AdShiftsServiceImpl;
import org.springframework.web.bind.annotation.*;

/**
 * @version oa-system
 * @developer breath
 * @User breath
 * @date 2024/3/26 17:01
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "adShifts")
@Tag(name = "班次模块", description = "班次模块")
@Slf4j
public class AdShiftsController {

    private final AdShiftsServiceImpl adShiftsService;

    private final IAdShiftsService iAdShiftsService;

    @RequestMapping(value = "findAdShiftsByID", method = RequestMethod.POST)
    @Operation(summary = "根据id查询班次", description = "根据id查询班次")
    public AdShiftsVO findAdShitsByID(@RequestParam(value = "adShiftsId") Long adShiftsId){
        log.warn("adShiftsId:{}", adShiftsId);
        return adShiftsService.findAdShitsByIDPro(adShiftsId);
    }

    @RequestMapping(value = "updateAdShifts", method = RequestMethod.POST)
    @Operation(summary = "修改班次", description = "修改班次")
    public void updateAdShifts(@RequestBody AdShiftsDTO adShiftsDTO){
        adShiftsService.updateAdShifts(adShiftsDTO);
    }

    @RequestMapping(value = "addAdShifts",method = RequestMethod.POST)
    @Operation(summary = "新增班次", description = "新增班次")
    public void addAdShifts(@RequestBody AdShiftsDTO adShiftsDTO){
        iAdShiftsService.addAdShifts(adShiftsDTO);
    }

    @RequestMapping(value = "addAdShiftsMq",method = RequestMethod.POST)
    @Operation(summary = "新增班次Mq", description = "新增班次Mq")
    public void addAdShiftsMq(@RequestBody AdShiftsDTO adShiftsDTO){
        iAdShiftsService.addAdShiftsMq(adShiftsDTO);
    }
    @RequestMapping(value = "addAdShiftsMqPro",method = RequestMethod.POST)
    @Operation(summary = "新增班次MqPro", description = "新增班次MqPro")
    public void addAdShiftsMqPro(@RequestBody AdShiftsDTO adShiftsDTO){
        iAdShiftsService.addAdShiftsMqPro(adShiftsDTO);
    }
}
