package com.tdtu.backend_android.service;

import java.util.List;

public interface CRUDService<E> {
    List<E> findAll();
    E findById(Long id);
    E save(E entity);
    void delete(Long id);
}

