package aoc2023.day05;

import java.util.Arrays;
import java.util.Objects;

public class MapRange {
    long sourceStart;
    long locationStart;
    long length;
    private long[] stringToLong(String inString) {
        return Arrays.stream(inString.replaceAll("[^0-9]+",";").split(";"))
                .map(s -> { try {
                    return Long.valueOf(s);
                } catch (NumberFormatException ignored) {
                    return null;
                }
                })
                .filter(Objects::nonNull)
                .mapToLong(x -> x)
                .toArray();
    }

    public boolean isInRange(long source) {
        return source >= this.sourceStart & source < this.sourceStart + this.length;
    }
    public long getLocation(long source) {
        return this.locationStart + source - this.sourceStart;
    }
    public boolean isInLocationRange(long location) {
        return location >= this.locationStart & location < this.locationStart + this.length;
    }
    public long getSource(long location) { return this.sourceStart + location - this.locationStart; }

    public long getLocationStart() { return locationStart; }

    public MapRange(String inputString) {
        long[] data = stringToLong(inputString);
        this.locationStart = data[0];
        this.sourceStart = data[1];
        this.length = data[2];
    }

}
