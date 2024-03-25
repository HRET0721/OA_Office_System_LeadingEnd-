package org.hret.service.util.query;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hret.entity.utils.query.Dept;

import java.util.List;

/**
 * Author:HRET Milky Way
 * Date:2024/3/24
 * version:1.0
 * @author HRET
 */
public interface DeptService extends IService<Dept> {

    /**
     * 利用部门id查询出部门员工信息
     */
    List<Dept> findDeptUser();

}
