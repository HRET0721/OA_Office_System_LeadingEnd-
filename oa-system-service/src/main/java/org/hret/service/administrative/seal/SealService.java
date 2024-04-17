package org.hret.service.administrative.seal;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.administrative.seal.SManagement;
import org.hret.entity.personnel.assess.AssessIndex;
import org.hret.pojo.JsonResult;

public interface SealService extends IService<SManagement> {
    PageInfo<SManagement> findall(SManagement sManagementDO);

    SManagement findal(Long id);

    JsonResult add(SManagement sManagementDO);

    int updatebyida(Integer id, Integer state);

    int updatebyidr(Integer id,Integer state);

    JsonResult radd(SManagement sManagementDO);
}
