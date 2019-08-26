package com.digis.vaa25.demo.api;

import com.digis.vaa25.demo.domain.Gender;
import com.digis.vaa25.demo.domain.User;
import com.digis.vaa25.demo.exception.UserNotFound;
import com.digis.vaa25.demo.service.UserService;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;

    @Test
    public void findUserByLogin() throws Exception {
        final User user = new User()
            .setLogin("vaa25")
            .setFullName("full name")
            .setDob(LocalDate.parse("1990-02-21"))
            .setGender(Gender.MALE);
        when(userService.findUserByLogin("vaa25")).thenReturn(user);
        mvc
            .perform(get("/user/vaa25"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(
                "{\"login\":\"vaa25\",\"fullName\":\"full name\",\"dob\":\"1990-02-21\",\"gender\":\"MALE\"}"));
    }

    @Test
    public void tryFindAbsentUserByLogin() throws Exception {
        when(userService.findUserByLogin("vaa25")).thenThrow(new UserNotFound());
        mvc.perform(get("/user/vaa25")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void createUser() throws Exception {

        final User user = new User()
            .setLogin("vaa25")
            .setFullName("full name")
            .setDob(LocalDate.parse("1990-02-21"))
            .setGender(Gender.MALE);
        when(userService.createUser(user)).thenReturn(user);
        mvc
            .perform(post("/user")
                .content("{\"login\":\"vaa25\",\"fullName\":\"full name\",\"dob\":\"1990-02-21\",\"gender\":\"MALE\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(content().json(
                "{\"login\":\"vaa25\",\"fullName\":\"full name\",\"dob\":\"1990-02-21\",\"gender\":\"MALE\"}"));
    }

    @Test
    public void editUser() throws Exception {

        final User user = new User()
            .setLogin("vaa25")
            .setFullName("full name")
            .setDob(LocalDate.parse("1990-02-21"))
            .setGender(Gender.MALE);
        when(userService.editUser(user)).thenReturn(user);
        mvc
            .perform(put("/user")
                .content("{\"login\":\"vaa25\",\"fullName\":\"full name\",\"dob\":\"1990-02-21\",\"gender\":\"MALE\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(
                "{\"login\":\"vaa25\",\"fullName\":\"full name\",\"dob\":\"1990-02-21\",\"gender\":\"MALE\"}"));
    }
}
