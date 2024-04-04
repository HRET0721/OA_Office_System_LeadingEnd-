package org.hret.entity.personnel.assess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hret.entity.utils.query.Page;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 考核和职位管理(AssessRecruitCandidate)实体类
 *
 * @author makejava
 * @since 2024-03-27 14:58:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AssessRecruitJob extends Page implements Serializable {
    private static final long serialVersionUID = 368705616769765993L;
/**
     * 考核id
     */
    private Integer assessId;
/**
     * 职位id
     */
    private Integer jobId;


}

