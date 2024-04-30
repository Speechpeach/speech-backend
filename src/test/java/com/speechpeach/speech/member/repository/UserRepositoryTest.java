//package com.speechpeach.speech.member.repository;
//
//import com.speechpeach.speech.member.entity.Member;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
//class UserRepositoryTest {
//
//    @Autowired
//    private MemberRepository userRepository;
//
//    private Member user;
//
//    @DisplayName("회원 저장")
//    @Test
//    @Order(1)
//    void save() {
//        // given
//        user = new Member();
//
//        // when
//        Member savedUser = userRepository.save(user);
//
//        // then
//        assertThat(savedUser.getMemberId()).isNotNull();
//        System.out.println("Saved userId: " + savedUser.getMemberId());
//    }
//
//    @DisplayName("회원 아이디로 조회")
//    @Test
//    @Order(2)
//    void findById() {
//        // given
//        user = new Member();
//        Member savedUser = userRepository.save(user);
//
//        // when
//        Optional<Member> foundUser = userRepository.findById(savedUser.getMemberId());
//
//        // then
//        assertThat(foundUser).isNotEmpty();
//    }
//}