package com.dinhpu.m4casestudy.services;

import java.util.List;

public interface IServices<T> {
    public List<T> findAll();
    public T findById(Integer id);
    public T save(T model);
    public T remove(Integer id);

}
