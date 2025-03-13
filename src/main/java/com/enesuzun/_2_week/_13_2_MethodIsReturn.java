package com.enesuzun._2_week;


public class _13_2_MethodIsReturn {
    //returnlu parametresiz
    public String metotReturnluParametresiz(){
        return "metot Returnlu Parametresiz";
    }
    public Integer metotReturnluParametresli(int number){
        return number;
    }

    public static void main(String[] args) {
        _13_2_MethodIsReturn isReturn1=new _13_2_MethodIsReturn();
        System.out.println(isReturn1.metotReturnluParametresiz());
        System.out.println(isReturn1.metotReturnluParametresli(22));


    }
}
