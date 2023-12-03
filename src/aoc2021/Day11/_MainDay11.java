package aoc2021.Day11;

import javax.management.NotCompliantMBeanException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class _MainDay11 {

    public static Octopus[][] readSource(Scanner sc) {
       List<ArrayList<Octopus>> sourceList = new ArrayList<>();
       byte[] bytes;
       while (sc.hasNextLine()) {
           ArrayList<Octopus> row = new ArrayList<>();
           byte[] chars = sc.nextLine().getBytes();
           for (byte ch : chars) {
               Octopus oc = new Octopus((byte) (ch - '0'));
               row.add(oc);
           }
           sourceList.add(row);
       }

       Octopus[][] grid = sourceList.stream().map(o -> o.toArray(new Octopus[0])).toArray(Octopus[][]::new);
       return grid;
    }


    public static long findResultPart1(Octopus[][] gridIn) {
        // get copy of input grid and its maximal indexes
        Octopus[][] grid = gridIn.clone();
        int rowMax = grid.length - 1;
        int colMax = grid[0].length - 1;
        // set no od days
        int days = 100;
        int day = 0;
        //flash counts
        long flashCount = 0;
        long lastFlashCount = 0;

        printGrid(grid, 0, flashCount);

        // do loop for each day
        // for (int day=1; day<=days; day++) {
        do {

            // get next day index and store last flash count
            day++;
            lastFlashCount = flashCount;

            // increase energy of each item in the grid
            boolean newFlash;
            for (int iRow = 0; iRow <= rowMax; iRow++)
                for (int iCol = 0; iCol <= colMax; iCol++)
                    newFlash = grid[iRow][iCol].increaseEnergy();

            // check the flashes and increase energy level of adjacents
            do {
                newFlash = false;
                for (int iRow = 0; iRow <= rowMax; iRow++) {
                    for (int iCol = 0; iCol <= colMax; iCol++) {
                        newFlash = grid[iRow][iCol].newFlash;
                        if (newFlash) {
                            increaseAdjacent(grid, iRow, iCol);
                            flashCount++;
                            grid[iRow][iCol].clearNewFlash();
                            break;
                        }
                    }
                    if (newFlash) break;
                }
            } while (newFlash);

            // if the newFlash status in each item
            for (int iRow = 0; iRow <= rowMax; iRow++)
                for (int iCol = 0; iCol <= colMax; iCol++)
                    grid[iRow][iCol].clearFlash();

            printGrid(grid, day, flashCount);

        } while ((flashCount - lastFlashCount) < 100);

        return flashCount;
    }

    public static long findResultPart2(Octopus[][] gridIn) {
        return 0;
    }

    private static void increaseAdjacent(Octopus[][] grid, int iRow, int iCol) {
        int rowMax = grid.length - 1;
        int colMax = grid[0].length - 1;

        if (iRow > 0) { grid[iRow-1][iCol].increaseEnergy(); }                                // adjacent up
        if ((iRow > 0) && (iCol < colMax)) { grid[iRow-1][iCol+1].increaseEnergy(); }         // adjacent up-right
        if (iCol < colMax) { grid[iRow][iCol+1].increaseEnergy(); }                           // adjacent right
        if ((iCol < colMax) && (iRow < rowMax)) { grid[iRow+1][iCol+1].increaseEnergy(); }    // adjacent right-down
        if (iRow < rowMax) { grid[iRow+1][iCol].increaseEnergy(); }                           // adjacent down
        if ((iRow < rowMax) && (iCol>0)) { grid[iRow+1][iCol-1].increaseEnergy(); }           // adjacent down-left
        if (iCol>0) { grid[iRow][iCol-1].increaseEnergy(); }                                  // adjacent left
        if ((iCol>0) && (iRow > 0)) { grid[iRow-1][iCol-1].increaseEnergy(); }                // adjacent left-up
    }

    private static void printGrid(Octopus[][] grid, int day, long counts) {
        int rowMax = grid.length - 1;
        int colMax = grid[0].length - 1;
        System.out.format("\nDay %03d", day);
        for (int iRow = 0; iRow <= rowMax; iRow++) {
            StringBuilder s = new StringBuilder();
            s.append("\n");
            for (int iCol = 0; iCol <= colMax; iCol++) {
                s.append(grid[iRow][iCol].energy);
                grid[iRow][iCol].clearFlash();
            }
            System.out.print(s);
        }
        System.out.format("\nFlash count ... % 9d\n", counts);
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/aoc2021/Day11.txt"));
            Octopus[][] grid = readSource(sc);

            System.out.format("\nThe 1st part result is .... % 12d", findResultPart1(grid));
            System.out.format("\nThe 2nd part result is .... % 12d", findResultPart2(grid));
            System.out.format("\n");

        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }

}
