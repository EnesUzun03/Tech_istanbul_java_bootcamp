package com.enesuzun._3_week;

import lombok.*;

import java.util.Date;

@NoArgsConstructor//Parametresiz contructer
@AllArgsConstructor//Parametreli
@Getter
@Setter
@ToString
@Builder

public class Week3_06_Class_LOMBOK {
    //field
    private long id;
    private String name;
    private String surname;
    private Date createdDate;

    public static void main(String[] args) {
        Week3_06_Class_LOMBOK lombok=new Week3_06_Class_LOMBOKBuilder()
                .id(1L)
                .name("Enes")
                .surname("Uzun")
                .createdDate(new Date(System.currentTimeMillis()))
                .build();

        System.out.println(lombok);

    }
}
