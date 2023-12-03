package aoc2021.Day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class _MainDay03 {

    // Get the numeric value from binary input string
    private static int getValue(String inString) {
        try {
            return  Integer.parseInt(inString,2);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // Get list of input data from file
    private static List<Integer> read(Scanner sc) {
        List<Integer> data = new ArrayList<>();
        while (sc.hasNextLine()) {
            data.add(getValue(sc.nextLine()));
        }
        return data;
    }

    // =====================================================
    // Find Result - Task 1
    private static long findResult_1(List<Integer> data) {
        int gammaRate = 0;
        int epsilonRate = 0;

        // Find the No of '1' for each bit position for all records
        // and count the number of records
        for (int i = 11; i >= 0; i--) {

            int bitOnCount = 0;

            for(int item : data) {
                if ((item & (int)Math.pow(2, i)) != 0) {
                    bitOnCount++;
                }
            }

            // Get gamma and epsilon rates
            if (bitOnCount > (data.size() - bitOnCount)) {
                gammaRate += (int)Math.pow(2, i);
            } else {
                epsilonRate += (int)Math.pow(2, i);
            }

        }

        // Get result
        return gammaRate * epsilonRate;


    }

    // =====================================================
    // Find Result - Task 1
    private static long findResult_2(List<Integer> data) {
        // Clone input List
        List<Integer> dataO2 = new ArrayList<>();
        List<Integer> dataCO2 = new ArrayList<>();
        for (int item :data) {
            dataO2.add(item);
            dataCO2.add(item);
        }

        // do cycle for all bits in all records
        for (int i = 11; i >= 0; i--) {

            // Get binary mask for bit position
            int mask = (int)Math.pow(2, i);

            // count bits ON in all records in 02 list
            int bitCountON = 0;
            for(int item : dataO2) if ((item & mask) != 0) bitCountON++;
            // Get oxygen rating
            boolean criteriaON = bitCountON >= (dataO2.size() - bitCountON);
            Iterator<Integer> itO2 = dataO2.iterator();
            while(itO2.hasNext() && (dataO2.size() > 1)) {
                int item = itO2.next();
                if (criteriaON ^ ((item & mask) != 0)) itO2.remove();
            }

            // count bits OFF in all records in C02 list
            int bitCountOFF = 0;
            for(int item : dataCO2) if ((item & mask) == 0) bitCountOFF++;
            // Get oxygen rating
            boolean criteriaOFF = bitCountOFF <= (dataCO2.size() - bitCountOFF);
            Iterator<Integer> itCO2 = dataCO2.iterator();
            while(itCO2.hasNext() && (dataCO2.size() > 1)) {
                int item = itCO2.next();
                if (criteriaOFF ^ ((item & mask) == 0)) itCO2.remove();
            }

            System.out.println("The list length is " + dataO2.size() + " ... " + dataCO2.size());

        }

        // Get result
        System.out.println("The values are " + dataO2.get(0) + " ... " + dataCO2.get(0));
        return dataO2.get(0) * dataCO2.get(0);

    }

    // ====================================================
    // Main
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/aoc2021/Day03.txt"));
            List<Integer> data = read(sc);
            System.out.println("The result is " + findResult_2(data));
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
