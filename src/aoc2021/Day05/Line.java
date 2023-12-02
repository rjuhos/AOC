package AOC2021.Day05;

public class Line {
    public boolean valid;
    public Point start = new Point();
    public Point stop = new Point();
    public int slope;
    public int offset;
    public boolean horizontal;
    public boolean vertical;

    public Line(String string) {
        init();
        // parse input string, e.g. "0,9 -> 5,9"  ....  <0,9> & <5,9>
        String[] points = string.split(" -> ");
        // parse start point
        String[] coordinates = points[0].split(",");
        start.x = Integer.parseInt(coordinates[0]);
        start.y = Integer.parseInt(coordinates[1]);
        // parse stop point
        coordinates = points[1].split(",");
        stop.x = Integer.parseInt(coordinates[0]);
        stop.y = Integer.parseInt(coordinates[1]);
        // get line properties
        valid = getProperties();
    }

    private void init() {
        // set the start point to 0,0
        start.x = 0; start.y = 0;
        // set the stop point to 0,0
        stop.x = 0; stop.y = 0;
        // set constants
        slope = 0; offset = 0;
        // set line horizontal and vertical property
        horizontal = false; vertical= false;
        // set line valid data status
        valid = false;
    }

    public Point PointFromY(int y) {
        Point point = new Point();
        point.y = y;
        if (vertical)
            point.x = start.x;
        else
            point.x = (int) ((y - offset)/slope);

        return point;
    }

    public Point PointFromX(int x) {
        Point point = new Point();
        point.x = x;
        point.y = (int) (slope*x + offset);

        return point;
    }

    private boolean getProperties() {

        boolean lineOK = (start.x != stop.x) || (start.y != stop.y);
        if (lineOK) {
            horizontal = (start.y == stop.y);
            vertical = (start.x == stop.x);
            if (vertical) {
                if (start.y > stop.y) {
                    Point p = start;
                    start = stop;
                    stop = p;
                }
                slope = 0x7fff_ffff;
                offset = start.y;
            } else {
                if (start.x > stop.x) {
                    Point p = start;
                    start = stop;
                    stop = p;
                }
                slope = (int) (stop.y - start.y) / (stop.x - start.x);
                offset = start.y - slope * start.x;
            }
        }

        return lineOK;
    }
}


