package aoc2023.day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class _MainDay04 {

    private static List<Card> cards;

    private static List<Card> getCards(Scanner sc) {
        List<Card> cards = new ArrayList<>();
        while (sc.hasNextLine()) {
            Card card = new Card(sc.nextLine());
            cards.add(card);
        }
        return cards;
    }
    private static int getResult1() {
        return cards.stream().mapToInt(Card::getPoints).sum();
    }

    private static int getResult2() {
        cards.forEach(Card::resetCopies);
        cards.forEach(c -> { int pos = cards.indexOf(c); int matches = c.getMatches();
                             for (int i = 1; i <= matches; i++) {
                                if (pos + i < cards.size()) {
                                    Card next = cards.get(pos + i);
                                    next.addCopies(c.getNoOfCards());
                                    cards.set(pos + i, next);
                                } else {
                                    break;
                                }
                             }
                           }
                     );
        return cards.stream().mapToInt(Card::getNoOfCards).sum();
    }

    public static void main (String[]args){
        try {
            Scanner sc = new Scanner(new File("resources/AOC2023/Day04.txt"));
            cards = getCards(sc);
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
