package AOC2022.Day12;

public class Vertex {
    private int id = -1;
    private char value = Character.MIN_VALUE;
    private boolean visited;
    private int fromVertex;
    private int distance;

    public Vertex() { init(); }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setVisited() {
        this.visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setFromVertex(int fromVertex) {
        this.fromVertex = fromVertex;
    }

    public int getFromVertex() {
        return fromVertex;
    }

    public void init() {
        visited = false;
        fromVertex = - 1;
        distance = Integer.MAX_VALUE/2;
    }
}
