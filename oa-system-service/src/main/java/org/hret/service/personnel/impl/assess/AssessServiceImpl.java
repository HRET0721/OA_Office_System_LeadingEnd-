package org.hret.service.personnel.impl.assess;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.hret.entity.personnel.assess.Assess;
import org.hret.entity.personnel.assess.AssessRecruitJob;
import org.hret.entity.personnel.assess.DeptAssess;
import org.hret.entity.personnel.assess.UserAssess;
import org.hret.entity.personnel.recruit.RecruitJob;
import org.hret.entity.utils.query.Dept;
import org.hret.entity.utils.query.User;
import org.hret.mapper.personnel.assess.AssessMapper;
import org.hret.pojo.JsonResult;
import org.hret.service.personnel.assess.AssessRecruitJobService;
import org.hret.service.personnel.assess.AssessService;
import org.hret.service.personnel.assess.DeptAssessService;
import org.hret.service.personnel.assess.UserAssessService;
import org.hret.service.personnel.recruit.RecruitJobService;
import org.hret.service.util.query.DeptService;
import org.hret.service.util.query.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HRET
 */
@Service
@RequiredArgsConstructor
public class AssessServiceImpl extends ServiceImpl<AssessMapper, Assess> implements AssessService {
    private final DeptService deptService;
    private final DeptAssessService deptAssessService;
    private final RecruitJobService recruitJobService;
    private final AssessRecruitJobService assessRecruitJobService;
    private final UserService userService;
    private final UserAssessService userAssessService;
    @Override
    public PageInfo<Assess> findAssessListAndPage(Assess assess) {

        PageHelper.startPage(assess.getPageNum(), assess.getPageSize());

        QueryWrapper<Assess> queryWrapper = new QueryWrapper<>();
        if ( assess.getAssessName() != null && !"".equals(assess.getAssessName()) ) {
            queryWrapper.like("assess_name", assess.getAssessName());
        }
        if ( assess.getStatus() != null && !"".equals(assess.getStatus())){
            queryWrapper.like("assess_status", assess.getStatus());
        }
        if ( assess.getAssessType() != null && !"".equals(assess.getAssessType()) ) {
            queryWrapper.eq("assess_type", assess.getAssessType());
        }
        if ( assess.getAssessTime() != null && !"".equals(assess.getAssessTime()) ) {
            queryWrapper.eq("assess_time", assess.getAssessTime());
        }

        return new PageInfo<>(list(queryWrapper));
    }
    @Override
    public PageInfo<Assess> findAssessAndUserListAndPage(Assess assess) {
        PageHelper.startPage(assess.getPageNum(), assess.getPageSize());
        QueryWrapper<Assess> queryWrapper = new QueryWrapper<>();
        if ( assess.getAssessName() != null && !"".equals(assess.getAssessName()) ) {
            queryWrapper.like("assess_name", assess.getAssessName());
        }
        List<Assess> list = this.list(queryWrapper);
        list = getUserAndAssess(list);
        return new PageInfo<>(list);
    }

    @Override 
    public Assess findUserIdAndAssess(Integer userId) {
//        for (Assess assess : usersId) {
//            // ... （保留原有联查逻辑）
//            Integer assessId = assess.getAssessId();
//            // 用户和考核联查
//            List<UserAssess> userAssesses = userAssessService.lambdaQuery().eq(UserAssess::getAssessId, assessId).list();
//            for (UserAssess userAssess : userAssesses) {
//                Integer userAssessId = userAssess.getUserId();
//                User user = userService.lambdaQuery().eq(User::getUserId, userAssessId).one();
//
//                if (user != null) {  // 添加 null 检查，确保 User 不为 null
//                    if (assess.getUsers() == null) {
//                        assess.setUsers(new ArrayList<>());
//                    }
//                    assess.getUsers().add(user);
//
//                    // 新增逻辑：获取并存储 userId
//                    Integer usersId = user.getUserId();
//                    // 根据您的需求，可以选择将 userId 存储到 Assess 对象的某个属性，或者添加到一个集合中返回
//                    // 示例：假设 Assess 类有一个 List<Integer> userIds 属性用于存储关联用户的 userId
//                    assess.getUserIds().add(userId);
//                }
//            }
//        }
//
        return null;
    }

    public List<Assess> getUserAndAssess(List<Assess> list) {

        for (Assess assess : list) {
            Integer assessId = assess.getAssessId();
            if (assessId != null) {
                //        考核和用户联查
                List<DeptAssess> deptAssessList = deptAssessService.lambdaQuery().eq(DeptAssess::getAssessId, assessId).list();
                for (DeptAssess deptAssess : deptAssessList) {
                    Integer deptId = deptAssess.getDeptId();
                    Dept dept = deptService.lambdaQuery().eq(Dept::getDeptId, deptId).one();
                    if (assess.getDepts() == null) {
                        assess.setDepts(new ArrayList<>());
                    }
                    assess.getDepts().add(dept);
                }
            }
//                职位和考核联查
            List<AssessRecruitJob> recruitJobList = assessRecruitJobService.lambdaQuery().eq(AssessRecruitJob::getAssessId, assessId).list();
            for (AssessRecruitJob assessRecruitJob : recruitJobList) {
                Integer recruitJobJobId = assessRecruitJob.getJobId();
                RecruitJob recruitJob1 = recruitJobService.lambdaQuery().eq(RecruitJob::getJobId, recruitJobJobId).one();
                if (assess.getRecruitJobs() == null) {
                    assess.setRecruitJobs(new ArrayList<>());
                }
                assess.getRecruitJobs().add(recruitJob1);
            }
//                用户和考核联查
            List<UserAssess> userAssesses = userAssessService.lambdaQuery().eq(UserAssess::getAssessId, assessId).list();
            for (UserAssess userAssess : userAssesses) {
                Integer userAssessId = userAssess.getUserId();
                User user = userService.lambdaQuery().eq(User::getUserId, userAssessId).one();
                if (assess.getUsers() == null) {
                    assess.setUsers(new ArrayList<>());
                }
                assess.getUsers().add(user);
            }
        }
        return list;
    }

    @Override
    public JsonResult addAssessMent(Assess assess) {
        assess.setHeadId(1);
        assess.setStatus("1");
        int insert = this.baseMapper.insert(assess);

        if (insert > 0) {
            return JsonResult.ok("添加成功");
        } else {
            return JsonResult.error("添加失败");
        }

    }

    @Override
    public JsonResult deleteAssessMent(Long assessId) {

        int i = this.baseMapper.deleteById(assessId);

        if (i > 0) {
            return JsonResult.ok("删除成功");
        } else {
            return JsonResult.error("删除失败");
        }

    }

    @Override
    public Assess findAeeseeMentById(Long assessId) {
        return this.baseMapper.selectById(assessId);
    }

    @Override
    public JsonResult updateAssessMent(Assess assess) {

        int i = this.baseMapper.updateById(assess);

        if (i > 0) {
            return JsonResult.ok("修改成功");
        } else {
            return JsonResult.error("修改失败");
        }

    }


}
