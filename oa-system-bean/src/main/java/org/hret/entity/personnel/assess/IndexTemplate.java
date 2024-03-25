package org.hret.entity.personnel.assess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hret.entity.utils.query.Page;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 考核模板和考核指标中间表(IndexTemplate)实体类
 *
 * @author makejava
 * @since 2024-03-25 14:34:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class IndexTemplate extends Page implements Serializable {
    private static final long serialVersionUID = -26704450124269289L;
/**
     * 考核模板ID
     */
    private Integer templateId;
/**
     * 考核指标ID
     */
    private Integer assessIndexId;


}

