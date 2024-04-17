package org.hret.service.administrative.seal;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.hret.entity.administrative.seal.SLibrary;
import org.hret.pojo.JsonResult;
import org.springframework.stereotype.Service;

public interface LibraryService extends IService<SLibrary> {
    PageInfo<SLibrary> findall(SLibrary sLibraryDO);

    SLibrary findal(Long id);

    JsonResult add(SLibrary sLibraryDO);

    int updatebyida(Integer id, Integer state);

    int updatebyidr(Integer id,Integer state);

    JsonResult radd(SLibrary sLibraryDO);

    JsonResult del(Long id);
}
