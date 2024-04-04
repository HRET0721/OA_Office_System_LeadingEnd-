package org.hret.entity.utils.query;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class RoleMenu implements Serializable {

    // 角色id
    @TableId
    private Integer roleId;

    // 菜单id
    private Integer menuId;

}
