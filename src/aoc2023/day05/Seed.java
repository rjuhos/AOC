package aoc2023.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Seed {
    private final List<Location> locations;
    private final long seedId;

    public long getId() { return seedId; }
    public void addLocation(String name, long destination) {
        Location location = new Location();
        location.setName(name);
        location.setLocation(destination);
        this.locations.add(location);
    }
    public boolean hasLocation(String name) {
        return this.locations.stream()
                          .anyMatch(p -> Objects.equals(p.getName(), name));
    }
    public long getLocationDestination(String name) {
        long position = Long.parseLong(null);
        for (Location p : this.locations) {
            if (Objects.equals(p.getName(), name)) {
                position = p.getLocation();
                break;
            }
        }
        return position;
    }
    public long getSeedDestination() {
        if (!locations.isEmpty())  {
            return locations.getLast().getLocation();
        } else {
            return this.seedId;
        }
    }
    public String getSeedLocation() {
        if (!locations.isEmpty())  {
            return locations.getLast().getName();
        } else {
            return "";
        }

    }
    public Seed(long id) {
        this.locations = new ArrayList<>();
        this.seedId = id;
    }

}
