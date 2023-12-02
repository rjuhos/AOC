package AOC2022.Day09;

public class Knot {
    int x = 0; int y = 0;

    public Knot() {}

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public void moveRight() { x++; }
    public void moveLeft() { if (x > 0) x--; }
    public void moveUp() { y++; }
    public void moveDown() { if (y>0) y--; }

}
