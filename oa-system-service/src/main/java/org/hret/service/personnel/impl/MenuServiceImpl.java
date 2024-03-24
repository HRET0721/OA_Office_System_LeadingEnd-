package org.hret.service.personnel.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hret.entity.utils.Menu;
import org.hret.mapper.util.MenuMapper;
import org.springframework.stereotype.Service;
import org.hret.service.util.MenuService;

import java.util.List;

/**
 * Author:HRET Milky Way
 * Date:2024/3/18
 * version:1.0
 *
 * @author HRET
 * 菜单服务层实现类
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<Menu> selectMenuTree(Long parentId) {

        // TODO: 根据父级id查询菜单
        List<Menu> list = list(Wrappers.<Menu>lambdaQuery().eq(Menu::getParentId, parentId));

        // TODO: 遍历菜单集合
        for (Menu menu : list) {

            // TODO: 获取当前菜单的id
            Long menuId = menu.getMenuId();

            // TODO: 递归查询当前菜单的子菜单
            List<Menu> menuTree = selectMenuTree(menuId);

            // TODO: 将当前子菜单集合设置到当前菜单的children属性中
            menu.setChildren(menuTree);

        }

        // TODO: 返回菜单集合
        return list;

    }

}
