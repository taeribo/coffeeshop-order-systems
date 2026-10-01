package com.coffeeshopordersystems.domain.user.service;
import com.coffeeshopordersystems.domain.user.entity.User;
import com.coffeeshopordersystems.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PointService {

    private final UserRepository userRepository;

    @Transactional
    public long charge(Long userId, long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("충전 금액은 0보다 커야 합니다.");
        }

        User user = userRepository.findById(userId)
                .orElseGet(() -> userRepository.save(new User(userId)));
        user.charge(amount);

        return user.getPoint();
    }
}
