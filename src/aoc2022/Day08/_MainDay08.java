package aoc2022.Day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class _MainDay08 {

    private static char[][] createForest(Scanner sc) {
        List<char[]> forest = new ArrayList<>();
        while (sc.hasNextLine()) {
            char[] row = sc.nextLine().toCharArray();
            forest.add(row);
        }
        return forest.toArray(new char[0][]);
    }

    private static boolean visibleFromLeft(char[][] forest, int row, int col) {
        return IntStream.range(0, col).allMatch(c -> forest[row][col] > forest[row][c]);
    }

    private static boolean visibleFromRight(char[][] forest, int row, int col) {
        int colMax = forest[0].length - 1;
        return IntStream.rangeClosed(col + 1, colMax).allMatch(c -> forest[row][col] > forest[row][c]);
    }

    private static boolean visibleFromTop(char[][] forest, int row, int col) {
        return IntStream.range(0, row).allMatch(r -> forest[row][col] > forest[r][col] );
    }

    private static boolean visibleFromBottom(char[][] forest, int row, int col) {
        int rowMax = forest.length - 1;
        return IntStream.rangeClosed(row + 1, rowMax).allMatch(r -> forest[row][col] > forest[r][col]);
    }

    private static int visibility(char [][] forest, int row, int col ) {
        boolean visibilityL = visibleFromLeft(forest, row, col);
        boolean visibilityR = visibleFromRight(forest, row, col);
        boolean visibilityT = visibleFromTop(forest, row, col);
        boolean visibilityB = visibleFromBottom(forest, row, col);
        return (visibilityL || visibilityR || visibilityT || visibilityB ? 1 : 0);
    }

    private static int viewToLeft(char[][] forest, int row, int col) {
        for (int r = row - 1; r > 0; r--)
            if (forest[r][col] >= forest[row][col]) return row - r;
        return row;
    }

    private static int viewToRight(char[][] forest, int row, int col) {
        int rowMax = forest.length - 1;
        for (int r = row + 1; r < rowMax; r++)
            if (forest[r][col] >= forest[row][col]) return r - row;
        return rowMax - row;
    }

    private static int viewToTop(char[][] forest, int row, int col) {
        for (int c = col - 1; c > 0; c--)
            if (forest[row][c] >= forest[row][col]) return col - c;
        return col;
    }

    private static int viewToBottom(char[][] forest, int row, int col) {
        int colMax = forest[0].length - 1;
        for (int c = col + 1; c < colMax; c++)
            if (forest[row][c] >= forest[row][col]) return c - col;
        return colMax - col;
    }
    private static int view(char[][] forest, int row, int col) {
        int viewL = viewToLeft(forest, row, col);
        int viewR = viewToRight(forest, row, col);
        int viewT = viewToTop(forest, row, col);
        int viewB = viewToBottom(forest, row, col);
        return viewL * viewR * viewT * viewB;
    }

    private static int getResult1(char[][] forest) {
        int width = forest[0].length;
        int depth = forest.length;
        int sum = 2*(width - 1) + 2*(depth - 1);
        for (int row = 1; row < width - 1; row++)
            for (int col = 1; col < depth - 1; col++)
                sum += visibility(forest, row, col);
        return sum;
    }

    private static int getResult2(char[][] forest) {
        int width = forest[0].length;
        int depth = forest.length;
        int bestView = 0;
        for (int row = 1; row < width - 1; row++)
            for (int col = 1; col < depth - 1; col++) {
                int view = view(forest, row, col);
                if (view > bestView) bestView = view;
            }
        return bestView;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/aoc2022/Day08.txt"));
            char[][] forest = createForest(sc);
            System.out.println("the 1st result is ..." + getResult1(forest));
            System.out.println("the 2st result is ..." + getResult2(forest));
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
