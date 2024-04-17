package org.hret.service.personnel.leave;

import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.leave.SealManagement;
import org.hret.pojo.JsonResult;

public interface SealManagementSevice {
    PageInfo<SealManagement> findSealPaginationList(SealManagement sealManagement);

    JsonResult deleteSeal(Long sealId);

    JsonResult addSeal(SealManagement sealManagement);

    SealManagement findSeal(Long sealId);

    JsonResult updateSeal(SealManagement sealManagement);
}
