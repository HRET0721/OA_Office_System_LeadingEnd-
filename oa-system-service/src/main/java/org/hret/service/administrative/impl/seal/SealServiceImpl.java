package org.hret.service.administrative.impl.seal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hret.entity.administrative.seal.SLibrary;
import org.hret.entity.administrative.seal.SManagement;
import org.hret.mapper.administrative.seal.SealMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.administrative.seal.SealService;
import org.springframework.stereotype.Service;

@Service
public class SealServiceImpl extends ServiceImpl<SealMapper, SManagement> implements SealService {


    @Override
    public PageInfo<SManagement> findall(SManagement sManagementDO) {
        PageHelper.startPage(sManagementDO.getPageNum(),sManagementDO.getPageSize());
        QueryWrapper<SManagement> queryWrapper = new QueryWrapper<>();
        if (sManagementDO.getSPeople()!=null && !"".equals(sManagementDO.getSPeople())){
            queryWrapper.like("s_people",sManagementDO.getSPeople());
        }
        if (sManagementDO.getSName()!=null && !"".equals(sManagementDO.getSName())) {
            queryWrapper.eq("s_name",sManagementDO.getSName());
        }
        if (sManagementDO.getState()!=null && !"".equals(sManagementDO.getState())) {
            queryWrapper.eq("state",sManagementDO.getState());
        }
        if (sManagementDO.getApplicationTime()!=null && !"".equals(sManagementDO.getApplicationTime())) {
            queryWrapper.eq("application_time",sManagementDO.getApplicationTime());
        }
        if (sManagementDO.getSubmitTime()!=null && !"".equals(sManagementDO.getSubmitTime())) {
            queryWrapper.eq("submit_time",sManagementDO.getSubmitTime());
        }
        return new PageInfo<>(list(queryWrapper));
    }

    @Override
    public SManagement findal(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public JsonResult add(SManagement sManagementDO) {
        int i = this.baseMapper.insert(sManagementDO);
        if (i>0){
            return JsonResult.ok("添加成功");
        }else {
            return JsonResult.error("添加失败");
        }
    }

    @Override
    public int updatebyida(Integer id,Integer state) {
        SManagement sManagement = this.baseMapper.selectById(id);
        sManagement.setState(state);
        return this.baseMapper.updateById(sManagement);
    }

    @Override
    public int updatebyidr(Integer id,Integer state) {
        SManagement sManagement = this.baseMapper.selectById(id);
        sManagement.setState(state);
        return this.baseMapper.updateById(sManagement);
    }

    @Override
    public JsonResult radd(SManagement sManagementDO) {
        int i = this.baseMapper.insert(sManagementDO);
        if (i>0){
            return JsonResult.ok("添加成功");
        }else {
            return JsonResult.error("添加失败");
        }
    }
}
