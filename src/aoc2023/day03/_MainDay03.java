package aoc2023.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _MainDay03 {

    private static byte[][] inputArray;

    public static byte[][] getInputArray(Scanner sc) {
        List<byte[]> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            byte[] bytes = sc.nextLine().getBytes();
            list.add(bytes);
        }
        return list.toArray(new byte[0][]);
    }

    private static List<Integer> getPartNumbers() {
        int result = 0;
        int sizeX = inputArray.length;
        int sizeY = inputArray[0].length;
        int x,y;
        int partNo;
        boolean isAdjucent;
        List<Integer> partNumbers = new ArrayList<>();

        for (x = 0; x < sizeX; x++) {
            partNo = 0;
            isAdjucent = false;
            for (y = 0; y < sizeY; y++) {
                char c = (char) inputArray[x][y];
                if ( isNumber(c) ) {
                    partNo = 10 * partNo + c - '0';
                    isAdjucent |= isAdjacentToSymbol(x, y);
                } else {
                    if (partNo > 0 & isAdjucent) { partNumbers.add(partNo); };
                    partNo = 0;
                    isAdjucent = false;
                }
                if (y == sizeY - 1 & partNo > 0  & isAdjucent) { partNumbers.add(partNo); };
            }
        }
        return partNumbers;
    }

    private static long getGears() {
        long result = 0;
        int sizeX = inputArray.length;
        int sizeY = inputArray[0].length;
        int x,y;

        for (x = 0; x < sizeX; x++) {
            for (y = 0; y < sizeY; y++) {
                char c = (char) inputArray[x][y];
                if (!isNumber(c) & !(c == '.')) {
                    int noOfGears = 0;
                    long gear = 0;
                    if (hasGearTopLeft(x,y)) {
                        if (noOfGears == 0) {
                            gear = getPartNo(x - 1, y - 1);
                        } else {
                            gear *= getPartNo(x - 1, y - 1);
                        }
                        noOfGears += 1;
                    }
                    if (hasGearTop(x,y)) {
                        if (noOfGears == 0) {
                            gear = getPartNo(x - 1, y);
                        } else {
                            gear *= getPartNo(x - 1, y);
                        }
                        noOfGears += 1;
                    }
                    if (hasGearTopRight(x,y)) {
                        if (noOfGears == 0) {
                            gear = getPartNo(x - 1, y + 1);
                        } else {
                            gear *= getPartNo(x - 1, y + 1);
                        }
                        noOfGears += 1;
                    }
                    if (hasGearLeft(x,y)) {
                        if (noOfGears == 0) {
                            gear = getPartNo(x , y - 1);
                        } else {
                            gear *= getPartNo(x , y - 1);
                        }
                        noOfGears += 1;
                    }
                    if (hasGearRight(x,y)) {
                        if (noOfGears == 0) {
                            gear = getPartNo(x, y + 1);
                        } else {
                            gear *= getPartNo(x, y + 1);
                        }
                        noOfGears += 1;
                    }
                    if (hasGearBottomLeft(x,y)) {
                        if (noOfGears == 0) {
                            gear = getPartNo(x + 1, y - 1);
                        } else {
                            gear *= getPartNo(x + 1, y - 1);
                        }
                        noOfGears += 1;
                    }
                    if (hasGearBottom(x,y)) {
                        if (noOfGears == 0) {
                            gear = getPartNo(x + 1, y);
                        } else {
                            gear *= getPartNo(x + 1, y);
                        }
                        noOfGears += 1;
                    }
                    if (hasGearBottomRight(x,y)) {
                        if (noOfGears == 0) {
                            gear = getPartNo(x + 1, y + 1);
                        } else {
                            gear *= getPartNo(x + 1, y + 1);
                        }
                        noOfGears += 1;
                    }
                    if (noOfGears > 1) {
                        result += gear;
                    }
                }
            }
        }

        return result;
    }

    private static boolean isAdjacentToSymbol(int posX, int posY) {
        boolean result = false;
        int sizeX = inputArray.length;
        int sizeY = inputArray[0].length;
        int x,y;

        for (x = posX - 1; x <= posX + 1; x++) {
            for (y = posY - 1; y <= posY + 1; y++) {
                if ( x >= 0 & x < sizeX & y >= 0 & y < sizeX) {
                    char c = (char) inputArray[x][y];
                    if ( !( isNumber(c) | c == '.' ) ) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    private static boolean hasGearTopLeft(int posX, int posY) {
        boolean result = false;

        if (posX > 0 & posY > 0) {
            result = isNumber((char) inputArray[posX-1][posY-1]) & !isNumber((char) inputArray[posX-1][posY]);
        }
        return result;
    }

    private static boolean hasGearTop(int posX, int posY) {
        boolean result = false;

        if (posX > 0) {
            result = isNumber((char) inputArray[posX-1][posY]);
        }
        return result;
    }

    private static boolean hasGearTopRight(int posX, int posY) {
        boolean result = false;
        int sizeY = inputArray[0].length;

        if (posX > 0 & posY < sizeY - 1) {
            result = isNumber((char) inputArray[posX-1][posY+1]) & !isNumber((char) inputArray[posX-1][posY]);
        }
        return result;
    }

    private static boolean hasGearLeft(int posX, int posY) {
        boolean result = false;

        if (posY > 0) {
            result = isNumber((char) inputArray[posX][posY-1]);
        }
        return result;
    }

    private static boolean hasGearRight(int posX, int posY) {
        boolean result = false;
        int sizeY = inputArray[0].length;

        if (posY < sizeY - 1) {
            result = isNumber((char) inputArray[posX][posY+1]);
        }
        return result;
    }

    private static boolean hasGearBottomLeft(int posX, int posY) {
        boolean result = false;
        int sizeX = inputArray.length;

        if (posX < sizeX - 1 & posY > 0) {
            result = isNumber((char) inputArray[posX+1][posY-1]) & !isNumber((char) inputArray[posX+1][posY]);
        }
        return result;
    }

    private static boolean hasGearBottom(int posX, int posY) {
        boolean result = false;
        int sizeX = inputArray.length;

        if (posX < sizeX - 1) {
            result = isNumber((char) inputArray[posX+1][posY]);
        }
        return result;
    }

    private static boolean hasGearBottomRight(int posX, int posY) {
        boolean result = false;
        int sizeX = inputArray.length;
        int sizeY = inputArray[0].length;

        if (posX < sizeX - 1 & posY < sizeY - 1) {
            result = isNumber((char) inputArray[posX+1][posY+1]) & !isNumber((char) inputArray[posX+1][posY]);
        }
        return result;
    }

    private static int getPartNo(int posX, int posY) {
        int result = 0;
        int sizeY = inputArray[0].length;
        int y = posY;

        while (y > 0) {
            if (!isNumber((char) inputArray[posX][y-1])) { break; }
            y-= 1;
        }
        while (isNumber((char) inputArray[posX][y])) {
            result = 10 * result + (char) inputArray[posX][y] - '0';
            if (y >= sizeY - 1) { break; }
            if (!isNumber((char) inputArray[posX][y+1])) { break; }
            y+= 1;
        }
        return result;
    }

    private static boolean isNumber(char c) {
        return c >= '0' & c <= '9';
    }

    private static int getResult1() {
        int result = 0;
        List<Integer> partNumbers = new ArrayList<>();

        partNumbers = getPartNumbers();
        for (int p :partNumbers) {
            result += p;
        }
        return result;
    }

    private static long getResult2() {
        return getGears();
    }

    public static void main (String[]args){
        try {
            Scanner sc = new Scanner(new File("resources/AOC2023/Day03.txt"));
            inputArray = getInputArray(sc);
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
