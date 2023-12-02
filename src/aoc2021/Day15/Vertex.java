package AOC2021.Day15;

public class Vertex {
    private long distance;
    private boolean visited;

    public Vertex() {
        distance = Long.MAX_VALUE;
        visited = false;
    }

    public void setDistance(Long value){
        distance = value;
    }

    public long getDistance() {
        return distance;
    }

    public void setVisited() {
        visited = true;
    }

    public void clearVisited() {
        visited = false;
    }

    public boolean isVisited() {
        return visited;
    }

    public void relax(long previousVertex, byte distance) {
        if (!visited && ((previousVertex + distance) < this.distance))
            this.distance = previousVertex + distance;
    }

}
