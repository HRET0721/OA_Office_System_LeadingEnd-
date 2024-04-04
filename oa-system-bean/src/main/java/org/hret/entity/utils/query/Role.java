package org.hret.entity.utils.query;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hret.entity.utils.Menu;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author HRET Milky Way
 * @version 1.0
 * Date:2024/4/1
 * description TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Page implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.AUTO)
    // 角色id
    private Integer roleId;

    // 角色名称
    private String roleName;

    // 角色描述
    private String roleRemark;

    // 角色创建人
    private String roleCreatePerson;

    // 角色创建时间
    private String roleCreateTime;

    // 角色更新人
    private String roleUpdatePerson;

    // 角色更新时间
    private String roleUpdateTime;

    @TableField(exist = false)
    // 角色员工集合
    private List<User> users;

    @TableField(exist = false)
    // 角色权限集合
    private List<Menu> menus;

}
