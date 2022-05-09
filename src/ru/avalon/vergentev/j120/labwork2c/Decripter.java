package ru.avalon.vergentev.j120.labwork2c;
import java.io.*;
import java.util.*;

public class Decripter {
    File file;
    StringBuilder data, dataWithoutComments;
    String[] dataEachString, dataEachStringWithoutComments, dataEachStringForPrint;
    Properties map = new Properties();

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
        dataEachStringWithoutComments = dataWithoutComments.toString().split(System.lineSeparator());
        return dataEachStringWithoutComments;
    }

    public void dataDecripter () {
        for (String i : getDataEachStringWithoutComments()) {
            if      (i.contains("print")) {
                printDecripter(i);
            }
            else if (i.contains("set")) {
                setDecripter(i);
            }
        }
    }

    public void printDecripter (String string) {
        dataEachStringForPrint = string.replaceAll("print ", "").replaceAll("\n", "").split("\"");
        for (int i = 0; i < dataEachStringForPrint.length; i++) {
            if (i % 2 == 1) {
                System.out.print(dataEachStringForPrint[i]);
            } else {
                if (map.containsKey(dataEachStringForPrint[i].replaceAll(" ", "").replaceAll(",", ""))) {
                System.out.print(map.get(dataEachStringForPrint[i].replaceAll(" ", "").replaceAll(",", "")));
                }
            }
        }
        System.out.print(System.lineSeparator());
    }

    public void setDecripter (String string) {
        String [] dataEachStringForSet = string.replaceAll("set ", "").replaceAll(",", "").split(" = ");
        String [] dataAfterEqual = dataEachStringForSet[1].split(" ");
        if (dataAfterEqual.length == 1) {
            map.put(dataEachStringForSet[0].replaceAll(" ", ""), dataEachStringForSet[1].replaceAll(" ", ""));
        } else {
            StringBuilder temp = new StringBuilder();
            for (String i : dataAfterEqual) {
                if (map.containsKey(i)) {
                    temp.append(map.get(i));
                } else {
                    temp.append(i);
                }
            }
            String [] numbers = temp.toString().split("[+-]");
            String [] signs = temp.toString().split("[0-9]+");
            int result = Integer.parseInt(numbers[0]);
            for (int i = 1; i < numbers.length; i++){
                if      (signs[i].equals("+")) result += Integer.parseInt(numbers[i]);
                else if (signs[i].equals("-")) result -= Integer.parseInt(numbers[i]);
            }
            map.put(dataEachStringForSet[0].replaceAll(" ", ""), String.valueOf(result));
        }
    }
}

