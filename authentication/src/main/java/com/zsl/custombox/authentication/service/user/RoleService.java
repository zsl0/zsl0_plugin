package com.zsl.custombox.authentication.service.user;

import com.zsl.custombox.authentication.model.pojo.login.Role;

import java.util.List;

/**
 * todo 查询角色及拥有菜单权限
 *
 * @Author zsl
 * @Date 2022/6/10 17:21
 * @Email 249269610@qq.com
 */
public interface RoleService {

    /**
     * 根据用户id查询角色
     */
    List<Role> queryByUserId(Long userId);

    /**
     * 根据菜单id查询角色
     */
    List<Role> queryByMenuId(Long menuId);

    /**
     * 根据id查询角色
     */
    Role queryByRoleId(Long roleId);

    /**
     * 查询所有角色
     */
    List<Role> queryAll();

    /**
     * 添加角色
     * @return 1,执行成功
     */
    int add(Role role);

    /**
     * 更新角色
     * @return 1,执行成功
     */
    int update(Role role);

    /**
     * 删除角色
     * @return 1,执行成功
     */
    int remove(Long roleId);
}
