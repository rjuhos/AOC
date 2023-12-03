package aoc2021.Day14;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _MainDay14 {

    private static String getInitString(Scanner scanner) {
        if (scanner.hasNextLine())
            return scanner.nextLine();
        return "";
    }

    private static Rule getRules( Scanner scanner) {
        Rule rules = new Rule();
        // get the rules
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains(" -> "))
                rules.addRule(line);
            }
        rules.growPair("NN");
        String newPair1 = rules.getNewPair1();
        String newPair2 = rules.getNewPair2();
        List<String> polymerData = rules.getPolymerData();
        return rules;
    }

    private static long findResult(String initString, Rule rules, int noOfSteps) {
        // get polymer data
        List<String> polymerData = rules.getPolymerData();
        // init counters
        long[] charCounter = new long['Z'-'@'];
        long[] polymerDataCounter = new long[polymerData.size()];
        for (int i=0; i<initString.length(); i++) {
            if (i<(initString.length() - 1))
                polymerDataCounter[polymerData.indexOf(initString.substring(i,i+2))]++;
            charCounter[initString.charAt(i) - 'A']++;
        }

        // grow polymer
        for (int step=0;  step<noOfSteps; step++) {
            long[] newCounter = new long[polymerData.size()];
            for (int i=0; i< polymerData.size(); i++) {
                if (polymerDataCounter[i] > 0) {
                    rules.growPair(polymerData.get(i));
                    newCounter[polymerData.indexOf(rules.getNewPair1())] += polymerDataCounter[i];
                    newCounter[polymerData.indexOf(rules.getNewPair2())] += polymerDataCounter[i];
                    charCounter[rules.getNewChar() - 'A'] += polymerDataCounter[i];
                }
            }
            polymerDataCounter = newCounter;
        }

        // find the most and the least common character numbers
        long min = Long.MAX_VALUE;
        long max = 0;
        for (int i=0; i<charCounter.length; i++) {
            if ((charCounter[i] < min) && (charCounter[i] != 0)) min = charCounter[i];
            if (charCounter[i] > max) max = charCounter[i];
        }

        return max - min;
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("resources/aoc2021/Day14.txt"));
            String initString = getInitString(scanner);
            Rule rules = getRules(scanner);

            long result = findResult(initString,rules,40);

            System.out.println("The result is ... " + result);
            System.out.println();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
