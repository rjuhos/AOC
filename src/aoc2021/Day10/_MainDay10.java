package AOC2021.Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.text.MessageFormat.*;

public class _MainDay10 {

    private static List<Chunks> readSource(Scanner sc) {
        List<Chunks> chunks = new ArrayList<>();
        while (sc.hasNextLine()) {
            Chunks ch = new Chunks(sc.nextLine());
            chunks.add(ch);
        }
        return chunks;
    }

    private static long findResultPart1(List<Chunks> chunks) {
        long result = 0;
        // read all records and check for errors
        for (Chunks ch : chunks) if (ch.errNo > 0 ) result += ch.errNo;
        // return the error code
        return result;
    }

    private static long findResultPart2(List<Chunks> chunks) {
        // autocomplete the non corrupt records and find the median of autocomplete points
        List<Long> pts = new ArrayList<>();
        for (Chunks ch : chunks) if (ch.getClosingPoint() > 0 ) pts.add(ch.closingPoints);
        if (pts.size() > 0) {
            Long[] closingPoints = (Long[]) pts.toArray(new Long[pts.size()]);
            Arrays.sort(closingPoints);
            return closingPoints[(closingPoints.length -1)/2];
        } else return 0;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/AOC2021/Day10.txt"));
            List<Chunks> chunks = readSource(sc);

            System.out.format("\nThe 1st part result is .... % 12d", findResultPart1(chunks));
            System.out.format("\nThe 2nd part result is .... % 12d", findResultPart2(chunks));
            System.out.format("\n");

        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }


    }


}



