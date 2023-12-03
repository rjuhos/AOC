package aoc2022.Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class _MainDay11 {

    private static Monkey[] monkeys;

    private static Monkey[] getMonkeys(Scanner sc) {
        List<Monkey> monkeyList = new ArrayList<>();
        String[] definition = new String[6];
        while (sc.hasNextLine()) {
            definition[0] = sc.nextLine();
            definition[1] = sc.nextLine();
            definition[2] = sc.nextLine();
            definition[3] = sc.nextLine();
            definition[4] = sc.nextLine();
            definition[5] = sc.nextLine();
            Monkey monkey = new Monkey(definition);
            monkeyList.add(monkey);
            if (sc.hasNextLine()) sc.nextLine();
        }
        Monkey[] monkeys = monkeyList.toArray(new Monkey[0]);
        // get the modulo value from all monky's test values and assign the modulo to them
        long modulo = 1;
        for (Monkey m : monkeys) modulo *= m.getTestValue();
        for (Monkey m : monkeys) m.setModulo(modulo);

        return monkeys;
    }

    private static long getResult() {
        long[] noOfInspections = new long[monkeys.length];

        for (int loop = 0; loop < 10000; loop++) {
            for (Monkey monkey : monkeys) {
                monkey.updateNoOfInspections();
                while (monkey.hasNext()) {
                    Long value = monkey.Next();
                    int toMonkey = monkey.getToMonkey();
                    monkeys[toMonkey].addItem(value);
                }
            }
            for (int i = 0; i< monkeys.length; i++) noOfInspections[i] = monkeys[i].getNoOfInspections();
        }

        Arrays.sort(noOfInspections);
        long result = noOfInspections[noOfInspections.length-1] * noOfInspections[noOfInspections.length-2];

        return result;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/aoc2022/Day11.txt"));
            monkeys = getMonkeys(sc);
            System.out.println("the result is ... " + getResult());
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
