package com.enesuzun.dao;

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
