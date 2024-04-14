package com.speechpeach.speech.user.repository;

import com.speechpeach.speech.user.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @DisplayName("회원 저장")
    @Test
    @Order(1)
    void save() {
        // given
        user = new User();

        // when
        User savedUser = userRepository.save(user);

        // then
        assertThat(savedUser.getUserId()).isNotNull();
        System.out.println("Saved userId: " + savedUser.getUserId());
    }

    @DisplayName("회원 아이디로 조회")
    @Test
    @Order(2)
    void findById() {
        // given
        user = new User();
        User savedUser = userRepository.save(user);

        // when
        Optional<User> foundUser = userRepository.findById(savedUser.getUserId());

        // then
        assertThat(foundUser).isNotEmpty();
    }
}