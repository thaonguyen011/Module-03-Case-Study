package com.example.case_study.model.DAO;

import java.sql.Connection;
import java.util.List;

public interface IEntityDAO<E> {
    Connection getConnection();
    List<E> selectAll();
    E selectById(int id);
    boolean insert(E e);
    boolean update(E e);
    boolean remove(int id);
}
