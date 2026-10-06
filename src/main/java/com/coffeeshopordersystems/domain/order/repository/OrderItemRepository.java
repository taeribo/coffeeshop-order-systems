package com.coffeeshopordersystems.domain.order.repository;

import com.coffeeshopordersystems.domain.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query(value = """
SELECT m.id AS menuId, m.name AS name, COUNT(oi.id) AS orderCount
FROM order_items oi
JOIN menus m ON m.id = oi.menu_id
WHERE oi.created_at >= :since
GROUP BY m.id, m.name
ORDER BY orderCount DESC, m.id ASC
LIMIT 3
""", nativeQuery = true)
    List<PopularMenuProjection> findPopularMenus(@Param("since")LocalDateTime since);
}
