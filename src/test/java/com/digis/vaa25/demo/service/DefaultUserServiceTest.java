package com.digis.vaa25.demo.service;

import com.digis.vaa25.demo.domain.Gender;
import com.digis.vaa25.demo.domain.User;
import com.digis.vaa25.demo.exception.UserAlreadyExists;
import com.digis.vaa25.demo.exception.UserNotFound;
import com.digis.vaa25.demo.repository.UserRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService service;

    @Before
    public void setUp() {
        service = new DefaultUserService(userRepository);
    }

    @Test
    public void findUserByLogin() {
        final User user = new User()
            .setLogin("vaa25")
            .setFullName("full name")
            .setDob(LocalDate.parse("1990-02-21"))
            .setGender(Gender.MALE);
        when(userRepository.findById("vaa25")).thenReturn(Optional.ofNullable(user));

        final User actual = service.findUserByLogin("vaa25");

        assertEquals(user, actual);
    }

    @Test(expected = UserNotFound.class)
    public void notFoundWhenTryFindUserByLogin() {
        when(userRepository.findById("vaa25")).thenReturn(Optional.empty());

        service.findUserByLogin("vaa25");

    }

    @Test
    public void createUser() {
        final User user = new User()
            .setLogin("vaa25")
            .setFullName("full name")
            .setDob(LocalDate.parse("1990-02-21"))
            .setGender(Gender.MALE);
        when(userRepository.existsById("vaa25")).thenReturn(false);
        when(userRepository.saveAndFlush(user)).thenReturn(user);

        final User actual = service.createUser(user);

        assertEquals(user, actual);
    }

    @Test(expected = UserAlreadyExists.class)
    public void throwsUserAlreadyExistsWhenCreateUser() {
        final User user = new User()
            .setLogin("vaa25")
            .setFullName("full name")
            .setDob(LocalDate.parse("1990-02-21"))
            .setGender(Gender.MALE);

        when(userRepository.existsById("vaa25")).thenReturn(true);

        service.createUser(user);
    }

    @Test
    public void editUser() {
        final User user = new User()
            .setLogin("vaa25")
            .setFullName("full name")
            .setDob(LocalDate.parse("1990-02-21"))
            .setGender(Gender.MALE);
        when(userRepository.existsById("vaa25")).thenReturn(true);
        when(userRepository.saveAndFlush(user)).thenReturn(user);

        final User actual = service.editUser(user);

        assertEquals(user, actual);
    }

    @Test(expected = UserNotFound.class)
    public void throwsUserNotFoundWhenEditUser() {
        final User user = new User()
            .setLogin("vaa25")
            .setFullName("full name")
            .setDob(LocalDate.parse("1990-02-21"))
            .setGender(Gender.MALE);

        when(userRepository.existsById("vaa25")).thenReturn(false);

        service.editUser(user);
    }
}
