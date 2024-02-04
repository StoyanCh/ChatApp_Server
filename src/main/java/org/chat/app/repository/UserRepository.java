package org.chat.app.repository;

import org.chat.app.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    User update(User user);

    User delete(User user);

    Optional<User> findById(int userId);
}
