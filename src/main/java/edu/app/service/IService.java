package edu.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IService <T> {

    T findById (long id);

    List<T> findAll();

    T save (T t);

    void deleteById(long id);


    Page<T> findAllWithPagination(Pageable pageable);

}
