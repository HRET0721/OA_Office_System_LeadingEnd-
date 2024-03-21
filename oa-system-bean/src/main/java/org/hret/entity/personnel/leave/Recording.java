package org.hret.entity.personnel.leave;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName(value = "holiday_recording")
public class Recording implements Serializable {
    /**
     * 审批id
     */
    @TableId(value = "approval_id", type = IdType.AUTO)
    private Integer approvalId;

    /**
     * 请假人
     */
    private String holidayName;

    /**
     * 请假类型
     */
    private String holidayType;

    /**
     * 请假时长
     */
    private String holidayDuration;

    /**
     * 发起时间
     */
    private Date lnitiationTime;

    /**
     * 完成时间
     */
    private Date endTime;
}
