package com.ohgiraffers.crud.menu.model.service;

import com.ohgiraffers.crud.menu.model.dao.MenuMapper;
import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    private final MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }
    public List<MenuDTO> findAllMenu() {

        return menuMapper.findAllMenu();
    }

    /* 읽기 전용 트랜잭션 설정 가능 -> 성능 최적화 */
    @Transactional(readOnly = true)
    public List<CategoryDTO> findCategoryList() {

        return menuMapper.findCategoryList();
    }

    @Transactional
    public void registMenu(MenuDTO menu) {

        menuMapper.registMenu(menu);
    }
}
