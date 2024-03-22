package org.hret.entity.personnel.assess;

import com.baomidou.mybatisplus.annotation.IdType;
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




}

