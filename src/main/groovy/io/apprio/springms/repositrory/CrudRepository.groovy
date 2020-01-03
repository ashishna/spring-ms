package io.apprio.springms.repositrory

interface CrudRepository<T> {

    T create(T t)
    T read(T t)
    T update(T t)
    T delete(T t)
}