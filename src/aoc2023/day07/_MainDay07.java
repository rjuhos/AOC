package aoc2023.day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class _MainDay07 {
    private static List<Hand> listOfHands;
    private static void getListOfHands(Scanner sc) {
        listOfHands = new ArrayList<>();
        while (sc.hasNextLine()) {
            Hand hand = new Hand(sc.nextLine());
            listOfHands.add(hand);
        }
        listOfHands.sort(Comparator.comparing(Hand::getHandType).thenComparing(Hand::getCardsSubstituted));
    }

    private static long getTotalWinnings() {
        long total = 0;
        for (Hand h : listOfHands) {
            total += (long) h.getBid() * (listOfHands.indexOf(h) + 1);
        }
        return total;
    }

    public static void main (String[]args){
        try {
            Scanner sc = new Scanner(new File("resources/AOC2023/Day07.txt"));
            getListOfHands(sc);
            System.out.println();
            System.out.println("the 1st result is ... " + getTotalWinnings());
            System.out.println();
//            System.out.println("the 2nd result is ... " + getResult2());
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }

}

