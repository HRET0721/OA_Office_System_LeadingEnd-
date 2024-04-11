package org.hret.controller.personnel.attendance.patch;

import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.attendance.patch.AdPatch;
import org.hret.service.personnel.attendance.patch.AdPatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version oa-system
 * @developer breath
 * @User breath
 * @date 2024/3/25 20:03
 */
@RestController("AdPatch")
@RequiredArgsConstructor
public class AdPatchController {

    @Autowired
    private AdPatchService adPatchService;

    @RequestMapping(value = "addAdPatch", method = RequestMethod.POST)
    public void addAdPatch(@RequestBody AdPatch adPatch){
        adPatchService.addAdPatch(adPatch);
    }
}
