package org.hret.entity.personnel.assess;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hret.entity.utils.query.Page;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 考核评分(AssessScore)实体类
 * @author makejava
 * @since 2024-03-21 14:02:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AssessScore extends Page implements Serializable {
    private static final long serialVersionUID = -50439787400008267L;
/**
     * 评分id
     */
    @TableId(value = "score_id", type = IdType.AUTO)
    private Integer scoreId;
/**
     * 评分名称
     */
    private String scoreName;
/**
     * 评分区间
     */
    private String scoreBands;
/**
     * 保留小数
     */
    private Integer keepDecimals;
/**
     * 状态(0:启用;1:停用:)
     */
    private String status;
/**
     * 等级制名称
     */
    private String hierarchyName;
/**
     * 等级名称
     */
    private String leaveName;
/**
     * 等级说明
     */
    private String leaveIllustrate;
/**
     * 分布规则
     */
    private String distributionRules;
/**
     * 分数区间
     */
    private String fraction;
 /**
     *
     */
    private String createBy;



}

