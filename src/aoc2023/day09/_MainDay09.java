package aoc2023.day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class _MainDay09 {

    private static final List<List<Integer>> listOfSequences = new ArrayList<>();

    private static int[] stringToIntegers(String inString) {
        return
                Arrays.stream(inString.split(" "))
                        .map(s -> { try {
                            return Integer.valueOf(s);
                        } catch (NumberFormatException ignored) {
                            return null;
                        }
                        })
                        .filter(Objects::nonNull)
                        .mapToInt(x -> x)
                        .toArray();
    }

    private static void getListOfSequences(Scanner sc) {
        while (sc.hasNextLine()) {
            List<Integer> sequence = new ArrayList<>();
            sequence = Arrays.stream(stringToIntegers(sc.nextLine())).boxed().collect(Collectors.toList());
            listOfSequences.add(sequence);
        }
    }

    private static int getNextValue(List<Integer> sequence) {
        List<List<Integer>> listOfSequences = new ArrayList<>();
        listOfSequences.add(sequence);
        do {
            List<Integer> newSequence = new ArrayList<>();
            for (int i = 1; i < sequence.size(); i++) {
                newSequence.add(sequence.get(i) - sequence.get(i - 1));
            }
            listOfSequences.add(newSequence);
            sequence = newSequence;
        } while (!sequence.stream().allMatch(i -> i == 0));
        int nextValue = 0;
        listOfSequences.getLast().add(nextValue);
        for (int i = listOfSequences.size() - 1; i > 0; i--) {
            nextValue = listOfSequences.get(i-1).getLast() + listOfSequences.get(i).getLast();
            listOfSequences.get(i-1).add(nextValue);
        }
        return nextValue;
    }
    private static int getFirstValue(List<Integer> sequence) {
        List<List<Integer>> listOfSequences = new ArrayList<>();
        listOfSequences.add(sequence);
        do {
            List<Integer> newSequence = new ArrayList<>();
            for (int i = 1; i < sequence.size(); i++) {
                newSequence.add(sequence.get(i) - sequence.get(i - 1));
            }
            listOfSequences.add(newSequence);
            sequence = newSequence;
        } while (!sequence.stream().allMatch(i -> i == 0));
        int firstValue = 0;
        listOfSequences.getLast().addFirst(firstValue);
        for (int i = listOfSequences.size() - 1; i > 0; i--) {
            firstValue = listOfSequences.get(i-1).getFirst() - listOfSequences.get(i).getFirst();
            listOfSequences.get(i-1).addFirst(firstValue);
        }
        return firstValue;
    }

    private static int getResult1() {
        int sum = 0;
        for (List<Integer> listOfSequence : listOfSequences) {
            sum += getNextValue(listOfSequence);
        }
        return sum;
    }
    private static int getResult2() {
        int sum = 0;
        for (List<Integer> listOfSequence : listOfSequences) {
            sum += getFirstValue(listOfSequence);
        }
        return sum;
    }

    public static void main (String[]args){
        try {
            Scanner sc = new Scanner(new File("resources/AOC2023/Day09.txt"));
            getListOfSequences(sc);
            System.out.println();
            System.out.println("the 1st result is ... " + getResult1());
            System.out.println();
            System.out.println("the 2st result is ... " + getResult2());
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }

}
