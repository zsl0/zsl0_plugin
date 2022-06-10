package com.zsl.custombox.authentication.service.user.impl;

import com.zsl.custombox.authentication.model.pojo.login.Role;
import com.zsl.custombox.authentication.service.user.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zsl
 * @Date 2022/6/10 17:36
 * @Email 249269610@qq.com
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> queryByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Role> queryByMenuId(Long menuId) {
        return null;
    }

    @Override
    public Role queryByRoleId(Long roleId) {
        return null;
    }

    @Override
    public List<Role> queryAll() {
        return null;
    }

    @Override
    public int add(Role role) {
        return 0;
    }

    @Override
    public int update(Role role) {
        return 0;
    }

    @Override
    public int remove(Long roleId) {
        return 0;
    }
}
