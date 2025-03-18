package com.enesuzun.controller;

import com.enesuzun.dao.IDaoGenerics;
import com.enesuzun.dao.StudentDao;
import com.enesuzun.dto.StudentDto;

import java.util.ArrayList;

public class StudentController implements IDaoGenerics<StudentDto> {


    //İnjection
    private StudentDao studentDao;

    //Constructer
    public StudentController(){
        studentDao=new StudentDao();
    }

    /// //////////////////////////////////////////

    @Override
    public StudentDto creat(StudentDto studentDto) {
        return studentDao.creat(studentDto);
    }

    @Override
    public StudentDto findbyNama(String name) {
        return studentDao.findbyNama(name);
    }

    @Override
    public ArrayList<StudentDto> list() {
        return studentDao.list();
    }

    @Override
    public StudentDto update(int id, StudentDto studentDto) {
        return studentDao.update(id,studentDto);
    }

    @Override
    public StudentDto delete(int id) {
        return studentDao.delete(id);
    }

    @Override
    public void chooise() {
        studentDao.chooise();
    }
}
