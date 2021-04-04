package MonsterMakerPackage;

public class Dice {
    private int diceCount;
    private int diceSize;
    private int bonus;

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

    public Dice() {}

    public int diceAverage() {
        return diceCount * diceSize / 2 + bonus;
    }

    public int hitDiceAverage() {
        return (int) ((diceSize / 2 + 0.5) * diceCount + bonus);
    }

    public int getDiceCount() {
        return diceCount;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }

    public int getDiceSize() {
        return diceSize;
    }

    public void setDiceSize(int diceSize) {
        this.diceSize = diceSize;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
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
