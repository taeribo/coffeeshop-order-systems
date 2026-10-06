package com.coffeeshopordersystems.domain.menu.service;

import com.coffeeshopordersystems.domain.menu.dto.MenuResponse;
import com.coffeeshopordersystems.domain.menu.dto.PopularMenuResponse;
import com.coffeeshopordersystems.domain.menu.repository.MenuRepository;
import com.coffeeshopordersystems.domain.order.entity.OrderItem;
import com.coffeeshopordersystems.domain.order.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final OrderItemRepository orderItemRepository;

    public List<MenuResponse> getMenus(){
        return menuRepository.findAll().stream()
                .map(menu -> new MenuResponse(menu.getId(),menu.getName(), menu.getDescription(), menu.getPrice()))
                .toList();
    }

    public List<PopularMenuResponse> getPopularMenus(){
        LocalDateTime since = LocalDateTime.now().minusDays(7);
        return orderItemRepository.findPopularMenus(since).stream()
                .map(p -> new PopularMenuResponse(p.getMenuId(), p.getName(),p.getOrderCount()))
                .toList();
    }
}
