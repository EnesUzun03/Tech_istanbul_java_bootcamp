package com.enesuzun.utils;

//Aspect springin bir parçasıdaır tam olarak kullanamadığımız için reflection şeklinde kullanacağız

//LogExuctionTime Annotation kullanan metotları dimleyen sınıftır

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LoggingAspect {
    //Belirtilen nesneledeki tüm methodların kontrolunu yapar @LogExuctionTime anatosyamu
    public static void logExecutionTime(Object obj){

        //reflection
        Class<?> clazz=obj.getClass();

        //for
        for (Method method :clazz.getDeclaredMethods()){
            if(method.isAnnotationPresent(LogEx.class)){
                long start = System.nanoTime();//Baslangıç zamanı
                try{
                    method.setAccessible(true);//Private metodları çağırabilmek için kullanılır
                    method.invoke(obj);//methodu çağır
                }catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }catch(Exception e){
                    e.printStackTrace();
                }//end catch
                long end=System.nanoTime();//bitiş zamanı
                //System.out.println(SpecialColor.GREEN+ method.getName()+ " Metodu " + ((end-start)/1_000_000.0) + " Milisaniye surdu " +SpecialColor.RESET);
                System.out.println(SpecialColor.GREEN+ method.getName()+ " Metodu " + ((end-start)/1000000.0) + " Milisaniye surdu " +SpecialColor.RESET);
            }
        }
    }

}
