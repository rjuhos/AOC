package AOC2021.Day09;

public class Point {
    public byte height;
    public boolean isLowest;
    public boolean isBasin;
    public int basinId;

    public void setBasin(int ID) {
        isBasin = true;
        basinId = ID;
    }

    public Point(byte value) {
        height = value;
        isLowest = value == 0;
        isBasin = value == 0;
        basinId = -1;
    }
}
