package com.enesuzun.tutorials._4_week;

import com.enesuzun.project.step2.EStudentType;

public class Week4_01_Enum {
    public static void main(String[] args) {
        EStudentType eStudentType=EStudentType.GRADUATE;
        System.out.println(eStudentType);
        System.out.println(eStudentType.ordinal());
        System.out.println(eStudentType.name());
    }
}
