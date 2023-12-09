package aoc2023.day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class _MainDay08 {

    private static List<Node> listOfNodes;
    private static char[] instructions;

    private static void getDirections(Scanner sc) {
        listOfNodes = new ArrayList<>();
        instructions = sc.nextLine().toCharArray();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (!s.isBlank()) {
                Node node = new Node(s);
                listOfNodes.add(node);
            }
        }
    }

    private static int getNoOfSteps1() {
        int step = 0;
        String nextNodeName = "AAA";
        Node node = getNode(nextNodeName);
        while (node != null) {

            switch (instructions[step % instructions.length]) {
                case 'L':
                    nextNodeName = node.getDirL();
                    break;
                case 'R':
                    nextNodeName = node.getDirR();
                    break;
                default:
                    // nop
            }
            step++;
            if (Objects.equals(nextNodeName, "ZZZ")) { break; }
            node = getNode(nextNodeName);
        }
        return step;
    }

    private static long findGCD(long a, long b) {
        if (b == 0) { return a; }
        return findGCD(b, a % b);
    }

    // Returns LCM of array elements
    private static long findLCM(int[] arr) {
        // Initialize result
        long ans = arr[0];

        // ans contains LCM of arr[0], ..arr[i]
        // after i'th iteration,
        for (int i = 0; i < arr.length; i++) {
            ans = ((arr[i] * ans) / (findGCD(arr[i], ans)));
        }
        return ans;
    }

    private static long getNoOfSteps2() {
        int step = 0;
        boolean noResult = false;
        boolean isTheLast;
        List<String> nextNodeName = new ArrayList<>();
        listOfNodes.stream().forEach(n -> {
            if (n.getName().endsWith("A")) {
                String s = n.getName(); nextNodeName.add(s);
            }
        });
        int[] steps = new int[nextNodeName.size()];
        System.out.println(nextNodeName);
        do {
            for (int i = 0; i < nextNodeName.size(); i++) {
                String nodeName = nextNodeName.get(i);
                Node node = getNode(nodeName);
                if (node != null) {
                    if (!nodeName.endsWith("Z")) {
                        switch (instructions[step % instructions.length]) {
                            case 'L':
                                nodeName = node.getDirL();
                                break;
                            case 'R':
                                nodeName = node.getDirR();
                                break;
                            default:
                                // nop
                        }
                    }
                    nextNodeName.set(nextNodeName.indexOf(nextNodeName.get(i)),nodeName);
                } else {
                    //noResult = true;
                    break;
                }
                if (nextNodeName.get(i).endsWith("Z") & steps[i] == 0) { steps[i] = step + 1; }
            };
            step++;
            isTheLast = true;
            System.out.print(nextNodeName);
            System.out.print(" .. ");
            System.out.println(step);
            for (String nnn : nextNodeName) { isTheLast &= nnn.endsWith("Z");}
        } while (!noResult & !isTheLast);

        return findLCM(steps);
    }

    private static Node getNode(String nodeName) {
        Node node = null;
        for (Node n : listOfNodes) {
            if (Objects.equals(n.getName(), nodeName)) {
                node = n;
                break;
            }
        }
        return node;
    }

    public static void main (String[]args){
        try {
            Scanner sc = new Scanner(new File("resources/AOC2023/Day08.txt"));
            getDirections(sc);
            System.out.println();
    //        System.out.println("the 1st result is ... " + getNoOfSteps1());
            System.out.println();
            System.out.println("the 2st result is ... " + getNoOfSteps2());
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }

}
