package org.hret.entity.personnel.leave;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hret.entity.utils.query.Page;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Holiday extends Page implements Serializable {
    @Serial
    private static final long serialVersionUID = -49452775890884099L;
    /**
     * 请假id
     */
    @TableId(value = "holiday_id", type = IdType.AUTO)
    private Integer holidayId;

    /**
     * 假期类型
     */
    private String holidayType;

    /**
     * 单位时长
     */
    private String holidayDuration;

    /**
     * 负责人
     */
    private String head;

    /**
     * 适用范围
     */
    private String scopeOfApplication;

    /**
     * 工作时长
     */
    private String workingHours;

    /**
     * 是否限额(0是,1否)
     */
    private Integer limitss;

    /**
     * 请假证明(0无需证明,1需要证明)
     */
    private Integer holidayProve;

    /**
     * 计入请假的日期(0工作日,1休息日,2节假日)
     */
    private Integer holidayDate;

    /**
     * 单次最小时长(0不限制,1最少请假天数)
     */
    private Integer minimumDuration;

    /**
     * 单次最大时长(0不限制,1最多请假天数)
     */
    private Integer maximumDuration;

    /**
     * 时间限制(0不限制,1提前?天提交申请)
     */
    private Integer timeLimit;

    /**
     * 申请资格
     */
    private Integer eligibility;

    /**
     * 发放方式
     */
    private String issuanceMethod;

    /**
     * 发放日期
     */
    private String releaseDate;

    /**
     * 发放规则
     */
    private String rules;

    /**
     * 发放额度
     */
    private String lssuanceQuota;

    /**
     * 假期有效期
     */
    private Integer holidayEffective;

    /**
     * 发起人
     */
    private String sponsorName;

    /**
     * 发起时间
     */
    private Date lnitiationTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 余额规则
     */
    private String balanceRules;
    /**
     * 一天折算
     */
    private String oneConversion;
//    private List<HolidayOperate> holidayOperate;


}
