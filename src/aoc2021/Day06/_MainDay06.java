package aoc2021.Day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _MainDay06 {

    private static List<Laternfish> readSource(Scanner scanner) {
        List<Laternfish> lfs = new ArrayList<>();
        Laternfish lf;
        byte defaultCycle = 6;

        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(",");
            for (String d : data) {
                lf = new Laternfish((byte)Integer.parseInt(d),defaultCycle);
                lfs.add(lf);
            }
        }

        return lfs;
    }

    private static long findResult(List<Laternfish> lfsDay0) {
        int days = 256;
        byte defaultCycle = 6;
        byte initCycle = 8;
        long result = 0;

        DayGrowth[] dayGrowths = new DayGrowth[days+1];
        for (int i= 0; i<=days; i++) {
            dayGrowths[i] = new DayGrowth(initCycle, defaultCycle);
        }
        dayGrowths[0] = new DayGrowth(initCycle,defaultCycle);
        dayGrowths[0].quantity = lfsDay0.size();

        for (int day=0; day<days; day++) {
            List<Laternfish> lfsNewBornDay0 = new ArrayList<>();

            for (Laternfish lf : lfsDay0) {
                if (lf.NewBorn()) {
                    Laternfish nbDay0 = new Laternfish(initCycle, defaultCycle);
                    lfsNewBornDay0.add(nbDay0);
                }
            }
            dayGrowths[day+1] = new DayGrowth(initCycle,defaultCycle);
            dayGrowths[day+1].quantity = lfsNewBornDay0.size();

            for (int d=1; d<= day; d++) {
                if ((dayGrowths[d].quantity > 0) && dayGrowths[d].NewBorn())
                    dayGrowths[day+1].quantity += dayGrowths[d].quantity;
            }


            result = 0;
            for (int i = 0; i<=day; i++)
                result += dayGrowths[i].quantity;
            System.out.println("day " + day + " .... " + result);
        }

        result += dayGrowths[days].quantity;
            return result;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/aoc2021/Day06.txt"));
            List<Laternfish> Lfs = readSource(sc);

            System.out.println("The result is ... " + findResult(Lfs));
            System.out.println();

        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }

    }

}
