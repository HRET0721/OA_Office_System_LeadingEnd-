package org.hret.service.util;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hret.entity.utils.Menu;

import java.util.List;

/**
 * Author:HRET Milky Way
 * Date:2024/3/18
 * version:1.0
 *
 * @author HRET
 * 菜单服务层
 */
public interface MenuService extends IService<Menu> {

    /**
     * 查询菜单列表
     *
     * @param parentId 父菜单id
     * @return 菜单列表
     */
    List<Menu> selectMenuTree(Long parentId);

}
