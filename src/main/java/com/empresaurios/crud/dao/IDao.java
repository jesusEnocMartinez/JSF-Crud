package com.empresaurios.crud.dao;

import java.util.List;

public interface IDao<T, V> {

    public void insert(T entity);

    public T selectById(V id);

    public List<T> select();

    public boolean delete(V id);

    public void update(T entity);
}
