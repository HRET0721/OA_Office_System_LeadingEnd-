package org.hret.service.personnel.documentation;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.documentation.Returns;
import org.hret.entity.personnel.documentation.WholeSect;
import org.hret.pojo.JsonResult;

public interface WholeSectService extends IService<WholeSect> {
    PageInfo<WholeSect> findWholePaginationList(WholeSect wholeSect);

    JsonResult addWhole(WholeSect wholeSect);

    JsonResult deleteWhole(Long wholeSectId);

    WholeSect findWhole(Long wholeSectId);

    JsonResult updateWhole(WholeSect wholeSect);
}
