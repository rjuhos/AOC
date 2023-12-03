package aoc2022.Day06;

import java.util.Stack;
import java.util.stream.IntStream;

public class Sequence {
    private Stack<Character> stack;
    private int stackMaxSize;
    public String sequence = "";

    public Sequence(int size){
        stack = new Stack<Character>();
        stackMaxSize = size;
    }

    public void addChar(char c) {
        stack.push(c);
        while (stack.size() > stackMaxSize)
            stack.remove(0);
        sequence = getSeq();
    }

    public boolean seqOK() {
        if (stack.size() < stackMaxSize)
            return false;
        else {
            for (int i = 0; i < stack.size(); i++) {
                char c = stack.get(i);
                if (!IntStream.range(i+1, stack.size()).allMatch(j -> stack.get(j) != c))
                    return false;
            }
            return true;
        }
    }

    private String getSeq() {
        StringBuilder s = new StringBuilder();
        for (char c:stack) s.append(c);
        return s.toString();
    }

}
