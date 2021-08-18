package edu.app.service;

import java.util.List;

public interface IService <T> {

    T findById (long id);

    List<T> findAll();

    T save (T t);

    void deleteById(long id);

}
