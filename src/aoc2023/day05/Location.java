package aoc2023.day05;

public class Location {

    private String name;
    private long location;

    public void setName(String name) { this.name = name; }
    public void setLocation(long location) { this.location = location; }
    public String getName() { return name; }
    public long getLocation() { return location; }

    public Location() {
        name = "";
        location = -1;
    }
}
