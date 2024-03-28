package org.hret.entity.personnel.assess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hret.entity.utils.query.Page;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 部门考核关联表(UserAssess)实体类
 *
 * @author makejava
 * @since 2024-03-27 14:07:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class DeptAssess extends Page implements Serializable {
    private static final long serialVersionUID = -23024204369213046L;

    private Integer deptId;
/**
     * 考核id
     */
    private Integer assessId;



}

