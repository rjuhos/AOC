package aoc2023.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class _MainDay01 {

    private static List<String> inputData;
    private static final Map<String, Integer> numDigit = Map.of(
            "0", 0,
            "1", 1,
            "2", 2,
            "3", 3,
            "4", 4,
            "5", 5,
            "6", 6,
            "7", 7,
            "8", 8,
            "9", 9
    );
    private static final Map<String, Integer> numString = Map.of(
            "zero", 0,
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9
    );

    private static List<String> getInputData(Scanner sc) {
        List<String> data = new ArrayList<>();
        while (sc.hasNextLine()) {
            data.add(sc.nextLine());
        }
        return data;
    }

    private static int getSimpleCode(String str) {
        int code = 0;
        boolean bFirst = false;
        int digit = 0;
        for (char c : str.toCharArray()) {
            switch (c) {
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
                    digit = c - '0';
                    if (!bFirst) {
                        code = 10 * digit;
                        bFirst = true;
                    }
                    break;
                default:
                    // nop
            }
        }
        code += digit;
        return code;
    }

    private static int getCode(String str, Map<String, Integer> num1, Map<String, Integer> num2) {
        int i;
        int code = 0;
        String subString;

        boolean found = false;
        for (i = 0; i < str.length(); i++) {
            subString = str.substring(i);
            for (String key : num1.keySet()) {
                if (subString.startsWith(key)) {
                    code += 10 * num1.get(key);
                    found = true;
                    break;
                }
            }
            if (!found) {
                for (String key : num2.keySet()) {
                    if (subString.startsWith(key)) {
                        code += 10 * num2.get(key);
                        found = true;
                        break;
                    }
                }
            }
            if (found) break;
        }

        found = false;
        for (i = str.length(); i >= 1; i--) {
            subString = str.substring(0, i);
            for (String key : num1.keySet()) {
                if (subString.endsWith(key)) {
                    code += num1.get(key);
                    found = true;
                    break;
                }
            }
            if (!found) {
                for (String key : num2.keySet()) {
                    if (subString.endsWith(key)) {
                        code += num2.get(key);
                        found = true;
                        break;
                    }
                }
            }
            if (found) break;
        }
        return code;
    }

    private static int getResult1() {
        int result = 0;
        for (String s : inputData) {
            result += getCode(s, numDigit, numDigit);
        }
        return result;
    }

    private static int getResult2() {
        int result = 0;
        for (String s : inputData) {
            result += getCode(s, numDigit, numString);
        }
        return result;
    }

        public static void main (String[]args){
            try {
                Scanner sc = new Scanner(new File("resources/AOC2023/Day01.txt"));
                inputData = getInputData(sc);
                System.out.println();
                System.out.println("the 1st result is ... " + getResult1());
                System.out.println();
                System.out.println("the 2nd result is ... " + getResult2());
                System.out.println();
            } catch (FileNotFoundException e) {
                System.out.println("this file does not exist");
            }
        }
    }