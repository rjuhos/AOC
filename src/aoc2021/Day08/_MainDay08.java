package AOC2021.Day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _MainDay08 {

    private static List<Pattern> readSource(Scanner scanner) {
        List<Pattern> patterns = new ArrayList<>();
        Pattern p;

        while (scanner.hasNextLine()) {
            p = new Pattern(scanner.nextLine());
            patterns.add(p);
        }

        return patterns;
    }

    private static long getResult(List<Pattern> patterns) {
        long result = 0;
        for (Pattern p : patterns)
            result += p.outputValue;
        return result;
    }

    public static void main (String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/AOC2021/Day08.txt"));
            List<Pattern> patterns = readSource(sc);

            System.out.println("The result is ... " + getResult(patterns));
            System.out.println();

        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }

    }
}
