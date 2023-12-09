package aoc2023.day07;

import java.util.Arrays;
import java.util.Collections;

public class Hand {

    private final char[] CARDS = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
    private HandType handType;
    private String cards;
    private int bid;
    private int[] cardCount = new int[CARDS.length];
    private int noOfJokers = 0;
    private void countCards() {
        for (int i = 0; i < CARDS.length; i++ ) {
            int pos = i;
            this.cardCount[pos] = (int) this.cards.chars().filter(ch -> ch == CARDS[pos]).count();
        }
    }

    private void getHandType(int[] cardCount) {
        this.noOfJokers = cardCount[9];
        cardCount[9] = 0;
        cardCount = Arrays.stream(cardCount).boxed()
                        .sorted(Collections.reverseOrder())
                        .mapToInt(Integer::intValue)
                        .toArray();
        cardCount[0] += this.noOfJokers;
        if (cardCount[0] == 5) {
            this.handType = HandType.FiveOfAKind;
        } else if (cardCount[0] == 4) {
            this.handType = HandType.FourOfAKind;
        } else if (cardCount[0] == 3 & cardCount[1] == 2) {
            this.handType = HandType.FullHouse;
        } else if (cardCount[0] == 3) {
            this.handType = HandType.ThreeOfAKind;
        } else if (cardCount[0] == 2 & cardCount[1] == 2) {
            this.handType = HandType.TwoPair;
        } else if (cardCount[0] == 2) {
            this.handType = HandType.OnePair;
        }
    }

    public HandType getHandType() { return handType; }
    public int getStrength() { return this.handType.ordinal(); }
    public String getCardsSubstituted() {
        String cards = this.cards;
        cards = cards.replaceAll("A","E");
        cards = cards.replaceAll("K","D");
        cards = cards.replaceAll("Q","C");
        cards = cards.replaceAll("J","0");
        cards = cards.replaceAll("T","A");
        return cards;
    }
    public int getBid() { return this.bid; }

    public Hand (String inString) {
        this.handType = HandType.HighCard;
        String[] s = inString.split(" ");
        this.cards = s[0];
        this.bid = Integer.parseInt(s[1]);
        countCards();
        getHandType(this.cardCount);
    }

}
