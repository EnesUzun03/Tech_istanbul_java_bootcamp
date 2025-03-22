package com.enesuzun.dao;

import java.util.List;
import java.util.Optional;

/**
 * 📌 Generic DAO Arayüzü
 * CRUD işlemleri için temel arayüzdür.
 */
public interface IDaoGenerics<T> {
    Optional<T> create(T entity);
    List<T> list();
    Optional<T> findByName(String name);
    Optional<T> findById(int id);
    Optional<T> update(int id, T entity);
    Optional<T> delete(int id);
    void choose();
}

/*
//Kendi kısmım

import com.enesuzun.dto.StudentDto;

import java.sql.Connection;
import java.util.ArrayList;

public interface IDaoGenerics<T> {
    //Crud işlemlerini daha sade yazmak istiyorsak
    //sadece bu pakettekiler kullansın istiyorsak public ler default yazpılmalı
    //Creat
    T creat(T t);

    //Find by id
    T findbyNama(String name);
    T findbyId(int id);
    //list
    ArrayList<T> list();

    //Update
    T update(int id, T t);
    //Delete
    T delete(int id);

    //Chooise
    void chooise();


    //Body metot normalde yazılmaz interface içerisine
    //database'e hazırlık içim eklemdi
    default Connection getInterfaceConnection(){
      return null;
    };

}
*/
