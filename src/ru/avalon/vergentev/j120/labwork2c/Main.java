package ru.avalon.vergentev.j120.labwork2c;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>гюдюмхе 3<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        Decripter decripter = new Decripter(new File("script.txt"));
//        decripter.printFileDataInConsole();
//        decripter.printDecriptData();
        decripter.dataDecripter();
    }
}
