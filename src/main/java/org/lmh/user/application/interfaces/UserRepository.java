package org.lmh.user.application.interfaces;

import org.lmh.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);
    User findById(Long id);
}
