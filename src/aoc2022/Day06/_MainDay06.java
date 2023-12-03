package aoc2022.Day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class _MainDay06 {

    private static int getResult(Scanner sc, int seqLen) {
        if (sc.hasNextLine()) {
            char[] charArray = sc.nextLine().toCharArray();
            Sequence startSeq = new Sequence(seqLen);
            for (int pos = 0; pos < charArray.length; pos++) {
                startSeq.addChar(charArray[pos]);
                if (startSeq.seqOK()) return pos + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        try {
            int seqLen = 14;
            Scanner sc = new Scanner(new File("resources/aoc2022/Day06.txt"));
            System.out.println("the result is ... " + getResult(sc, seqLen));
        } catch (
            FileNotFoundException e) {
                System.out.println("this file does not exist");
        }
    }
}
