package aoc2022.Day13;

import aoc2022.Day07.FileSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _MainDay13 {

    private static List<Packet> packets;

    private static List<Packet> redPackets(Scanner sc) {
        List<Packet> packets = new ArrayList<>();
        while (sc.hasNextLine()) {
            Packet packet = new Packet(sc.nextLine(), sc.nextLine());
            packets.add(packet);
            if (sc.hasNextLine()) sc.nextLine();
        }
        return packets;
    }

    private static int getResult() {
        int result = 0;
        for (int i = 0; i < packets.size(); i++)
            if (packets.get(i).rightOrder) result += i+1;
        return result;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/aoc2022/Day13.txt"));
            packets = redPackets(sc);
            System.out.println();
            System.out.println("the 1st result is ... " + getResult());
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
