package aoc2021.Day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _MainDay16 {

    public static long readDataSource(Scanner scanner) {
        long versionSum = 0;
        long result = 0;
        List<Packet> Packets = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String message = hexToBinary(scanner.nextLine());
            Packet Packet = new Packet(message);
            Packets.add(Packet);
            versionSum += Packet.getVersionSum();
            result = Packet.getValue();
            System.out.println(result);
        }
        System.out.println("TREE --------------------");
        Packets.get(Packets.size() - 1).prettyPrint();
        return result;
    }

    private static String hexToBinary(String hexString) {
        StringBuilder bString = new StringBuilder();
        // convert HEX data string to Binary string
        for (char ch : hexString.toCharArray()) {
            switch (ch) {
                case '0' -> bString.append("0000");
                case '1' -> bString.append("0001");
                case '2' -> bString.append("0010");
                case '3' -> bString.append("0011");
                case '4' -> bString.append("0100");
                case '5' -> bString.append("0101");
                case '6' -> bString.append("0110");
                case '7' -> bString.append("0111");
                case '8' -> bString.append("1000");
                case '9' -> bString.append("1001");
                case 'A' -> bString.append("1010");
                case 'B' -> bString.append("1011");
                case 'C' -> bString.append("1100");
                case 'D' -> bString.append("1101");
                case 'E' -> bString.append("1110");
                case 'F' -> bString.append("1111");
            }
        }
        return bString.toString();
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("resources/aoc2021/Day16.txt"));
            long result = readDataSource(scanner);

            System.out.println();
            System.out.println("The result is ... " + result);
            System.out.println();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}