package aoc2022.Day07;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileSystem {
    public List<FileSystem> dirs = new LinkedList<FileSystem>();
    public List<File> files = new ArrayList<File>();
    private FileSystem parent = null;
    private String name = "";
    public long size = 0;

    public FileSystem(String dirName) {
        this.name = dirName;
    }

    private void assignParent(FileSystem parent) {
        this.parent = parent;
    }

    public void addDir(String dirName) {
        FileSystem dir = new FileSystem(dirName);
        dir.assignParent(this);
        this.dirs.add(dir);
    }

    public void addFile(String fileName, int size) {
        File file = new File(fileName, size);
        this.files.add(file);
    }

    public String getName() {
        return this.name;
    }

    public FileSystem moveIn(String dirName) {
        for (FileSystem dir : this.dirs)
            if (dir.getName().equals(dirName)) return dir;
        return this;
    }

    public FileSystem moveOut() {
        if (this.parent != null) return this.parent;
        else return this;
    }

    public long getSize() {
        long size = 0;
        for (FileSystem dir : dirs) size += dir.getSize();
        for (File file : files) size += file.size;
        this.size = size;
        return size;
    }

    private void prettyPrint(int depth) {
        if (depth > 0) {
            StringBuilder prefix = new StringBuilder("");
            for (int i = 0; i < depth - 1; i++) {
                prefix.append("  ");
            }
            if (this.size <= 100000)
                System.out.println(prefix + "> " + this.name + "  (" + this.size + ")*");
            else
                System.out.println(prefix + "> " + this.name + "  (" + this.size + ")");
            for (File file : this.files)
                System.out.println(prefix + "  .. " + file.name + "  (" + file.size + ")");
            for (FileSystem dir : dirs)
                dir.prettyPrint(depth + 1);
        }
        else {
            System.out.println(this.name + "  (" + this.size + ")");
            for (File file : this.files)
                System.out.println(".. " + file.name + "  (" + file.size + ")");
            for (FileSystem dir : dirs)
                dir.prettyPrint(depth + 1);
        }
    }

    public void prettyPrint() {
        prettyPrint(0);
    }

}
