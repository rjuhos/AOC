package aoc2021.Day10;

import java.util.Stack;

public class Chunks {
    Stack<Integer> stack = new Stack<>();
    private static final int value1 = 3;        // '()'
    private static final int value2 = 57;       // '[]'
    private static final int value3 = 1197;     // '{}'
    private static final int value4 = 25137;    // '<>'

    public boolean corrupted;
    public boolean incomplete;
    public int errNo;
    public long closingPoints;

    public Chunks(String str) {
        char[] chars = str.toCharArray();
        corrupted = false;
        incomplete = false;
        errNo = 0;
        closingPoints = 0;

        for (char c : chars) {
            switch (c) {
                case '(' -> stack.push(value1);
                case '[' -> stack.push(value2);
                case '{' -> stack.push(value3);
                case '<' -> stack.push(value4);
                case ')' -> errNo = checkForError(value1);
                case ']' -> errNo = checkForError(value2);
                case '}' -> errNo = checkForError(value3);
                case '>' -> errNo = checkForError(value4);
            }
            if (errNo != 0) break;
        }

        if (errNo != 0) corrupted = true;
        else if (!stack.empty()) incomplete = true;
    }

    private int checkForError(int value) {
        if (stack.isEmpty()) return -1;
        else if (stack.pop() != value) return value;
        else return 0;
    }

    public long getClosingPoint() {
        if (incomplete) {
            closingPoints = 0;
            while (!stack.empty())
                switch (stack.pop()) {
                    case value1 -> closingPoints = 5*closingPoints + 1;
                    case value2 -> closingPoints = 5*closingPoints + 2;
                    case value3 -> closingPoints = 5*closingPoints + 3;
                    case value4 -> closingPoints = 5*closingPoints + 4;
                }
            return closingPoints;
        } else return 0;
    }

}
