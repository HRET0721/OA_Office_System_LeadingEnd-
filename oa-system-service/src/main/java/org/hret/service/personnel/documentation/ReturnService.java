package org.hret.service.personnel.documentation;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.documentation.Returns;
import org.hret.pojo.JsonResult;

public interface ReturnService extends IService<Returns> {
    PageInfo<Returns> findReturnPaginationList(Returns returns);

    JsonResult addReturns(Returns returns);

   

    JsonResult updateStatus(Returns returns);

    JsonResult deleteReturns(Long returnId);
}
