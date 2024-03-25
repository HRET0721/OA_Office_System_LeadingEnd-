package org.hret.entity.personnel.assess;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hret.entity.utils.query.Page;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.util.Date;
import java.io.Serializable;

/**
 * 考核指标表(AssessIndex)实体类
 *
 * @author makejava
 * @since 2024-03-21 14:02:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AssessIndex extends Page implements Serializable {
    @Serial
    private static final long serialVersionUID = -95847280987889377L;
/**
     * 指标id
     */
    @TableId(value = "assess_index_id", type = IdType.AUTO)
    private Integer assessIndexId;
/**
     * 指标名称
     */
    private String assessIndexName;
/**
     * 指标说明
     */
    private String indexDescription;
/**
     * 评价标准
     */
    private String evaluationCriteria;
/**
     * 指标权重
     */
    private Double metricWeights;
/**
     * 目标值
     */
    private Double targetValue;
/**
     * 指标类型(0:工作指标;1:价值观指标;2:加减分项;3:其他指标;)
     */
    private String indexType;
/**
     * 评分上限
     */
    private Integer ratingCaps;
/**
     * 状态(0:启用;1:停用;)
     */
    private String status;
/**
     * 创建时间
     */
    private Date createTime;



}

