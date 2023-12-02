package AOC2021.Day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class _MainDay07 {

    public static List<Integer> readSource(Scanner sc) {
        List<Integer> data = new ArrayList<>();

        if (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] str = line.split(",");
            for (String s : str)
                data.add(Integer.parseInt(s));
        }

        return data;
    }

    private static long getResult(List<Integer> data) {
        List<Level> levels = new ArrayList<>();
        Level lvlLast = new Level(0);

        // create list for all levels from min to max
        data.sort(Comparator.naturalOrder());
        int lvlMin = data.get(0);
        int lvlMax = data.get(data.size()-1);
        for (int l = lvlMin; l <=lvlMax; l++) {
            if (levels.isEmpty()) {
                Level lvl = new Level(l);
                levels.add(lvl);
                lvlLast = lvl;
            }
            else if (l != lvlLast.levelId) {
                Level lvl = new Level(l);
                levels.add(lvl);
                lvlLast = lvl;
            }
        }

        // find the fuel cost for each level and find the level with minimal cost
        long fuelCostMin = (long)(Math.pow(2,63) - 1);
        int lvlCostMin = 0;
        for (Level lvl : levels) {
            for (int d : data)
                lvl.fuelCost += getCost((int)Math.abs(d - lvl.levelId));
            if (lvl.fuelCost < fuelCostMin) {
                lvlCostMin = lvl.levelId;
                fuelCostMin = lvl.fuelCost;
            }
        }

        return fuelCostMin;

    }

    public static long getCost(int steps) {
        long cost = 0;
        for (int i=1; i<=steps; i++)
            cost += i;
        return cost;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/AOC2021/Day07.txt"));
            List<Integer> pos = readSource(sc);

            System.out.println("The result is ... " + getResult(pos));
            System.out.println();

        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }

    }

}