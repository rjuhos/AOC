package aoc2023.day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class _MainDay05 {

    private static List<Seed> seeds;
    private static List<Map> maps;
    private static long[] seedsArray;

    private static long[] stringToLong(String inString) {
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

    private static boolean mapWithSourceExists(String mapSourceName) {
        return maps.stream()
                .anyMatch(m -> Objects.equals(m.getSourceName(), mapSourceName));
    }
    private static boolean mapWithDestinationExists(String mapDestinationName) {
        return maps.stream()
                .anyMatch(m -> Objects.equals(m.getDestinationName(), mapDestinationName));
    }

    private static Map getMapWithSource(String mapSourceName) {
        Map map = null;
        for (Map m : maps) {
            if (Objects.equals(m.getSourceName(), mapSourceName)) {
                map = m;
                break;
            }
        }
        return map;
    }
    private static Map getMapWithDestination(String mapDestinationName) {
        Map map = null;
        for (Map m : maps) {
            if (Objects.equals(m.getDestinationName(), mapDestinationName)) {
                map = m;
                break;
            }
        }
        return map;
    }

    private static void getInputData(Scanner sc) {
        seeds = new ArrayList<>();
        maps = new ArrayList<>();
        Map map = null;
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.contains("seeds:")) {
                seedsArray = stringToLong(str);
            //    Arrays.stream(seedsArray).forEach(id -> { Seed seed = new Seed(id); seeds.add(seed); });
          } else if (str.contains("map:")) {
                map = new Map(str.substring(0,str.length()-5));
            } else if (str.isBlank()) {
                if (map != null & !mapWithSourceExists(map != null ? map.getSourceName() : null )) {
                    maps.add(map);
                }
                map = null;
            } else {
                if (map != null) {
                    map.addFunction(str);
                }
            }
        }
        if (map != null & !mapWithSourceExists(map != null ? map.getSourceName() : null )) {
            maps.add(map);
        }
    }

    private static void getAllSeedsLocations() {
        // set first map as a start of location search
        String mapSourceName = "seed";
        while (mapWithSourceExists(mapSourceName)) {
            Map map= getMapWithSource(mapSourceName);
            for (Seed s : seeds ) {
                s.addLocation(map.getDestinationName(), map.getLocation(s.getSeedDestination()));
            }
            mapSourceName = map.getDestinationName();
        }
    }
    private static void getSeedNo() {
        // set first map as a start of location search
        String mapDestinationName = "location";
        while (mapWithDestinationExists(mapDestinationName)) {
            Map map= getMapWithSource(mapDestinationName);
            for (Seed s : seeds ) {
                s.addLocation(map.getDestinationName(), map.getLocation(s.getSeedDestination()));
            }
            mapDestinationName = map.getDestinationName();
        }
    }

    private static long getSeedMinDestinations() {
        getAllSeedsLocations();
        long min = Long.MAX_VALUE;
        long seedLocation;
        for (long seed : seedsArray) {
            seedLocation = seed;
            for (Map map : maps) {
                seedLocation = map.getLocation(seedLocation);
            }
            if (seedLocation < min) {
                min = seedLocation;
            }
        }
        return min;
    }

    private static long getLowestLocation() {
        long minimalLocation = maps.getLast().getMinimalLocationStart();
        return 0;
    }

    public static void main (String[]args){
        try {
            Scanner sc = new Scanner(new File("resources/AOC2023/Day05.txt"));
            getInputData(sc);
            System.out.println();
            System.out.println("the result is ... " + getSeedMinDestinations());
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("this file does not exist");
        }
    }
}
