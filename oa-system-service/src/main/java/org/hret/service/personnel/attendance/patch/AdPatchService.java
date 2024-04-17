package org.hret.service.personnel.attendance.patch;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hret.entity.personnel.attendance.patch.AdPatch;

public interface AdPatchService extends IService<AdPatch> {
    AdPatch findAdPatchById(Long patchId);

    void addAdPatch(AdPatch adPatch);
}
