package aoc2022.Day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _MainDay04 {

    private static List<Assignment> read(Scanner sc) {
        List<Assignment> assignments = new ArrayList<>();
        Assignment assignment;

        while (sc.hasNextLine()) {
            assignment = new Assignment(sc.nextLine());
            assignments.add(assignment);
        }
        return assignments;
    }

    private static int getNoOfOverlaps(List<Assignment> data) {
        int noOfOverlaps = 0;
        for (Assignment a:data)
            if (a.overlap) noOfOverlaps++;
        return noOfOverlaps;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/aoc2022/Day04.txt"));
            List<Assignment> data = read(sc);
            System.out.println("the 1st result is ... " + getNoOfOverlaps(data));
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
