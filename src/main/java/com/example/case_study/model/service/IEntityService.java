package com.example.case_study.model.service;

import java.util.List;

public interface IEntityService<E> {
    List<E> selectAll();
    E selectById(int id);
    boolean insert(E e);
    boolean update(E e);
    boolean remove(int id);
}
