package org.hret.service.util.query.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.hret.entity.utils.query.Dept;
import org.hret.entity.utils.query.User;
import org.hret.mapper.util.query.DeptMapper;
import org.hret.service.util.query.DeptService;
import org.hret.service.util.query.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:HRET Milky Way
 * Date:2024/3/24
 * version:1.0
 * @author HRET
 */
@Service
@RequiredArgsConstructor
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    private final UserService userService;

    @Override
    public List<Dept> findDeptUser() {

        // 查询出所有的部门信息
        List<Dept> deptList = this.list();

        // 查询出部门下的员工信息
        for (Dept dept : deptList) {
            List<User> list = userService.lambdaQuery().eq(User::getDeptId, dept.getDeptId()).list();
            // 将员工信息放入部门信息中
            dept.setUserList(list);
        }

        // 返回部门信息
        return deptList;
    }

    @Override
    public List<Dept> findDeptByUserName(String userName) {

        // 查询出员工信息并根据部门id去重 distinct：去重
        List<User> list = userService.lambdaQuery().like(User::getUserName, userName).list().stream().distinct().toList();

        // 查询出部门信息
        List<Dept> deptList = this.listByIds(List.of(list.stream().map(User::getDeptId).toArray(Integer[]::new)));

        // 将员工信息放入部门信息中
        for (Dept dept : deptList) {
            dept.setUserList(list.stream().filter(user -> user.getDeptId().equals(dept.getDeptId())).toList());
        }

        // 返回部门信息
        return deptList;
    }
}
