package aoc2022.Day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _MainDay01 {

    private static List<Long> read(Scanner sc) {
        List<Long> data = new ArrayList<>();

        long totalCalories = 0;
        while (sc.hasNextLine()) {
            try {
                totalCalories += Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                data.add(totalCalories);
                totalCalories = 0 ;
            }
        }
        if (totalCalories!=0) {
            data.add(totalCalories);
        }
        return data;
    }

    private static void sort(List<Long> data) {
        for (int i = data.size() - 1; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                if (data.get(j - 1) > data.get(j)) {
                    long temp = data.get(j - 1);
                    data.set(j - 1, data.get(j));
                    data.set(j, temp);
                }
            }
        }
    }

    private static long findMax(List<Long> data) {
        // sort data
        sort(data);
        // return max
        long max = data.get(data.size() - 1) + data.get(data.size() - 2) + data.get(data.size() - 3);
        return max;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/aoc2022/Day01.txt"));
            List<Long> data = read(sc);
            System.out.println("the maximal total calories is " + findMax(data));
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
