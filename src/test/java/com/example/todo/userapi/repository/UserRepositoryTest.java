package com.example.todo.userapi.repository;

import com.example.todo.userapi.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired UserRepository userRepository;

    @Test
    @DisplayName("회원가입에 성공해야 한다.")
    @Transactional
    @Rollback
    void saveTest() {
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("말똥이");
        userEntity.setEmail("abc1234@def.com");
        userEntity.setPassword("1234");

        //when
        UserEntity savedUser = userRepository.save(userEntity);
        //then
        assertNotNull(savedUser);
    }

    @Test
    @DisplayName("이메일로 회원을 조회해야 한다.")
    void findByEmailTest() {
        // given
        String email = "abc1234@def.com";
        //when
        UserEntity foundUser = userRepository.findByEmail(email);
        //then
        assertEquals("말똥이", foundUser.getUserName());
    }

    @Test
    @DisplayName("이메일 중복을 체크해야 한다.")
    void existEmailTest() {
        // given
        String email = "safdsf@def.com";
        //when
        boolean flag = userRepository.existsByEmail(email);
        //then
        assertFalse(flag);
    }
}