package AOC2022.Day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class _MainDay03 {

    private static char findShare(String[] strings) {
        // preset shared character to Null
            char share = Character.MIN_VALUE;
        // find shared character in strings array
            for (char c: strings[0].toCharArray()) {
                boolean hasShare = IntStream.range(1, strings.length).allMatch(i -> strings[i].indexOf(c) >= 0);
                if (hasShare) { share = c; break; }
            }
        return share;
    }

    private static int getSharePriority(char share) {
        return ((share >= 'a' && share <= 'z') ? (int) share - (int) 'a' + 1 :
                (share >= 'A' && share <= 'Z') ? (int) share - (int) 'A' + 27 :
                0
               );
    }

    private static void printShare(char share, String[] strings) {
        System.out.print("share - " + share + ": " + strings[0]);
        for (int i = 1, stringsLength = strings.length; i < stringsLength; i++)
            System.out.print(" - " + strings[i]);
        System.out.println();
    }

    private static long getResult1(List<String> data) {
        long result = 0;
        for (String s : data) {
            // create strings from input data - split one input into two parts
                String[] strings = { s.substring(0, s.length() / 2), s.substring(s.length() / 2) };
            // get shared character from strings and add the share's priority to the result
                char share = findShare(strings);
                result += getSharePriority(share);
            // print share result
                printShare(share, strings);
        }
        System.out.println();
        return result;
    }

    private static long getResult2(List<String> data, int groupSize) {
        long result = 0;
        int start = 0;
        while (start <= data.size() - groupSize) {
            // create strings array from input data - number of strings defined by group size
                List<String> stringsList = new ArrayList<>();
                for (int i = 0; i < groupSize; i++) stringsList.add(data.get(start + i));
                String[] strings = stringsList.toArray(new String[0]);
            // get shared character from strings and add the share's priority to the result
                char share = findShare(strings);
                result += getSharePriority(share);
            // print share result
                printShare(share, strings);
            // new strings list
                start += groupSize;
        }
        System.out.println();
        return result;
    }

    private static List<String> getInputData(Scanner sc) {
        List<String> data = new ArrayList<>();
        while (true) {
            if (!sc.hasNextLine()) break;
            data.add(sc.nextLine());
        }
        return data;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/AOC2022/Day03.txt"));
            List<String> inputData = getInputData(sc);
            System.out.println();
            System.out.println("the 1st result is ... " + getResult1(inputData));
            System.out.println();
            System.out.println("the 2nd result is ... " + getResult2(inputData,3));
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
