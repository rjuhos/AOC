package aoc2022.Day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _MainDay09 {

    private static List<MoveCmd> commands;
    private static Map map;
    private static Rope rope;

    private static List<MoveCmd> getCommands(Scanner sc) {
        List<MoveCmd> commands = new ArrayList<>();
        while (sc.hasNextLine()) {
            Scanner line = new Scanner(sc.nextLine());
            MoveCmd cmd = new MoveCmd(line.next().charAt(0), line.nextInt());
            commands.add(cmd);
        }
        return commands;
    }

    private static void moveRope(List<MoveCmd> commands) {
        for (MoveCmd cmd : commands) {
            for (int i = 0; i < cmd.getDist(); i++) {
                switch (cmd.getDir()) {
                    case 'R' -> rope.moveHeadRight();
                    case 'L' -> rope.moveHeadLeft();
                    case 'U' -> rope.moveHeadUp();
                    case 'D' -> rope.moveHeadDown();
                }
                if (rope.tailMoved) map.setVisited(rope.getTailX(), rope.getTailY());
            }
            boolean stop = true;
        }
    }

    private static int getResult() {
        int visited = 0;
        System.out.println();
        for (int y = map.getSizeY() - 1; y >= 0; y--) {
            StringBuilder sbMapRow = new StringBuilder();
            for (int x = 0; x < map.getSizeX() - 1; x++) {
                if (map.isVisited(x, y)) visited++;
                sbMapRow.append(map.read(x, y));
            }
            System.out.println(sbMapRow);
        }
        System.out.println();
        return visited;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/aoc2022/Day09.txt"));
            commands = getCommands(sc);
            map = new Map(commands);
            rope = new Rope(10, map.getStartX(), map.getStartY());
            moveRope(commands);
            System.out.println("the result is ..." + getResult());
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
