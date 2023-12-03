package aoc2021.Day14;

import java.util.ArrayList;
import java.util.List;

public class Rule {

    List<String> pairs;
    List<Character> inserts;
    private String newPair1;
    private String newPair2;
    private char newChar;

    public Rule() {
        pairs = new ArrayList<>();
        inserts = new ArrayList<>();
    }

    public void addRule(String inString) {
        String[] s = inString.split(" -> ");
        String pair = s[0];
        char insert = s[1].charAt(0);
        pairs.add(pair);
        inserts.add(insert);
        newPair1 = "";
        newPair2 = "";
        newChar = Character.MIN_VALUE;
    }

    public void growPair(String pair) {
        String string = new StringBuilder(pair).insert(1, inserts.get(pairs.indexOf(pair))).toString();
        String[] sArray = new String[2];
        newPair1 = string.substring(0,2);
        newPair2 = string.substring(1,3);
        newChar = string.charAt(1);
    }

    public String getNewPair1() {
        return newPair1;
    }

    public String getNewPair2() {
        return newPair2;
    }

    public char getNewChar() {
        return newChar;
    }

    public List<String> getPolymerData() {
        return pairs;
    }

}
