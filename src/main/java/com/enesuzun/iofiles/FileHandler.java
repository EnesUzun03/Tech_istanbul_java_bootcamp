package com.enesuzun.iofiles;




/*

import java.io.*;

public class FileHandler implements IFileHandlerInterface{
    //field
    private String filePath="";

    //Constructer
    public FileHandler(){
        filePath="isnotfile.txt";
    }

    //Method
    //dosya yoksa olustur varsa kullan
    private void creatFileNotExists(){

    }

    //dosya yaz
    public void writeFile(String data){
        //java 1.7 try with- resources
        try(BufferedWriter bufferedWriter= new BufferedWriter(new FileWriter(filePath,true))){
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    //dosya oku
    public void readFile(){
        try(BufferedReader bufferedReader= new BufferedReader(new FileReader(filePath))){
            String line="";
            while ((line=bufferedReader.readLine()) != null){
                System.out.println("dosyadan okundu");
            }
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    //gettre and setter
    public String filePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static void main(String[] args) {
        //reflection
    }
}
*/
