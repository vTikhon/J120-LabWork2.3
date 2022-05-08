package ru.avalon.vergentev.j120.labwork2c;
import java.io.*;
import java.util.*;

public class Decripter {
    File file;
    StringBuilder data, dataWithoutComments, temp;
    String[] dataEachString, dataEachStringWithoutComments, dataEachStringForPrint, dataEachStringForSet, dataEachStringForSetEachFragment;
    Properties map;

    public Decripter (File file) {
        this.file = file;
        reader();
    }

    //METHODS
    public StringBuilder reader () {
        try {
            if (!file.exists() || !file.canRead()) throw new SecurityException("File doesn't exist or can't be readable !!!");
            int symbolExisting;
            FileReader fileReader = new FileReader(file);
            data = new StringBuilder();
            while ((symbolExisting = fileReader.read()) != -1) {
                data.append((char)symbolExisting);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String[] getDataEachStringWithoutComments () {
        dataEachString = reader().toString().split("\n");
        dataWithoutComments = new StringBuilder();
        for (String i : dataEachString) {
            if (i.charAt(0) != '#' & !i.isBlank()) {
                dataWithoutComments.append(i).append('\n');
            }
        }
        dataEachStringWithoutComments = dataWithoutComments.toString().split("\n");
        return dataEachStringWithoutComments;
    }


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




    public void dataDecripter () {
        map = new Properties();
        for (String i : getDataEachStringWithoutComments()) {
            if      (i.contains("print")) {
//                printDecripter(i);
            }
            else if (i.contains("set")) {
                setDecripter(i);
            }
        }


//        if (dataEachStringForSet[1].contains("+") || dataEachStringForSet[1].contains("-")) {
//            dataEachStringForSetEachFragment = dataEachStringForSet[1].split(" ");
//            for (String j : dataEachStringForSetEachFragment) {
//                for (Object k : map.keySet()) {
//                    if (j.equals(k)) {
//                        j = String.valueOf(map.get(k));
//                    }
//                }
//            }
//        }
//        for (Object k : map.keySet()) {
//            System.out.println(k);
//        }

//        System.out.println(dataEachStringForSet[0] + " = " + map.get(dataEachStringForSet[0]));
    }

    public void printDecripter (String string) {
        dataEachStringForPrint = string.replace("print ", "").split("\"");
        for (int i = 0; i < dataEachStringForPrint.length; i++) {
            if (i%2 == 1) {
                System.out.print(dataEachStringForPrint[i]);
            } else {
                if (dataEachStringForPrint[i].contains("$")) {
                    System.out.print(dataEachStringForPrint[i].replace(" ", "").replace(",", ""));
                }
            }
        }
        System.out.println("");
    }

    public void setDecripter (String string) {
        temp = new StringBuilder();
        String [] dataEachStringForSet = string.replace("set ", "").split(" = ");
        String [] dataAfterEqual = dataEachStringForSet[1].split(" ");
//        System.out.println(dataAfterEqual.length);
//        System.out.println(dataEachStringForSet[1]);
        if (dataAfterEqual.length == 1) {
            map.put(dataEachStringForSet[0].replace(" ", ""), dataEachStringForSet[1].replace(" ", ""));
        } else {
            for (int i = 0; i < dataAfterEqual.length; i++) {
                if (dataAfterEqual[i].contains("$") && map.containsKey(dataAfterEqual[i])) {
//                    dataAfterEqual[i] = String.valueOf(map.get(dataAfterEqual[i]));
                    temp.append(String.valueOf(map.get(dataAfterEqual[i])));
                } else {
                    temp.append(dataAfterEqual[i]);
                }
//                System.out.println(map.getProperty(dataAfterEqual[i]));

//                System.out.println(temp);
            }
            System.out.println(temp);
//            for (String i : dataAfterEqual) {
////                System.out.println(i);
//                temp.append(i);
//            }
//            System.out.println(temp);
        }

    }
}

