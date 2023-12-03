package aoc2022.Day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _MainDay05 {

    private static void loadCrates(List<List<Character>> stacks,String s) {
        int noOfStacks = 0;
        // check no of existing stacks
        int createsInRow = (s.length() + 1) / 4;
        while (stacks.size() < createsInRow) {
            List<Character> stack = new ArrayList<Character>();
            stacks.add(stack);
        }
        // load crates in to the stacks
        int start = 0;
        while (s.indexOf("[", start) >= 0) {
            int pos = s.indexOf("[", start) + 1;
            int stackNo = pos / 4;
            stacks.get(stackNo).add(0, s.charAt(pos));
            start = pos;
        }
    }

    private static void addCommand(List<Command> commands, String s) {
        // get new command
        Command c = new Command();
        String[] cmd = s.split(" ");
        c.qty = Integer.parseInt(cmd[1]);
        c.from = Integer.parseInt(cmd[3]) - 1;
        c.to = Integer.parseInt(cmd[5]) - 1;
        commands.add(c);
    }

    private static void reStack(List<List<Character>> stacks, List<Command> commands) {
        for (Command cmd:commands) {
            for (int i = 0; i < cmd.qty; i++) {
                if (!stacks.get(cmd.from).isEmpty()) {
                    char crate = stacks.get(cmd.from).get(stacks.get(cmd.from).size()-1);
                    stacks.get(cmd.from).remove(stacks.get(cmd.from).size()-1);
                    stacks.get(cmd.to).add(stacks.get(cmd.to).size() - i, crate);
                }
            }
        }
    }

    private static String getResult(List<List<Character>> stacks) {
        StringBuilder outString = new StringBuilder();
        for (List<Character> stack:stacks) {
            outString.append(stack.get(stack.size()-1));
        }
        return outString.toString();
    }

    public static void read(Scanner sc, List<List<Character>> stacks, List<Command> commands) {
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (s.contains("[")) loadCrates(stacks, s);
            if (s.contains("move")) addCommand(commands, s);
        }
    }

    public static void main(String[] args) {
        List<List<Character>> stacks = new ArrayList<List<Character>>();
        List<Command> commands = new ArrayList<Command>();
        try {
            Scanner sc = new Scanner(new File("resources/aoc2022/Day05.txt"));
            read(sc, stacks, commands);
            reStack(stacks, commands);
            System.out.println("the 1st result is ... " + getResult(stacks));
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
