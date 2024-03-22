package org.hret.entity.personnel.assess;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hret.entity.utils.query.Page;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.io.Serializable;

/**
 * 考核模板表(AssessTemplate)实体类
 *
 * @author makejava
 * @since 2024-03-19 19:03:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AssessTemplate extends Page implements Serializable {
    private static final long serialVersionUID = 779175493447493921L;
/**
     * 模板id
     */
    @TableId(value = "template_id", type = IdType.AUTO)
    private Integer templateId;
/**
     * 模板名称
     */
    private String templateName;
/**
     * 评分方式(0百分之(0-100):;1:十分之(0-10);2:五分之(0-5);)
     */
    private String scoreMethod;
/**
     * 计分方式(0:加权计算;1:加和计算;)
     */
    private String scoring;
/**
     * 备注
     */
    private String remark;
/**
     * 创建时间
     */
    private Date createTime;
/**
     * 总分
     */
    private String score;
    /**
     * 员工自评
     */
    private Integer employeeSelfAssessment;
    /**
     * 允许添加指标
     */
    private Integer addMetrics;
    /**
     * 评价权限
     */
    private Object commentPermissions;
    /**
     * 邀请同事评价
     */
    private Integer inviteColleagues;
    /**
     * 最少人数
     */
    private Integer minNumber;
    /**
     * 最多人数
     */
    private Integer maxNumber;
    /**
     * 截止时间
     */
    private Date deadline;
    /**
     * 上级评价
     */
    private Integer superiorEvaluation;
    /**
     * 可执行人
     */
    private Object executor;
    /**
     * 指派给指定人(0:手动调整;1自动跳过:;2:指派给指定人;)
     */
    private String nodeEmpty;

//封装考核指标
    @TableField(exist = false)
    private AssessIndex assessIndex;

}

