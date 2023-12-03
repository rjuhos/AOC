package aoc2021.Day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _MainDay05 {

    private static List<Line> readLines(Scanner scanner) {
        List<Line> lines = new ArrayList<>();
        Line line;

        while (scanner.hasNextLine()) {
            line = new Line(scanner.nextLine());
            lines.add(line);
        }

        return lines;
    }

    private static long findResult(List<Line> lines) {
        int size = 1000;
        int result = 0;

        // create empty map
        int[][] map = new int[size][size];

        // fill the map
        for (Line l : lines) {
            if (l.valid) {
                if (l.vertical)
                    for (int y = l.start.y; y <= l.stop.y; y++) {
                        Point p = l.PointFromY(y);
                        map[p.x][p.y]++;
                    }
                else //if (l.horizontal)
                    for (int x = l.start.x; x <= l.stop.x; x++) {
                        Point p = l.PointFromX(x);
                        map[p.x][p.y]++;
                    }
            }
        }

        // count crossings
        for (int x=0; x<size; x++)
            for (int y=0; y<size; y++)
                if (map[x][y] >= 2) result++;

        return result;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/aoc2021/Day05.txt"));
            List<Line> Lines = readLines(sc);

            System.out.println("No of lines ...... " + Lines.size());
            System.out.println("No of crossings... " + findResult(Lines));
            System.out.println();

        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }

    }

}




