package aoc2022.Day10;

import java.util.Scanner;

public class Instruction {
    private String type = "";
    private int value = 0;

    public Instruction(String instructionString) {
        Scanner cmdLine = new Scanner(instructionString);
        type = cmdLine.next("[a-z]+");
        if (type.equals("addx")) value = cmdLine.nextInt();
    }

    public String getType() { return type; }

    public int getValue() { return value; }

}
