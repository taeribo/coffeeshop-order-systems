package com.coffeeshopordersystems.domain.user.entity;

import com.coffeeshopordersystems.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    private Long id;


    @Column(name = "point", nullable = false)
    private long point;

    public User(Long id) {
        this.id = id;
    }

    public void charge(long amount){
        this.point += amount;
    }
}
