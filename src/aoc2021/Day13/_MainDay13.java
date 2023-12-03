package aoc2021.Day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _MainDay13 {

    private static long readSource(Scanner scanner) {
        // reade data source
        List<Dot> dots = new ArrayList<>();
        List<Integer> foldX = new ArrayList<>();
        List<Integer> foldY = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains(",")) {
                String[] substring = line.split(",");
                Dot dot = new Dot(Integer.parseInt(substring[0]), Integer.parseInt(substring[1]));
                dots.add(dot);
            } else if (line.contains("x=")) {
                int x = Integer.parseInt(line.substring(line.indexOf("x=") + 2, line.length()));
                foldX.add(x);
            } else if (line.contains("y=")) {
                int y = Integer.parseInt(line.substring(line.indexOf("y=") + 2, line.length()));
                foldY.add(y);
            }
        }

        // set the map
        boolean[][] sourceMap = new boolean[2*foldY.get(0) + 1][2*foldX.get(0) + 1];
        for (Dot dot : dots) sourceMap[dot.getPosY()][dot.getPosX()] = true;

        boolean[][] map = sourceMap.clone();
        //fold map by X
        for (int fX : foldX) {
            boolean[][] newMap = new boolean[map.length][fX];
            for (int y=0; y<newMap.length; y++)
                for (int x=0; x<fX; x++)
                    newMap[y][x] = (map[y][x] || map[y][2*fX-x]);
            map = newMap;
            long result = 0;
            for (int y = 0; y< map.length; y++)
                for (int x = 0; x< map[0].length; x++)
                    if (map[y][x]) result++;
            result = result;
        }
        //fold map by Y
        for (int fY : foldY) {
            boolean[][] newMap = new boolean[fY][map[0].length];
            for (int y=0; y<fY; y++)
                for (int x=0; x<newMap[0].length; x++)
                    newMap[y][x] = (map[y][x] || map[2*fY-y][x]);
            map = newMap;
        }

        //count overlapping dots
        long result = 0;
        for (int y = 0; y< map.length; y++) {
            String outString = "";
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x]) {
                    outString += "#";
                    result++;
                } else {
                    outString += " ";
                }
            }
            System.out.println(outString);
        }

        return result;
    }

    private static long setBit(long value, int bit) {
        long mask = (long) Math.pow(2, bit);
        return value & mask;
    }

    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("resources/aoc2021/Day13.txt"));
            long result = readSource(scanner);

            System.out.println();
            System.out.println("the result is ..." + result);
            System.out.println();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
