package aoc2022.Day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.IntStream;

public class _MainDay12 {

    private static char[][] map;    // character map
    private static Vertex[] vertexTable;  // table of vertexes
    private static int start = -1;
    private static int end = -1;
    private static List<Vertex> path;
    private static char cLF = 10;

    private static void readMap(Scanner sc) {
        List<char[]> mapRows = new ArrayList<>();
        while (sc.hasNextLine()) {
            char[] mapRow = sc.nextLine().toCharArray();
            mapRows.add(mapRow);
        }
        map = mapRows.toArray(new char[0][]);
        List<Vertex> tableList = new ArrayList<>();
        for (int i = 0; i< map.length * map[0].length; i++) {
            Vertex v = new Vertex();
            v.setId(i);
            v.setValue(map[i / map[0].length][i % map[0].length]);
            if (v.getValue() == 'S') { start = i; v.setValue('a'); }
            if (v.getValue() == 'E') { end = i; v.setValue('z'); }
            tableList.add(v);
        }
        vertexTable = tableList.toArray(new Vertex[0]);
    }

    private static int findShortestPath() {
        int next = -1;
        int current = start;
        vertexTable[current].setDistance(0);
        do {
            vertexTable[current].setVisited();
            int row = current / map[0].length;
            int col = current % map[0].length;
            // next RIGHT
            if (col < map[0].length - 1) {
                next = row*map[0].length + col + 1;
                setDistance(vertexTable[current], vertexTable[next]);
            }
            // next UP
            if (row > 0) {
                next = (row-1)*map[0].length + col;
                setDistance(vertexTable[current], vertexTable[next]);
            }
            // next LEFT
            if (col > 0) {
                next = row*map[0].length + col - 1;
                setDistance(vertexTable[current], vertexTable[next]);
            }
            // next DOWN
            if (row < map.length - 1) {
                next = (row+1)*map[0].length + col;
                setDistance(vertexTable[current], vertexTable[next]);
            }
            // get next not visited with the smallest distance
            current = getNextVertex();
        } while (current >= 0 && current != end);

        return (current == end && vertexTable[end].getFromVertex() >= 0 ? getNoOfSteps() : Integer.MAX_VALUE);
    }


    private static void setDistance(Vertex current, Vertex next) {
        // if not visited, set the distance and id from
        if (!next.isVisited()) {
            int distance = next.getValue() - current.getValue() + 1;
            if (distance <= 2 && (current.getDistance() + Math.abs(distance)) <= next.getDistance()) {
                next.setDistance(current.getDistance() + Math.abs(distance));
                next.setFromVertex(current.getId());
            }
        }
    }

    private static int getNextVertex() {
        // get next not visited with the smallest distance
        int nextVertex = -1;
        int distance = Integer.MAX_VALUE;
        for (Vertex v : vertexTable)
            if (!v.isVisited() && v.getDistance() < distance) {
                distance = v.getDistance();
                nextVertex = v.getId();
            }
        return nextVertex;
    }

    private static int getNoOfSteps() {
        // create list array - path from start to end
        path = new ArrayList<>();
        int vID = end;
        do {
            path.add(0, vertexTable[vID]);
            vID = vertexTable[vID].getFromVertex();
        } while (vID != start);
        path.add(0, vertexTable[vID]);
        for (int i = 0; i < path.size(); i++) {
            vID = path.get(i).getId();
            int row = vID / map[0].length;
            int col = vID % map[0].length;
        }
        System.out.format("Start %4d .... no of steps: %4d%n", start, path.size() - 1);
        return path.size() - 1;
    }

    private static int getFewestSteps() {
        int fewestSteps = Integer.MAX_VALUE;
            for (Vertex v : vertexTable) {
                if (v.getValue() == 'a') {
                    IntStream.range(0,vertexTable.length).forEach(i -> vertexTable[i].init() );
                    start = v.getId();
                    int noOfSteps = findShortestPath();
                    if ( noOfSteps < fewestSteps ) fewestSteps = noOfSteps;
                }
            }
        return fewestSteps;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/aoc2022/Day12.txt"));
            readMap(sc);
            System.out.println();
            System.out.println("the 1st result is ... " + findShortestPath());
            System.out.println();
            System.out.println("the 2nd result is ... " + getFewestSteps());
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
