package edu.app.service.user_service;


import edu.app.service.IService;

public interface IUserService <T> extends IService<T> {
    boolean findUserByUsername(String username);
}

