package AOC2021.Day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class _MainDay02 {

    private static String cmdForward = "forward";
    private static String cmdUp = "up";
    private static String cmdDown = "down";
    private static String delimiter = " ";

    private static long getUnits(String inString) {
        try {
            return  Integer.parseInt(inString.substring(inString.indexOf(delimiter) + 1));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static long findResult(Scanner sc) {
        long horizontalPosition = 0;
        long verticalPosition = 0;
        long aim = 0;

        while (sc.hasNextLine()) {
            String record = sc.nextLine();
            long moveUnits = getUnits(record);
            if (record.contains(cmdForward)) {
                horizontalPosition += moveUnits;
                verticalPosition += moveUnits * aim;
            } else if (record.contains(cmdUp)) {
                aim -= moveUnits;
            } else if (record.contains(cmdDown)) {
                aim += moveUnits;
            }
        }

        return horizontalPosition * verticalPosition;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/AOC2021/Day02.txt"));
            System.out.println("the result is " + findResult(sc));
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
