package org.hret.controller.util;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hret.entity.utils.Menu;
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

    @RequestMapping(value = "selectMenuTree", method = RequestMethod.GET)
    @Operation(summary = "查询菜单树", description = "查询菜单树")
    public List<Menu> selectMenuTree(@RequestParam(value = "parentId") Long parentId) {
        return menuService.selectMenuTree(parentId);
    }

}
