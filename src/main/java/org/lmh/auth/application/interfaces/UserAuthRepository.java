package org.lmh.auth.application.interfaces;

import org.lmh.auth.domain.UserAuth;
import org.lmh.user.domain.User;

public interface UserAuthRepository {
    UserAuth registerUser(UserAuth auth, User user);
    UserAuth loginUser(String email, String password);
}
