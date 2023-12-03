package aoc2022.Day09;

public class MoveCmd {
    private char dir = Character.MIN_VALUE;
    private int dist = 0;

    public MoveCmd(char dir, int dist) {
        this.dir = dir;
        this.dist = dist;
    }

    public char getDir() {
        return this.dir;
    }

    public int getDist() {
        return this.dist;
    }
}
