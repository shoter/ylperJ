package com.shoter.ylper.core;

public interface Repository<TEntity> {
    void add(TEntity entity);
    void remove(TEntity entity);
    void update(TEntity entity);
}
