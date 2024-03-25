package org.hret.entity.utils.query;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hret.entity.personnel.leave.HolidayDalance;

/**
    *Author:HRET Milky Way
    *Date:2024/3/24
    *version:1.0
 * @author HRET
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
    * 用户id
    */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
    * 用户账号
    */
    private String userAccount;

    /**
    * 用户姓名
    */
    private String userName;

    /**
    * 用户密码
    */
    private String userPassword;

    /**
    * 用户手机号
    */
    private String userPhone;

    /**
    * 头像图片
    */
    private String profilePhoto;

    /**
    * 所属部门
    */
    private Integer deptId;

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    // 用户余额
    private List<HolidayDalance> holidayDalances;
}