package aoc2022.Day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class _MainDay02 {

    private static int lost     = 0;
    private static int draw     = 3;
    private static int won      = 6;
    private static int stone    = 1;
    private static int paper    = 2;
    private static int scissors = 3;

    private static int getScore1(String strategy) {
        int score = 0;

        // Parse strategy
        String play[] = Pattern.compile(" ").split(strategy,2);
        switch (play[1]) {
            case "X":   // stone
                score = 1;
                switch (play[0]) {
                    case "A":   // stone
                        score += draw;
                        break;
                    case "B":   // paper
                        score += lost;
                        break;
                    case "C":   // scissors
                        score += won;
                }
                break;
            case "Y":   // paper
                score = 2;
                switch (play[0]) {
                    case "A":   // stone
                        score += won;
                        break;
                    case "B":   // paper
                        score += draw;
                        break;
                    case "C":   // scissors
                        score += lost;
                }
                break;
            case "Z":   // scissors
                score = 3;
                switch (play[0]) {
                    case "A":   // stone
                        score += lost;
                        break;
                    case "B":   // paper
                        score += won;
                        break;
                    case "C":   // scissors
                        score += draw;
                }
        }
        return score;
    }

    private static int getScore2(String strategy) {
        int score = 0;

        // Parse strategy
        String play[] = Pattern.compile(" ").split(strategy,2);
        switch (play[1]) {
            case "X":   // lose
                score = lost;
                switch (play[0]) {
                    case "A":   // stone
                        score += scissors;
                        break;
                    case "B":   // paper
                        score += stone;
                        break;
                    case "C":   // scissors
                        score += paper;
                }
                break;
            case "Y":   // draw
                score = draw;
                switch (play[0]) {
                    case "A":   // stone
                        score += stone;
                        break;
                    case "B":   // paper
                        score += paper;
                        break;
                    case "C":   // scissors
                        score += scissors;
                }
                break;
            case "Z":   // win
                score = won;
                switch (play[0]) {
                    case "A":   // stone
                        score += paper;
                        break;
                    case "B":   // paper
                        score += scissors;
                        break;
                    case "C":   // scissors
                        score += stone;
                }
        }
        return score;
    }

    private static List<Integer> read(Scanner sc) {
        List<Integer> data = new ArrayList<>();

        while (sc.hasNextLine()) {
                data.add( getScore2( sc.nextLine() ) );
        }
        return data;
    }

    private static long totalResult(List<Integer> data) {
        int total = 0;
        for (int i = 0; i < data.size(); i++) {
            total += data.get(i);
        }
        return total;
    }
    
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/aoc2022/Day02.txt"));
            List<Integer> data = read(sc);
            System.out.println("the total score is " + totalResult(data));
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
