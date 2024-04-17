package org.hret.service.personnel.documentation;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.documentation.CaseLocker;
import org.hret.pojo.JsonResult;

import java.util.List;

public interface CaseLockerService extends IService<CaseLocker> {
    PageInfo<CaseLocker> findLockerPaginationList(CaseLocker caseLocker);

    JsonResult deleteLocker(Long lockerId);

    JsonResult addLocker(CaseLocker caseLocker);

    CaseLocker findLock(Long lockerId);

    JsonResult updateLocker(CaseLocker caseLocker);

    JsonResult findLockerPaginationListById(Long affiliationss);

    List<CaseLocker> findAlll(CaseLocker caseLocker);
}
