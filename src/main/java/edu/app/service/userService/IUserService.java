package edu.app.service.userService;

import edu.app.service.IService;

public interface IUserService<T> extends IService<T> {
    boolean findUserByUsername(String username);

    boolean findUserByEmail(String email);
}


