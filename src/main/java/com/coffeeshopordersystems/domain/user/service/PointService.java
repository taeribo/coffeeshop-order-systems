package com.coffeeshopordersystems.domain.user.service;
import com.coffeeshopordersystems.domain.user.entity.User;
import com.coffeeshopordersystems.domain.user.repository.UserRepository;
import com.coffeeshopordersystems.global.exception.BusinessException;
import com.coffeeshopordersystems.global.exception.ErrorCode;
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
            throw new BusinessException(ErrorCode.CHARGE_AMOUNT_INVALID);
        }

        User user = userRepository.findById(userId)
                .orElseGet(() -> userRepository.save(new User(userId)));
        user.charge(amount);

        return user.getPoint();
    }
}
