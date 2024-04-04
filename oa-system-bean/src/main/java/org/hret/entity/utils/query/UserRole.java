package org.hret.entity.utils.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author HRET Milky Way
 * @version 1.0
 * Date:2024/4/1
 * description TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {

    // 用户id
    private Integer userId;

    // 角色id
    private Integer roleId;

}
