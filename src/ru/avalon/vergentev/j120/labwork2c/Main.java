package ru.avalon.vergentev.j120.labwork2c;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        new Decripter(new File("script.txt")).dataDecripter();
    }
}
