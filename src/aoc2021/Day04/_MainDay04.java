package aoc2021.Day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _MainDay04 {

    private static List<Integer> readDraws(Scanner sc) {
        List<Integer> data = new ArrayList<>();

        if (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] stringArray = line.split(",");
            for (String s : stringArray)
                data.add(Integer.parseInt(s));
       }
        if (sc.hasNextLine())
            sc.nextLine();
        return data;

    }

    private static List<Board> readBoards(Scanner sc) {
        List<Board> data = new ArrayList<>();
        Board board = new Board();

        int boardID = 1;
        board.clearBoard();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.length() > 0) {
                board.addRow(line);
            } else {
                board.setId(boardID);
                data.add(board);
                board = new Board();
                board.clearBoard();
                boardID++  ;
            }
        }

        return data;
    }

    private static long findResult(List<Integer> draws, List<Board> boards) {
        long result = -1;

        for (int d : draws) {
            for (Board b : boards) {
                b.findNumber(d);
                if (b.Completed()) {
                    result = b.getResult() * d;
                    b.clearBoard();
                    //break;
                }
            }
            //if (result > -1) break;
        }

        return result;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/aoc2021/Day04.txt"));
            List<Integer> Draws = readDraws(sc);
            List<Board> Boards = readBoards(sc);

            System.out.println("No of Draws ..... " + Draws.size());
            System.out.println("No of Boards .... " + Boards.size());

            System.out.println("The result is ... " + findResult(Draws,Boards));
            System.out.println();


        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }


}
