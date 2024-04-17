package org.hret.service.personnel.impl.leave;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.leave.SealManagement;
import org.hret.mapper.personnel.leave.SealManagementMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.leave.SealManagementSevice;
import org.springframework.stereotype.Service;

import javax.crypto.SealedObject;
@Service
public class SealManagementServicelmpl extends ServiceImpl<SealManagementMapper, SealManagement>implements SealManagementSevice {
    @Override
    public PageInfo<SealManagement> findSealPaginationList(SealManagement sealManagement) {
        PageHelper.startPage(sealManagement.getPageNum(), sealManagement.getPageSize());
        QueryWrapper<SealManagement> queryWrapper = new QueryWrapper<>();
        if (sealManagement.getSealName() != null && !"".equals(sealManagement.getSealName())) {
            queryWrapper.like("seal_name", sealManagement.getSealName());
        }
        if (sealManagement.getSealTitle() != null && !"".equals(sealManagement.getSealTitle())) {
            queryWrapper.like("seal_title", sealManagement.getSealTitle());
        }
        if (sealManagement.getState() != null && !"".equals(sealManagement.getState())) {
            queryWrapper.eq("state", sealManagement.getState());
        }
        if (sealManagement.getDateOfPplication() != null && !"".equals(sealManagement.getDateOfPplication())) {
            queryWrapper.eq("date_of_pplication", sealManagement.getDateOfPplication());
        }
        if (sealManagement.getSubmissionTime() != null && !"".equals(sealManagement.getSubmissionTime())) {
            queryWrapper.eq("submission_time", sealManagement.getSubmissionTime());
        }

        return new PageInfo<>(list(queryWrapper));
    }

    @Override
    public JsonResult deleteSeal(Long sealId) {
        int i = this.baseMapper.deleteById(sealId);
        if (i > 0) {
            return JsonResult.ok("删除成功");
        } else {
            return JsonResult.error("删除失败");
        }
    }

    @Override
    public JsonResult addSeal(SealManagement sealManagement) {
        int insert = this.baseMapper.insert(sealManagement);
        if (insert > 0) {
            return JsonResult.ok("添加成功");
        } else {
            return JsonResult.error("添加失败");
        }
    }

    @Override
    public SealManagement findSeal(Long sealId) {
        return getById(sealId);
    }

    @Override
    public JsonResult updateSeal(SealManagement sealManagement) {
        int i = this.baseMapper.updateById(sealManagement);
        if (i > 0) {
            return JsonResult.ok("修改成功");
        } else {
            return JsonResult.error("修改失败");
        }
    }
}