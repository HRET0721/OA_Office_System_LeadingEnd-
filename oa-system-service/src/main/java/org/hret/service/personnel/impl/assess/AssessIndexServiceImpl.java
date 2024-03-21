package org.hret.service.personnel.impl.assess;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hret.entity.personnel.assess.AssessIndex;
import org.hret.mapper.personnel.assess.AssessIndexMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.assess.AssessIndexService;
import org.springframework.stereotype.Service;

@Service
public class AssessIndexServiceImpl extends ServiceImpl<AssessIndexMapper, AssessIndex> implements AssessIndexService {
    @Override
    public PageInfo<AssessIndex> findAssessIndexListAndPage(AssessIndex assessIndex) {
        PageHelper.startPage(assessIndex.getPageNum(),assessIndex.getPageSize());
        QueryWrapper<AssessIndex> queryWrapper = new QueryWrapper<>();
        if (assessIndex.getAssessIndexName()!=null && !"".equals(assessIndex.getAssessIndexName())){
            queryWrapper.like("assess_index_name",assessIndex.getAssessIndexName());
        }
        if (assessIndex.getIndexType()!=null && !"".equals(assessIndex.getIndexType())) {
            queryWrapper.eq("index_type",assessIndex.getIndexType());
        }
        if (assessIndex.getCreateTime()!=null && !"".equals(assessIndex.getCreateTime())) {
            queryWrapper.le("create_time",assessIndex.getCreateTime());
        }
        return new PageInfo<>(list(queryWrapper));
    }

    @Override
    public JsonResult addAssessIndex(AssessIndex assessIndex) {
        int i = this.baseMapper.insert(assessIndex);
        if (i>0){
            return JsonResult.ok("添加成功");
        }else {
            return JsonResult.error("添加失败");
        }
    }

    @Override
    public JsonResult updateAssessIndex(AssessIndex assessIndex) {
        int i = this.baseMapper.updateById(assessIndex);
        if (i>0){
            return JsonResult.ok("修改成功");
        }else {
            return JsonResult.error("修改失败");
        }
    }

    @Override
    public JsonResult deleteAssessIndex(Long assessIndexId) {
        int i = this.baseMapper.deleteById(assessIndexId);
        if (i>0){
            return JsonResult.ok("删除成功");
        }else {
            return JsonResult.error("删除失败");
        }
    }

    @Override
    public AssessIndex findAssessIndexById(Long assessIndexId) {
      return  this.baseMapper.selectById(assessIndexId);
    }
}
