package aoc2022._Test;

import java.nio.Buffer;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Test {

    public static void main(String[] args) {
        String abc = "[1,2,3,4,5,6 ss";
        Scanner sc = new Scanner(abc);
        String s;
        int i;
        while (sc.hasNext()) {
            if (sc.hasNext("[^0-9]")) {
                s = sc.next("[^0-9]");
            };
            i = sc.nextInt();
        }
        boolean b = abc.isEmpty();
        int x = 5;
        int y = 7;
        int[][] intArray = new int[x][y];
        System.out.println(intArray.length);
        int in = 's';
        char a = (char) ('a' - 1);
    }
}