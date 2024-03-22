package org.hret.entity.utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Author:HRET Milky Way
 * Date:2024/3.18
 * version:1.0
 *
 * @author HRET
 * 菜单实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Menu implements Serializable {

    //菜单id
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;
    //菜单名称
    private String menuName;
    //菜单路由
    private String menuPath;
    //父菜单id
    private Long parentId;
    //显示顺序
    private Integer orderNum;
    //是否为外链（0是 1否）
    private Integer isFrame;
    //是否缓存（0缓存 1不缓存）
    private Integer isCache;
    //菜单类型（M目录 C菜单 F按钮）
    private String menuType;
    //显示状态（0显示 1隐藏）
    private String menuVisible;
    //菜单状态（0正常 1停用）
    private String menuStatus;
    //权限标识
    private String menuPerms;
    //菜单图标
    private String menuIcon;
    //创建者
    private String menuCreateBy;
    //创建时间
    private String menuCreateTime;
    //更新者
    private String menuUpdateBy;
    //更新时间
    private String menuUpdateTime;
    //备注
    private String menuRemark;

    @TableField(exist = false)
    private List<Menu> children;

}

