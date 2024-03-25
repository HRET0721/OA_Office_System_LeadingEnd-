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
            // 统计部门下的员工数量
            dept.setDeptCount(list.size());
            // 将员工信息放入部门信息中
            dept.setUserList(list);
        }

        // 返回部门信息
        return deptList;
    }
}
