package aoc2021.Day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _MainDay09 {

    public static List<ArrayList<Point>> readSource(Scanner sc) {
        List<ArrayList<Point>> map = new ArrayList<>();
        while (sc.hasNextLine()) {
            ArrayList<Point> points = new ArrayList<>();
            byte[] bytes = sc.nextLine().getBytes();
            for (byte b : bytes) {
                Point p = new Point((byte)(b-0x30));
                points.add(p);
            }
            map.add(points);
        }
        Point[][] mapPoints =  map.stream().map(p -> p.toArray(new Point[0])).toArray(Point[][]::new);
        return map;
    }

    public static int findLowPoints(List<ArrayList<Point>> map) {
        int basinId = -1;
        // find the low points in the map
        for (int height=0; height<=9; height++)
            for (ArrayList<Point> mapRow : map)
                for (Point p : mapRow)
                    if (p.height == height) {
                        int iRow = map.indexOf(mapRow);
                        int iCol = mapRow.indexOf(p);
                        boolean isLowest = true;
                        // check direction left
                        int col = iCol;
                        while (isLowest && (col > 0)) {
                            Point pNext = map.get(iRow).get(col - 1);
                            if (pNext.height == height) col--;
                            else if (pNext.height < height) isLowest = false;
                            else break; // while loop
                        }
                        // check direction right
                        col = iCol;
                        while (isLowest && (col < (mapRow.size() - 1))) {
                            Point pNext = map.get(iRow).get(col + 1);
                            if (pNext.height == height) col++;
                            else if (pNext.height < height) isLowest = false;
                            else break; // while loop
                        }
                        // check direction up
                        int row = iRow;
                        while (isLowest && (row > 0)) {
                            Point pNext = map.get(row - 1).get(iCol);
                            if (pNext.height == height) row--;
                            else if (pNext.height < height) isLowest = false;
                            else break; // while loop
                        }
                        // check direction down
                        row = iRow;
                        while (isLowest && (row < (map.size() - 1))) {
                            Point pNext = map.get(row + 1).get(iCol);
                            if (pNext.height == height) row++;
                            else if (pNext.height < height) isLowest = false;
                            else break; // while loop
                        }
                        if (isLowest) {
                            p.isLowest = true;
                            p.isBasin = true;
                            basinId++;
                            p.basinId = basinId;
                        }
                    }
        return (basinId + 1);
    }

    public static long findBasins(List<ArrayList<Point>> map, int noOfLowPoints) {
        // find the low points that flow downward
        for (int height=0; height<8; height++)
            for (ArrayList<Point> mapRow : map)
                for (Point p : mapRow)
                    if (p.height == height) {
                        int iRow = map.indexOf(mapRow);
                        int iCol = mapRow.indexOf(p);
                        boolean isBasin = p.isBasin;
                        // check direction left
                        int col = iCol;
                        int h = height;
                        while (isBasin && (col > 0))
                            if ((map.get(iRow).get(col - 1).height >= h ) && (map.get(iRow).get(col - 1).height != 9)) {
                                h = map.get(iRow).get(col - 1).height;
                                map.get(iRow).get(col - 1).setBasin(p.basinId);
                                col--;
                            } else break;
                        // check direction right
                        col = iCol;
                        h = height;
                        while (isBasin && (col < (mapRow.size() - 1)))
                            if ((map.get(iRow).get(col + 1).height >= h ) && (map.get(iRow).get(col + 1).height != 9)) {
                                h = map.get(iRow).get(col + 1).height;
                                map.get(iRow).get(col + 1).setBasin(p.basinId);
                                col++;
                            } else break;
                        // check direction up
                        int row = iRow;
                        h = height;
                        while (isBasin && (row > 0))
                            if ((map.get(row - 1).get(iCol).height >= h ) && (map.get(row - 1).get(iCol).height != 9)) {
                                h = map.get(row - 1).get(iCol).height;
                                map.get(row - 1).get(iCol).setBasin(p.basinId);
                                row--;
                            } else break;
                        // check direction down
                        row = iRow;
                        h = height;
                        while (isBasin && (row < (map.size() - 1)))
                            if ((map.get(row + 1).get(iCol).height >= h ) && (map.get(row + 1).get(iCol).height != 9)) {
                                h = map.get(row + 1).get(iCol).height;
                                map.get(row + 1).get(iCol).setBasin(p.basinId);
                                row++;
                            } else break;
                    }

        // print map & count the size of basins
        long[] basinSize = new long[noOfLowPoints];
        System.out.println();
        for (ArrayList<Point> mapRow : map) {
            StringBuilder str = new StringBuilder();
            for (Point p : mapRow)
                if (p.isLowest) {
                    //String subStr = Integer.toString(p.height);
                    //str.append(subStr.substring(subStr.length() - 1));
                    str.append("■");
                    basinSize[p.basinId]++;
                }
                else if (p.isBasin) {
                    basinSize[p.basinId]++;
                    String subStr = Integer.toString(p.basinId);
                    str.append(subStr.substring(subStr.length() - 1));
                    //str.append(" ");
                } else str.append(" ");
            str.append("   ").append(map.indexOf(mapRow));
            System.out.println(str);
        }
        System.out.println();

        Arrays.sort(basinSize);
        return (basinSize[noOfLowPoints-3] * basinSize[noOfLowPoints-2] * basinSize[noOfLowPoints-1]);
    }

    private static long findResult(List<ArrayList<Point>> map) {
        long result = 0;

        for (ArrayList<Point> mapRow : map)
            for (Point p : mapRow)
                if (p.isLowest)
                    result += p.height + 1;
                    //result++;

        return result;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/aoc2021/Day09.txt"));
            List<ArrayList<Point>> map = readSource(sc);
            int noOfLowPoints = findLowPoints(map);
            System.out.println("The first result is ... " + findResult(map));
            System.out.println("The second result is .. " + findBasins(map, noOfLowPoints));
            System.out.println();

        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }


    }
}
