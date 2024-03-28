package org.hret.controller.util;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.utils.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.hret.service.personnel.impl.MenuServiceImpl;

import java.util.List;

/**
 * Author:HRET Milky Way
 * Date:2024/3/18
 * version:1.0
 *
 * @author HRET
 * 菜单控制层
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "menu")
@Tag(name = "菜单模块", description = "菜单模块")
public class MenuController {

    private final MenuServiceImpl menuService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "selectMenuTree", method = RequestMethod.GET)
    @Operation(summary = "查询菜单树", description = "查询菜单树")
    public List<Menu> selectMenuTree(@RequestParam(value = "parentId") Long parentId) {

        // 从缓存中获取数据
        Object menuList = redisTemplate.opsForValue().get("menuList：" + parentId);

        // 判断redis中是否有数据
        if (!ObjectUtils.isEmpty(menuList)) {
            // 如果有数据则直接返回
            return (List<Menu>) menuList;
        }

        // 没有数据则从数据库中查询
        List<Menu> menus = menuService.selectMenuTree(parentId);

        // 将数据存入redis中
        redisTemplate.opsForValue().set("menuList：" + parentId, menus);

        return menus;
    }

}
