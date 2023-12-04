package aoc2023.day04;

import java.util.Arrays;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class Card {

    private int ID;
    private int[] numbers;
    private int[] winningNumbers;
    private int matches;
    private int points;
    private int noOfCards;


    public int getID() { return ID; }

    public int getMatches() { return matches; }

    public int getPoints() { return points; }

    public int getNoOfCards() { return noOfCards; }

    public void addCopies(int noOfCopies) { noOfCards += noOfCopies; }

    public void resetCopies() { noOfCards = 1; }

    private int[] getNumbersFromString(String inString) {
        return
            Arrays.stream(inString.replaceAll("[^0-9]+",";").split(";"))
                  .map(s -> { try {
                                  return Integer.valueOf(s);
                              } catch (NumberFormatException ignored) {
                                  return null;
                              }
                            })
                  .filter(Objects::nonNull)
                  .mapToInt(x -> x)
                  .sorted()
                  .toArray();
    }

    private void findMatches() {
        Arrays.stream(numbers)
              .forEach(n -> { if (Arrays.stream(winningNumbers).anyMatch(wn -> wn == n)) { matches++; } });
        points = (matches > 0) ? (int) Math.pow(2,matches - 1) : 0;
    }

    public Card(String inString) {
        String[] s = inString.replace("|", ":").split(":");
        if (s.length == 3) {
            ID = getNumbersFromString(s[0])[0];
            numbers = getNumbersFromString(s[1]);
            winningNumbers = getNumbersFromString(s[2]);
            findMatches();
            noOfCards = 1;
        }
    }
}
