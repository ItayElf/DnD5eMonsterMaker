package MonsterMakerPackage;

public class Dice {
    int diceCount;
    int diceSize;
    int bonus;

    public Dice(int count, int size, int bonus) {
        diceCount = count;
        diceSize = size;
        this.bonus = bonus;
    }

    public Dice(int count, int size) {
        this(count, size, 0);
    }

    public Dice(int size) {
        this(1, size, 0);
    }

    public int diceAverage() {
        return diceCount * diceSize / 2 + bonus;
    }

    @Override
    public String toString() {
        String str = diceCount + "d" + diceSize;
        if (bonus != 0) {
            String sep = bonus > 0 ? " + " : " - ";
            str = str + sep + Math.abs(bonus);
        }
        return str;
    }
}
