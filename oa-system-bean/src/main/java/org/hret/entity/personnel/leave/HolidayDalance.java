package org.hret.entity.personnel.leave;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hret.entity.utils.query.Dept;
import org.hret.entity.utils.query.Page;
import org.hret.entity.utils.query.User;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName(value = "holiday_balance")
public class HolidayDalance extends Page implements Serializable {
    @Serial
    private static final long serialVersionUID = -49452775890884099L;
    //          余额id
    @TableId(value = "balance_id", type = IdType.AUTO)
    @Excel(name = "余额id", width = 20, isImportField = "true")
    private Integer balanceId;
    @Excel(name = "余额人员", width = 20, isImportField = "true")
    private String balanceName;
    @Excel(name = "用户id", width = 20, isImportField = "true")
    private Integer userId;
    @Excel(name = "部门id", width = 20, isImportField = "true")
    private Integer deptId;
    @Excel(name = "职位", width = 20, isImportField = "true")
    private String position;
    //          事假
    @Excel(name = "事假", width = 20, isImportField = "true")
    private Double leavess;
    //          年假
    @Excel(name = "年假", width = 20, isImportField = "true")
    private Double annualLeavess;
    //          病假
    @Excel(name = "病假", width = 20, isImportField = "true")
    private String sickLeavess;
    //          产假
    @Excel(name = "产假", width = 20, isImportField = "true")
    private Double maternityLeavess;
    //          陪产假
    @Excel(name = "陪产假", width = 20, isImportField = "true")
    private Double paternityLeavess;
    //          婚假
    @Excel(name = "婚假", width = 20, isImportField = "true")
    private Double marriageLeavess;
    //           创建时间

    @Excel(name = "创建时间", width = 20, isImportField = "true")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lnitiationTimess;
    @TableField(exist = false)
//             部门假期
    private String deptName;
    @TableField(exist = false)
    private String userNames;

}
