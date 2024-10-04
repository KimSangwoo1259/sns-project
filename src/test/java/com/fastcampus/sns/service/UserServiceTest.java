package com.fastcampus.sns.service;

import com.fastcampus.sns.Fixture.UserEntityFixture;
import com.fastcampus.sns.exception.SnsApplicationException;
import com.fastcampus.sns.model.entity.UserEntity;
import com.fastcampus.sns.repository.UserEntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @MockBean
    private UserEntityRepository userEntityRepository;


    @Test
    void 회원가입이_정상적으로_동작하는_경우() {
        String userName = "name";
        String password = "pwd";

        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.empty());
        when(userEntityRepository.save(any())).thenReturn(Optional.of(UserEntityFixture.get(userName, password ,1L)));


        Assertions.assertDoesNotThrow(() -> userService.join(userName,password));
    }
    @Test
    void 회원가입시_userName으로_회원가입된_유저가_이미_있는경우() {
        String userName = "name";
        String password = "pwd";

        UserEntity fixture = UserEntityFixture.get(userName, password ,1L);

        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(fixture));
        when(userEntityRepository.save(any())).thenReturn(Optional.of(mock(UserEntity.class)));


        Assertions.assertThrows(SnsApplicationException.class, () -> userService.join(userName,password));
    }

    @Test
    void 로그인이_정상적으로_동작하는_경우() {
        String userName = "name";
        String password = "pwd";

        UserEntity fixture = UserEntityFixture.get(userName, password ,1L);

        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(fixture));


        Assertions.assertDoesNotThrow(() -> userService.login(userName, password));
    }

    @Test
    void 로그인시_없는UserName입력시_에러반환() {
        String userName = "name";
        String password = "pwd";

        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.empty());


        Assertions.assertThrows(SnsApplicationException.class, () -> userService.join(userName,password));
    }

    @Test
    void 로그인시_틀린pwd_에러반환() {
        String userName = "name";
        String password = "pwd";
        String wrongPassword = "wrongPassword";

        UserEntity fixture = UserEntityFixture.get(userName, password ,1L);

        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(fixture));


        Assertions.assertThrows(SnsApplicationException.class, () -> userService.join(userName,wrongPassword));
    }
}
