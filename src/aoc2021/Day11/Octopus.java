package AOC2021.Day11;

public class Octopus {
    public byte energy;
    public boolean flash;
    public boolean newFlash;

    public Octopus(byte level) {
        energy = level;
        flash = false;
        newFlash = false;
    }

    public boolean increaseEnergy() {
        if (!flash) {
            energy++;
            flash = (energy == 10);
            if (flash) {
                energy = 0;
                newFlash = true;
            }
            return newFlash;
        }
        return false;
    }

    public void clearFlash() {
        flash = false;
    }

    public void clearNewFlash() {
        newFlash = false;
    }


}
