package org.hret.entity.personnel.leave;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hret.entity.utils.query.Page;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
@AllArgsConstructor
@Component
@TableName(value = "holiday_operate")
@NoArgsConstructor
@Data
public class HolidayOperate extends Page implements Serializable {
    @Serial
    private static final long serialVersionUID = -49452775890884099L;
    @TableId(value = "operate_id", type = IdType.AUTO)
    private Integer operateId;

    /**
     * 操作时间
     */
    private Date operatingTime;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作内容
     */
    private String operateContent;

    /**
     * 操作时长
     */
    private String operateDuration;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 余额id
     */
    private Integer balanceId;
    @TableField(exist = false)
//             部门假期
    private String deptName;
    @TableField(exist = false)
    private String userNames;
    @TableField(exist = false)
    private String balanceName;


}
