package com.coffeeshopordersystems.domain.menu.service;

import com.coffeeshopordersystems.domain.menu.dto.MenuResponse;
import com.coffeeshopordersystems.domain.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuResponse> getMenus(){
        return menuRepository.findAll().stream()
                .map(menu -> new MenuResponse(menu.getId(),menu.getName(), menu.getDescription(), menu.getPrice()))
                .toList();
    }
}
