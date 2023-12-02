package AOC2022.Day09;

import java.util.ArrayList;
import java.util.List;

public class Rope {
    private List<Knot> knots = new ArrayList<>();
    public boolean tailMoved = false;
    private Knot head, tail;

    public Rope(int noOfKnots, int startX, int startY) {
        this.knots = knots;
        for (int i = 0; i < noOfKnots; i++) {
            Knot knot = new Knot();
            knot.x = startX;
            knot.y = startY;
            knots.add(knot);
        }
        head = knots.get(0);
        tail = knots.get(knots.size()-1);
    }

    public void moveHeadRight() {
        head.moveRight();
        updateKnots();
    }

    public void moveHeadLeft() {
        head.moveLeft();
        updateKnots();
    }

    public void moveHeadUp() {
        head.moveUp();
        updateKnots();
    }

    public void moveHeadDown() {
        head.moveDown();
        updateKnots();
    }

    private void updateKnots() {
        for (int k = 1; k < knots.size(); k++) {
            boolean hasMoved = moveNextKnot(k);
            tailMoved = (k == knots.size() - 1) && hasMoved;
        }
    }

    private boolean moveNextKnot(int i) {
        boolean moved;
        Knot thisKnot = this.knots.get(i-1);
        Knot nextKnot = this.knots.get(i);
        int distX = thisKnot.getX() - nextKnot.getX();
        int distY = thisKnot.getY() - nextKnot.getY();
        if (distX > 1) {
            nextKnot.moveRight();
            if (distY > 0) nextKnot.moveUp(); else if (distY < 0) nextKnot.moveDown();
            moved = true;
        } else
        if (distX < -1) {
            nextKnot.moveLeft();
            if (distY > 0) nextKnot.moveUp(); else if (distY < 0) nextKnot.moveDown();
            moved = true;
        } else
        if (distY > 1) {
            nextKnot.moveUp();
            if (distX > 0) nextKnot.moveRight(); else if (distX < 0) nextKnot.moveLeft();
            moved = true;
        } else
        if (distY < -1) {
            nextKnot.moveDown();
            if (distX > 0) nextKnot.moveRight(); else if (distX < 0) nextKnot.moveLeft();
            moved = true;
        } else moved = false;
        return moved;
    }

    public int getTailX() { return tail.getX(); }
    public int getTailY() { return tail.getY(); }

}
