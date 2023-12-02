package AOC2021.Day08;

import java.util.Arrays;

public class Pattern {
    public String[] input;
    public String[] output;
    public long outputValue;

    private String ZERO;
    private String ONE;
    private String TWO;
    private String THREE;
    private String FOUR;
    private String FIVE;
    private String SIX;
    private String SEVEN;
    private String EIGHT;
    private String NINE;

    public Pattern(String string) {
        input = new String[10];
        output = new String[4];
        String[] data = string.replace(" | "," - ").split(" - ");
        input = data[0].split(" ");
        output = data[1].split(" ");
        sortIOs();
        outputValue = decodeDigits();
    }

    public long getResult1() {
        long result = 0;
        for (int i=0; i<4; i++) {
            switch (output[i].length()) {
                case 2, 3, 4, 7 -> result++;
            }
        }
        return result;
    }

    private long decodeDigits() {
        long result = 0;
        // find ONE, FOUR, SEVEN and EIGHT
        for (int i=0; i<10; i++) {
            String s = input[i];
            switch (s.length()) {
                case 2 -> ONE = s;
                case 3 -> SEVEN = s;
                case 4 -> FOUR = s;
                case 7 -> EIGHT = s;
            }
        }
        // find SIX, NINE and ZERO
        for (int i=0; i<10; i++) {
            String s = input[i];
            if (s.length() == 6) {
                if (isSubDigit(FOUR,s))
                    NINE = s;
                else if (isSubDigit(SEVEN,s))
                    ZERO = s;
                else
                    SIX = s;
            }
        }
        // find TWO, THREE and FIVE
        for (int i=0; i<10; i++) {
            String s = input[i];
            if (s.length() == 5) {
                if (isSubDigit(s,SIX))
                    FIVE = s;
                else if (isSubDigit(SEVEN,s))
                    THREE = s;
                else
                    TWO = s;
            }
        }

        // get the output number
        for (int i=0; i<4; i++) {
            String s = output[i];
            result *=10;
            if (s.equals(ONE))
                result += 1;
            else if (s.equals(TWO))
                result += 2;
            else if (s.equals(THREE))
                result += 3;
            else if (s.equals(FOUR))
                result += 4;
            else if (s.equals(FIVE))
                result += 5;
            else if (s.equals(SIX))
                result += 6;
            else if (s.equals(SEVEN))
                result += 7;
            else if (s.equals(EIGHT))
                result += 8;
            else if (s.equals(NINE))
                result += 9;
            else if (s.equals(ZERO))
                result += 0;
            }

        return result;
    }

    private boolean isSubDigit(String find, String source) {
        boolean result = true;
        char[] chars = find.toCharArray();
        for (char ch : chars)
            if (source.indexOf(ch) == -1) {
                result = false;
                break;
            }

        return result;
    }

    private void sortIOs() {
        for (int i=0; i<10; i++)
            input[i] = sortChars(input[i]);
        for (int i=0; i<4; i++)
            output[i] = sortChars(output[i]);
    }

    private String sortChars(String input) {
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
