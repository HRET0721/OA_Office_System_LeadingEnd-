package org.hret.entity.utils.query;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;


import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hret.entity.personnel.leave.HolidayDalance;
import org.hret.entity.personnel.leave.HolidayOperate;

/**
    *Author:HRET Milky Way
    *Date:2024/3/24
    *version:1.0
 * @author HRET
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
    * 部门id
    */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Integer deptId;

    /**
    * 部门名称
    */
    private String deptName;

    /**
     * 部门员工信息
     */
    @TableField(exist = false)
    private List<User> userList;

    @TableField(exist = false)
    // 部门假期
    private List<HolidayDalance> holidayDalances;
    @TableField(exist = false)
    private List<HolidayOperate>holidayOperates;

}