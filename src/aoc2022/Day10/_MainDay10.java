package AOC2022.Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _MainDay10 {

    private static List<Instruction> instructions;
    private static Display display;

    private static List<Instruction> getInstructions(Scanner sc) {
        List<Instruction> instructions = new ArrayList<>();
        while (sc.hasNextLine()) {
            Instruction instruction = new Instruction(sc.nextLine());
            instructions.add(instruction);
        }
        return instructions;
    }

    private static long getResult() {
        long sum = 0;
        display = new Display();
        for (Instruction instruction : instructions) {
            switch (instruction.getType()) {
                case "addx" -> display.cmdAddX(instruction.getValue());
                case "noop" -> display.cmdNoop();
            }
            if (display.isClockEvent()) sum += display.getClockEventValue();
        }
        display.printOut();;
        return sum;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/AOC2022/Day10.txt"));
            instructions = getInstructions(sc);
            System.out.println("the result is ..." + getResult());
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
