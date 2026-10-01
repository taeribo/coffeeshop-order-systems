package com.coffeeshopordersystems.domain.menu.repository;

import com.coffeeshopordersystems.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
