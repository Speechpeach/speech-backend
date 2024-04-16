package com.speechpeach.speech.user.service;

import com.speechpeach.speech.user.entity.User;
import com.speechpeach.speech.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.speechpeach.speech.global.exception.ExceptionCode.NOT_FOUND_USER_ID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UUID createUser() {
        User user = new User();
        User savedUser = userRepository.save(user);
        return savedUser.getUserId();
    }

    public User getUser(final UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND_USER_ID.getMessage()));
    }
}
