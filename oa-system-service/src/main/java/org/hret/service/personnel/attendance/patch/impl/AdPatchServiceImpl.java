package org.hret.service.personnel.attendance.patch.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hret.entity.personnel.attendance.patch.AdPatch;
import org.hret.mapper.personnel.attendance.patch.AdPatchMapper;
import org.hret.service.personnel.attendance.patch.AdPatchService;
import org.springframework.stereotype.Service;

/**
 * @version oa-system
 * @developer breath
 * @User breath
 * @date 2024/3/25 19:55
 */
@Service
public class AdPatchServiceImpl extends ServiceImpl<AdPatchMapper, AdPatch> implements AdPatchService{
    @Override
    public AdPatch findAdPatchById(Long patchId) {
        return null;
    }

    @Override
    public void addAdPatch(AdPatch adPatch) {

    }
}
