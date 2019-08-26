package com.digis.vaa25.demo.service;

import com.digis.vaa25.demo.domain.User;

public interface UserService {
    /**
     * Finds user in database.
     *
     * @param login user login.
     * @return user.
     */
    User findUserByLogin(String login);

    /**
     * Creates user in database. User login should be unique.
     *
     * @param user user.
     * @return created user.
     */
    User createUser(User user);

    /**
     * Edits user in database. User with same login should be in database.
     *
     * @param user user.
     * @return edited user.
     */
    User editUser(User user);
}
