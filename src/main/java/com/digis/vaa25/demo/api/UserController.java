package com.digis.vaa25.demo.api;

import com.digis.vaa25.demo.domain.User;
import com.digis.vaa25.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/user", produces = APPLICATION_JSON_UTF8_VALUE)
public final class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{login}")
    public User findUserByLogin(@PathVariable("login") final String login) {
        return userService.findUserByLogin(login);
    }

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public User createUser(@RequestBody final User user) {
        return userService.createUser(user);
    }

    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    public User editUser(@RequestBody final User user) {
        return userService.editUser(user);
    }

}
