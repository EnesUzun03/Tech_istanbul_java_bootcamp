package com.enesuzun.tutorials._2_week;


public class _13_1_MethodIsNotReturn {
    //Metotlar (Returnsuz Parametresiz)
    public void metotParametresiz(){
        System.out.println("metotParametresiz");
    }

    //Metotlar (Returnsuz Parametreli)
    public static void metotParametreli(String name){
        System.out.println("metotParametresiz"+name);
    }
    //Metotlar (Returnsuz Parametreli)

    public static void metotParametreli(String name,int age){
        System.out.println("metotParametresiz"+name + " " + age);
    }
    //new
    //static
    public static void main(String[] args) {
        _13_1_MethodIsNotReturn data1=new _13_1_MethodIsNotReturn();
        data1.metotParametresiz();

        //intance yapısı olmadan çağırma (new olmadan çağırma)
        _13_1_MethodIsNotReturn.metotParametreli("Enes UZUN");
        _13_1_MethodIsNotReturn.metotParametreli("Enes UZUN" ,22);
    }

}
