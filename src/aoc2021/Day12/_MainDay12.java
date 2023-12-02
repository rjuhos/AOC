package AOC2021.Day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class _MainDay12 {

    public static List<Cave> readSource(Scanner sc) {
        List<Cave> caveList = new ArrayList<>();
        List<String> caveNames = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] cn = sc.nextLine().split("-");
            if (!caveNames.contains(cn[0])) {
                caveNames.add(cn[0]);
                Cave cave = new Cave(cn[0]);
                cave.addAdjacent(cn[1]);
                caveList.add(cave);
            } else {
                caveList.get(caveNames.indexOf(cn[0])).addAdjacent(cn[1]);
            }
            if (!caveNames.contains(cn[1])) {
                caveNames.add(cn[1]);
                Cave cave = new Cave(cn[1]);
                cave.addAdjacent(cn[0]);
                caveList.add(cave);
            } else {
                caveList.get(caveNames.indexOf(cn[1])).addAdjacent(cn[0]);
            }
        }

        return caveList;
    }

    private static long findResultPart1(List<Cave> caves) {
        Stack<Cave> stackDFS = new Stack<>();
        long result = 0;

        List<String> caveNames = new ArrayList<>();
        List<Boolean> smallCaves = new ArrayList<>();
        for (Cave c : caves) {
            caveNames.add(c.getName());
            smallCaves.add(!c.isStart() && !c.isEnd() && !c.isBigCave());
        }

        // find the "start" cave
        for (Cave c : caves)
            if (c.isStart()) {
                Cave stackIn = new Cave(c.getName());
                for (String a : c.getAdjacentList())
                    stackIn.addAdjacent(a);
                // add one visit to the adjacent small caves
                //stackIn.addVisit(caveNames, smallCaves);
                stackDFS.push(stackIn);
                break;
            }

       while (!stackDFS.empty()) {
           if (!stackDFS.lastElement().hasNextAdjacent())
               stackDFS.pop();
           else {
               String caveName = stackDFS.lastElement().getNextAdjacent();
               stackDFS.lastElement().setVisited(caveName);
               Cave c = caves.get(caveNames.indexOf(caveName));
                   if (c.isEnd()) {
                       result++;
                       printStack(stackDFS);
                   } else {
                       // get clone or the cave
                       Cave stackIn = new Cave(c.getName());
                           for (String a : c.getAdjacentList()) stackIn.addAdjacent(a);
                       // load stack
                       stackDFS.push(stackIn);
                       // set visited caves
                       for (int i = 0; i < (stackDFS.size() - 1); i++)
                           if (!stackDFS.get(i).isBigCave())
                               stackDFS.lastElement().setVisited(stackDFS.get(i).getName());
                       // add one visit to the adjacent small caves
                       //stackIn.addVisit(caveNames, smallCaves);
                   }
           }
       }

        return result;
    }

    private static void printStack(Stack<Cave> stack) {
        StringBuilder s = new StringBuilder();
        if (!stack.empty()) s.append(stack.get(0).getName());
        for (int i=1; i< stack.size(); i++)
            s.append("-").append(stack.get(i).getName());
        s.append("-end");
        System.out.println(s);
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/AOC2021/Day12.txt"));
            List<Cave> caves = readSource(sc);

              System.out.format("\nThe 1st part result is .... % 12d", findResultPart1(caves));
//            System.out.format("\nThe 2nd part result is .... % 12d", findResultPart2(caves));
            System.out.format("\n");

        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
