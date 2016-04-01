package com.dillselectric.contracts;

import java.util.List;

public interface Repository <T> {
    List<T> getAll();
    void insert(T item);
    void update(T item);
    T findById(int id);
    void save();
    void delete(int id);
}
