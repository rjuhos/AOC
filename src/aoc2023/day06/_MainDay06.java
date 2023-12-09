package aoc2023.day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class _MainDay06 {

    private static long[] timeArray;
    private static long timeTotal;
    private static long[] distArray;
    private static long distTotal;

    private static long[] stringToLong(String inString) {
        return Arrays.stream(inString.replaceAll("[^0-9]+",";").split(";"))
                .map(s -> { try {
                    return Long.valueOf(s);
                } catch (NumberFormatException ignored) {
                    return null;
                }
                })
                .filter(Objects::nonNull)
                .mapToLong(x -> x)
                .toArray();
    }

    private static void getInputData(Scanner sc) {

        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (s.contains("Time")) {
                timeArray = stringToLong(s);
                s = s.replaceAll(" +","");
                timeTotal = stringToLong(s)[0];
            } else if (s.contains("Distance")) {
                distArray = stringToLong(s);
                s = s.replaceAll(" +","");
                distTotal = stringToLong(s)[0];
            }
        }
    }

    private static long travelDistance(long speedUpTime, long maxTravelTime) {
        return ( maxTravelTime - speedUpTime ) * speedUpTime;
    }

    private static long getResult1() {
        List<Long> noOfWaysToWin = new ArrayList<>();
        for (int i = 0; i < timeArray.length; i++) {
            long maxTime = timeArray[i];
            long waysToWin = 0;
            for (int t = 1; t < maxTime; t++) {
                waysToWin += (travelDistance(t,maxTime) > distArray[i]) ? 1 : 0;
            }
            if (waysToWin > 0) { noOfWaysToWin.add(waysToWin); }
        }
        long result = 1;
        for (long w : noOfWaysToWin) {
            result *= w; ;
        }
        return result;
    }

    private static long getResult2() {
        long waysToWin = 0;
        for (int t = 1; t < timeTotal; t++) {
            waysToWin += (travelDistance(t,timeTotal) > distTotal) ? 1 : 0;
        }
        return waysToWin;
    }

    public static void main (String[]args){
        try {
            Scanner sc = new Scanner(new File("resources/AOC2023/Day06.txt"));
            getInputData(sc);
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
