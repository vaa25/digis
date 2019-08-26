package com.digis.vaa25.demo.service;

import com.digis.vaa25.demo.domain.User;
import com.digis.vaa25.demo.exception.UserAlreadyExists;
import com.digis.vaa25.demo.exception.UserNotFound;
import com.digis.vaa25.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByLogin(final String login) {
        return userRepository.findById(login).orElseThrow(UserNotFound::new);
    }

    @Override
    @Transactional
    public User createUser(final User user) throws UserAlreadyExists {
        if (userRepository.existsById(user.getLogin())) {
            throw new UserAlreadyExists();
        } else {
            return userRepository.saveAndFlush(user);
        }
    }

    @Override
    @Transactional
    public User editUser(final User user) {
        if (!userRepository.existsById(user.getLogin())) {
            throw new UserNotFound();
        } else {
            return userRepository.saveAndFlush(user);
        }
    }
}
