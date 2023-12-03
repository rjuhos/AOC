package aoc2022.Day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _MainDay07 {

    private static FileSystem getFileSystem(Scanner sc) {
        // Create root directory
        FileSystem root = new FileSystem("root");
        // read inputs from scanner
        FileSystem dir = root;
        while (sc.hasNextLine()) {
            // read and parse commands
            String[] cmd = sc.nextLine().split(" ");
            switch (cmd[0]) {
                case "$":    // execute command
                    switch (cmd[1]) {
                        case "cd":  // change directory
                            switch (cmd[2]) {
                                case "/":
                                    // move to root
                                    dir = root;
                                    break;
                                case "..":
                                    // move out from directory
                                    dir = dir.moveOut();
                                    break;
                                default:
                                    // move to directory
                                    dir = dir.moveIn(cmd[2]);
                            }
                            break;
                        case "ls":  // do nothing
                            break;
                        default:
                    }
                    break;
                case "dir":  // add subdirectory
                    dir.addDir(cmd[1]);
                    break;
                default:      // add file in to the directory
                    dir.addFile(cmd[1], Integer.valueOf(cmd[0]));
            }
        }
        return root;
    }

    private static long getResult1(FileSystem fs, int limit, long total) {
        for (FileSystem dir : fs.dirs ) {
            total = getResult1(dir, limit, total);
            long dirSize = dir.getSize();
            if (dirSize <= limit)
                total += dirSize;
        }
        return total;
    }

    private static long getResult2(FileSystem fs, long required, long smallest) {
        for (FileSystem dir : fs.dirs ) {
            smallest = getResult2(dir, required, smallest);
            long dirSize = dir.getSize();
            if (dirSize >= required && dirSize < smallest)
                smallest = dirSize;
        }
        return smallest;
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("resources/aoc2022/Day07.txt"));
            FileSystem fileSystem = getFileSystem(sc);
            int limit = 100000;
            long totalAvailableSize = 70000000;
            long minimalUnusedSize = 30000000;
            long fileSystemSize = fileSystem.getSize();
            long minimalFreeUpSize = minimalUnusedSize - (totalAvailableSize - fileSystemSize);
            System.out.println();
            fileSystem.prettyPrint();
            System.out.println();
            System.out.println("the file system size is ... " + fileSystemSize);
            System.out.println("the 1st result is ......... " + getResult1(fileSystem, limit, 0));
            System.out.println("the 2nd result is ......... " + getResult2(fileSystem, minimalFreeUpSize, fileSystemSize));
            System.out.println("the minimal free up size .. " + minimalFreeUpSize);
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
