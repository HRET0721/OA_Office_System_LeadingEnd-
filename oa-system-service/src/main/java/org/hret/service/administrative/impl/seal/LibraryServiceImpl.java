package org.hret.service.administrative.impl.seal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hret.entity.administrative.seal.SLibrary;
import org.hret.mapper.administrative.seal.LibraryMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.administrative.seal.LibraryService;
import org.springframework.stereotype.Service;

@Service
public class LibraryServiceImpl extends ServiceImpl<LibraryMapper, SLibrary> implements LibraryService {
    @Override
    public PageInfo<SLibrary> findall(SLibrary sLibraryDO) {
        PageHelper.startPage(sLibraryDO.getPageNum(),sLibraryDO.getPageSize());
        QueryWrapper<SLibrary> queryWrapper = new QueryWrapper<>();
        if (sLibraryDO.getSName()!=null && !"".equals(sLibraryDO.getSName())) {
            queryWrapper.eq("s_name",sLibraryDO.getSName());
        }
        if (sLibraryDO.getState()!=null && !"".equals(sLibraryDO.getState())) {
            queryWrapper.eq("state",sLibraryDO.getState());
        }
        if (sLibraryDO.getResponsible()!=null && !"".equals(sLibraryDO.getResponsible())) {
            queryWrapper.eq("responsible",sLibraryDO.getResponsible());
        }
        if (sLibraryDO.getCreationTime()!=null && !"".equals(sLibraryDO.getCreationTime())) {
            queryWrapper.eq("creation_time",sLibraryDO.getCreationTime());
        }
        return new PageInfo<>(list(queryWrapper));
    }

    @Override
    public SLibrary findal(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public JsonResult add(SLibrary sLibraryDO) {
        int i = this.baseMapper.insert(sLibraryDO);
        if (i>0){
            return JsonResult.ok("添加成功");
        }else {
            return JsonResult.error("添加失败");
        }
    }

    @Override
    public int updatebyida(Integer id, Integer state) {
        SLibrary sLibrary = this.baseMapper.selectById(id);
        sLibrary.setState(state);
        return this.baseMapper.updateById(sLibrary);


    }

    @Override
    public int updatebyidr(Integer id,Integer state) {
        SLibrary sLibrary = this.baseMapper.selectById(id);
        sLibrary.setState(state);
        return this.baseMapper.updateById(sLibrary);
    }

    @Override
    public JsonResult radd(SLibrary sLibraryDO) {
        int i = this.baseMapper.insert(sLibraryDO);
        if (i>0){
            return JsonResult.ok("添加成功");
        }else {
            return JsonResult.error("添加失败");
        }
    }

    @Override
    public JsonResult del(Long id) {
        int i = this.baseMapper.deleteById(id);
        if (i>0){
            return JsonResult.ok("添加成功");
        }else {
            return JsonResult.error("添加失败");
        }
    }
}
