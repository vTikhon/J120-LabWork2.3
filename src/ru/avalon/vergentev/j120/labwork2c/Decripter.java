package ru.avalon.vergentev.j120.labwork2c;
import java.io.*;
import java.util.*;

public class Decripter {
    File file;
    FileReader fileReader;
    FileWriter fileWriter;
    StringBuilder data, dataWithoutComments;
    String[] dataEachString, dataEachStringForPrint, dataEachStringForSet, dataEachStringForSetEachFragment;
    Properties map;

    public Decripter (File file) {
        try {
            this.file = file;
            if (!file.exists() || !file.canRead()) throw new SecurityException("File doesn't exist or can't be readable !!!");
            int symbolExisting;
            fileReader = new FileReader(file);
            data = new StringBuilder();
            while ((symbolExisting = fileReader.read()) != -1) {
                data.append((char)symbolExisting);
            }
            dataEachString = data.toString().split("\n");
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //METHODS
//    public void printFileDataInConsole () {
//        if (!file.exists() || !file.canRead())  throw new SecurityException("File can't be readable or doesn't exist !!!");
//        try {
//            int symbolExisting;
//            fileReader = new FileReader(file);
//            while ((symbolExisting = fileReader.read()) != -1) {
//                System.out.print((char)symbolExisting);
//            }
//            fileReader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void printDecriptData () {
//        dataWithoutComments = new StringBuilder();
//        for (String i : dataEachString) {
//            if (!(i.charAt(0)=='#') && !(i.length() == 1)) {
//                dataWithoutComments.append(i).append('\n');
//            }
//        }
//        dataEachString = dataWithoutComments.toString().split("\n");
//        for (String i : dataEachString)
//        System.out.println(i);
//    }


    //основной метод преобразования и анализа кода
    public void dataDecripter () {
        //убираем строки содержащие комментарии и пустые строки
        dataWithoutComments = new StringBuilder();
        for (String i : dataEachString) {
            if (i.charAt(0) != '#' && !i.isEmpty()) {
                dataWithoutComments.append(i).append('\n');
            }
        }
        //анализируем каждую из оставшихся строк на содержание операторов print или set
        map = new Properties();
        dataEachString = dataWithoutComments.toString().split("\n");
        for (String i : dataEachString) {
            if (i.contains("print")) {
                printDecripter(i);
            }
            else if (i.contains("set")) {
                setDecripter(i);
            }
        }



        if (dataEachStringForSet[1].contains("+") || dataEachStringForSet[1].contains("-")) {
            dataEachStringForSetEachFragment = dataEachStringForSet[1].split(" ");
            for (String j : dataEachStringForSetEachFragment) {
                for (Object k : map.keySet()) {
                    if (j.equals(k)) {
                        j = String.valueOf(map.get(k));
                    }
                }
            }
        }
        for (Object k : map.keySet()) {
            System.out.println(k);
        }

//        System.out.println(dataEachStringForSet[0] + " = " + map.get(dataEachStringForSet[0]));
    }

    //вспомогательный метод, который работает с оператором print
    public String printDecripter (String i) {
        dataEachStringForPrint = i.replace("print ", "").split("\"");
        for (int j = 1; j < dataEachStringForPrint.length; j = j + 2) {
//            System.out.println(dataEachStringForPrint[j]);
        }
        return i;
    }

    //вспомогательный метод, который работает с оператором set
    public String setDecripter (String i) {
        dataEachStringForSet = i.replace("set ", "").split(" = ");
        map.put(dataEachStringForSet[0], dataEachStringForSet[1]);
//        System.out.println(dataEachStringForSet[0] + " = " + map.get(dataEachStringForSet[0]));
        return i;
    }
}

