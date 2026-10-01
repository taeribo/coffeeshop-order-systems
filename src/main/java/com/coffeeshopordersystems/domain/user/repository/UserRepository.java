package com.coffeeshopordersystems.domain.user.repository;

import com.coffeeshopordersystems.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
