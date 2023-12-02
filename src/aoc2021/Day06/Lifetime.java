package AOC2021.Day06;

public abstract class Lifetime {
    public byte cycleTime = 6;
    public byte lifetime;

    public Lifetime(byte init, byte cycle) {
        cycleTime = cycle;
        lifetime = init;
    }

    public boolean NewBorn() {
        boolean newborn = false;
        lifetime--;
        if (lifetime < 0) {
            lifetime = cycleTime;
            newborn = true;
        }
        return newborn;
    }

}
