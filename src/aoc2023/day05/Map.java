package aoc2023.day05;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private final List<MapRange> ranges;
    private final String sourceName, destinationName;

    public void addFunction(String inputString) {
        MapRange fn = new MapRange(inputString);
        ranges.add(fn);
    }
    public String getSourceName() { return this.sourceName; }
    public String getDestinationName() { return this.destinationName; }
    public long getLocation(long source) {
        long location = 0;
        if (!ranges.isEmpty()) {
            for (MapRange r : ranges) {
                if (r.isInRange(source)) {
                    location =  r.getLocation(source);
                    break;
                } else {
                    location = source;
                }
            }
        } else {
            location = source;
        }
        return location;
    }
    public long getSource(long location) {
        long source = 0;
        if (!ranges.isEmpty()) {
            for (MapRange r : ranges) {
                if (r.isInLocationRange(location)) {
                    source =  r.getSource(location);
                    break;
                } else {
                    source = location;
                }
            }
        } else {
            source = location;
        }
        return source;
    }

    public long getMinimalLocationStart(){
        long minimalLocation = Long.MAX_VALUE;
        for (MapRange r : ranges) { minimalLocation = Math.min(r.getLocationStart(), minimalLocation); }
        return minimalLocation;
    }
    public Map(String mapName) {
        ranges = new ArrayList<>();
        String[] name = mapName.split("-to-");
        this.sourceName = name[0];
        this.destinationName = name[1];
    }

}
