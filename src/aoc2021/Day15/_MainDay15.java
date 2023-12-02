package AOC2021.Day15;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _MainDay15 {

    private static Byte[][] getDistances(@NotNull Scanner scanner) {
        List<ArrayList<Byte>> datList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            char[] line = scanner.nextLine().toCharArray();
            ArrayList<Byte> dataRow = new ArrayList<>();
            for (char c : line)
                dataRow.add((byte)(c - '0'));
            datList.add(dataRow);
        }

        Byte[][] distance = datList.stream().map(b -> b.toArray(new Byte[0])).toArray(Byte[][]::new);
        return distance;
    }

    private static Byte[][] getFullMapDistance(Byte[][] distance) {
        Byte[][] fullMapDistance = new Byte[5*distance.length][5*distance[0].length];
        // extend columns 5 times with increment of 1
        for (int row = 0; row< distance.length; row++)
            for (int col = 0; col< distance[0].length; col++) {
                fullMapDistance[row][col] = wrapByte(distance[row][col]);
                fullMapDistance[row][col+distance[0].length] = wrapByte((byte)(distance[row][col]+1));
                fullMapDistance[row][col+2*distance[0].length] = wrapByte((byte)(distance[row][col]+2));
                fullMapDistance[row][col+3*distance[0].length] = wrapByte((byte)(distance[row][col]+3));
                fullMapDistance[row][col+4*distance[0].length] = wrapByte((byte)(distance[row][col]+4));
            }
        // extend rows 5 times with increment of 1
        for (int row = 0; row< distance.length; row++)
            for (int col = 0; col< fullMapDistance[0].length; col++) {
                fullMapDistance[row+distance.length][col] = wrapByte((byte)(fullMapDistance[row][col]+1));
                fullMapDistance[row+2*distance.length][col] = wrapByte((byte)(fullMapDistance[row][col]+2));
                fullMapDistance[row+3*distance.length][col] = wrapByte((byte)(fullMapDistance[row][col]+3));
                fullMapDistance[row+4*distance.length][col] = wrapByte((byte)(fullMapDistance[row][col]+4));
            }

        return fullMapDistance;
    }

    private static byte wrapByte(Byte b) {
        if (b>9)
            return (byte)(b-9);
        else
            return b;
    }


    private static long findShortestPath(Byte[][] distance) {
        Vertex[][] vertex = new Vertex[distance.length][distance.length];
        // init all vertexes to default values
        for (int row = 0; row< vertex.length; row++)
            for (int col = 0; col< vertex[0].length; col++) {
                vertex[row][col] = new Vertex();
            }
        vertex[0][0].setDistance((long)0);

        long scan = 0;
        int row = 0;
        int col = 0;
        do {
            int iRowCol = findShortestDistance(vertex);
            row = iRowCol / 1000;
            col = iRowCol % 1000;
            vertex[row][col].setVisited();
            long lastDistance = vertex[row][col].getDistance();
            if (col > 0) vertex[row][col-1].relax(lastDistance, distance[row][col-1]);
            if (col < (vertex[0].length - 1)) vertex[row][col+1].relax(lastDistance, distance[row][col+1]);
            if (row > 0) vertex[row-1][col].relax(lastDistance, distance[row-1][col]);
            if (row < (vertex.length - 1)) vertex[row+1][col].relax(lastDistance, distance[row+1][col]);

            if (scan==445)
                scan = scan;
            scan++;
            System.out.format("\rScan No =% 7d, row =% 4d, col =% 4d, dist =% 5d", scan, row, col, lastDistance);

        } while ((row<(vertex.length-1)) || (col<(vertex[0].length-1)));


        return vertex[vertex.length-1][vertex[0].length-1].getDistance();
    }

    private static int findShortestDistance(Vertex[][] vertex) {
        // the shortest non visited distance
        long lastDistance = Long.MAX_VALUE;
        // indexes of the vertex with the shortest distance
        int iRow = 0;
        int iCol = 0;
        for (int row = 0; row< vertex.length; row++)
            for (int col = 0; col< vertex[0].length; col++) {
                if (!(vertex[row][col].isVisited()) && (vertex[row][col].getDistance() <= lastDistance)) {
                    lastDistance = vertex[row][col].getDistance();
                    iRow = row;
                    iCol = col;
                }
            }

        return (1000*iRow + iCol);
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("resources/AOC2021/Day15.txt"));
            Byte[][] distance = getDistances(scanner);
            Byte[][] fullMapDistance = getFullMapDistance(distance);

            long result = findShortestPath(fullMapDistance);

            System.out.println();
            System.out.println();
            System.out.println("The result is ... " + result);
            System.out.println();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
