package com.coffeeshopordersystems.domain.menu.controller;

import com.coffeeshopordersystems.domain.menu.dto.MenuListResponse;
import com.coffeeshopordersystems.domain.menu.dto.PopularMenuListResponse;
import com.coffeeshopordersystems.domain.menu.service.MenuService;
import com.coffeeshopordersystems.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping()
    public ApiResponse<MenuListResponse> getMenus() {
        MenuListResponse response = new MenuListResponse(menuService.getMenus());
        return ApiResponse.success("메뉴 목록 조회에 성공했습니다.", response);
    }
    @GetMapping("/popular")
    public ApiResponse<PopularMenuListResponse> getPopularMenus(){
        PopularMenuListResponse response = new PopularMenuListResponse(menuService.getPopularMenus());
        return ApiResponse.success("인기 메뉴 목록 조회에 성공했습니다.", response);
    }
}

