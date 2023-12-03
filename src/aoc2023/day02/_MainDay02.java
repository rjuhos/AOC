package aoc2023.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class _MainDay02 {

    private static List<String> inputData;

    private static List<String> getInputData(Scanner sc) {
        List<String> data = new ArrayList<>();
        while (sc.hasNextLine()) {
            data.add(sc.nextLine());
        }
        return data;
    }

    private static int getResult1() {
        int result = 0;
        for (String s : inputData) {
            Game game = getGameData(s);
            if ( game.QtyRed <= 12 && game.QtyGreen <= 13 && game.QtyBlue <= 14) {
                result += game.ID;
            }
        }
        return result;
    }

    private static long getResult2() {
        long result = 0;
        for (String s : inputData) {
            Game game = getGameData(s);
            result += (long) game.QtyRed * game.QtyGreen * game.QtyBlue;
        }
        return result;
    }

    private static Game getGameData(String inString) {
        Game game = new Game();

        String[] gameData = inString.split(":");
        if (gameData.length == 2) {
            String[] gameID = gameData[0].split(" ");
            game.ID = parseInt(gameID[1]);
            String[] packs = gameData[1].split(";");
            for (String p : packs) {
                String[] cubes = p.split(",");
                for (String c : cubes) {
                    String[] cubeData = c.trim().split(" ");
                    String cubeColor = cubeData[1];
                    int cubeQty = parseInt(cubeData[0]);
                    switch (cubeColor) {
                        case "red":
                            if (cubeQty > game.QtyRed) { game.QtyRed = cubeQty; }
                            break;
                        case "green":
                            if (cubeQty > game.QtyGreen) { game.QtyGreen = cubeQty; }
                            break;
                        case "blue":
                            if (cubeQty > game.QtyBlue) { game.QtyBlue = cubeQty; }
                           break;
                        default:
                            // nop
                    }
                }
            }
        }
        return game;
    }
    public static void main (String[]args){
        try {
            Scanner sc = new Scanner(new File("resources/AOC2023/Day02.txt"));
            inputData = getInputData(sc);
            System.out.println();
            System.out.println("the 1st result is ... " + getResult1());
            System.out.println();
            System.out.println("the 2nd result is ... " + getResult2());
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }

}
