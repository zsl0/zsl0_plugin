package com.zsl.custombox.authentication.service.menu;

import com.zsl.custombox.authentication.model.pojo.login.Menu;

import java.util.List;

/**
 * @Author zsl
 * @Date 2022/6/9 17:38
 * @Email 249269610@qq.com
 */
public interface MenuService {

    /**
     * 根据用户id获取菜单
     */
    void loadUserMenu(Long userId);

    /**
     * 获取所有菜单
     */
    int loadAll();

    /**
     * 根据角色获取菜单
     */
    int loadRoleMenu(Long roleId);

    /**
     * 获取单个菜单信息
     */
    int loadMenu(Long menuId);

    /**
     * 添加菜单
     */
    int add(Menu menu);

    /**
     * 删除菜单
     */
    int remove(Long menuId);

    /**
     * 更新菜单信息
     */
    int update(Menu menu);
}
