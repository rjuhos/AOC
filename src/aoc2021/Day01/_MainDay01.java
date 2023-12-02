package AOC2021.Day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _MainDay01 {

    private static List<Integer> read(Scanner sc) {
        List<Integer> data = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            data.add(Integer.parseInt(line));
        }
        return data;
    }

    private static long threeMeasurementWindow(List<Integer> data, int pos) {
        return (data.get(pos-2) + data.get(pos-1) + data.get(pos));
    }

    private static long find(List<Integer> data) {
        long counts = 0;
        for (int i = 3; i < data.size(); i++) {
            if (threeMeasurementWindow(data,(i-1)) < threeMeasurementWindow(data, i)) {
                counts++;
            }
        }
        return counts;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/AOC2022/Day01.txt"));
            List<Integer> data = read(sc);
            System.out.println("the result is " + find(data));
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
