package com.zsl.custombox.authentication.service.menu;

import com.zsl.custombox.authentication.mapper.MenuMapper;
import com.zsl.custombox.authentication.model.pojo.login.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zsl
 * @Date 2022/6/9 17:46
 * @Email 249269610@qq.com
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public void loadUserMenu(Long userId) {

    }

    @Override
    public int loadAll() {
        return 0;
    }

    @Override
    public int loadRoleMenu(Long roleId) {
        return 0;
    }

    @Override
    public int loadMenu(Long menuId) {
        return 0;
    }

    @Override
    public int add(Menu menu) {
        return 0;
    }

    @Override
    public int remove(Long menuId) {
        return 0;
    }

    @Override
    public int update(Menu menu) {
        return 0;
    }
}
