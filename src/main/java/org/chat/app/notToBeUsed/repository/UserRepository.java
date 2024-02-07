package org.chat.app.notToBeUsed.repository;

import org.chat.app.notToBeUsed.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    User update(User user);

    User delete(User user);

    Optional<User> findById(int userId);
}
