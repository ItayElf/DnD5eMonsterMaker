package MonsterMakerPackage;

public class Action {
    private String name;
    private int mode; // 0 - no attack, 1 - melee, 2 - ranged
    private int bonusToHit;
    private int[] reach;
    private String targets;
    private Dice damageDice;
    private String damageType;
    private String description = "";

    public Action(String name, int mode, int bonusToHit, int[] reach, String targets, Dice damageDice, String damageType, String description) {
        this.name = name;
        this.mode = mode;
        this.bonusToHit = bonusToHit;
        this.reach = reach;
        this.targets = targets;
        this.damageDice = damageDice;
        this.damageType = damageType;
        this.description = description;
    }

    public Action(String name, int mode, int bonusToHit, int[] reach, String targets, Dice damageDice, String damageType) {
        this.name = name;
        this.mode = mode;
        this.bonusToHit = bonusToHit;
        this.reach = reach;
        this.targets = targets;
        this.damageDice = damageDice;
        this.damageType = damageType;
    }

    public Action(String name, String description) {
        this.name = name;
        this.description = description;
        this.mode = 0;
    }

    public Action() {
    }


    public int getBonusToHit() {
        return bonusToHit;
    }

    public void setBonusToHit(int bonusToHit) {
        this.bonusToHit = bonusToHit;
    }

    public int[] getReach() {
        return reach;
    }

    public void setReach(int[] reach) {
        this.reach = reach;
    }

    public String getTargets() {
        return targets;
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }

    public Dice getDamageDice() {
        return damageDice;
    }

    public void setDamageDice(Dice damageDice) {
        this.damageDice = damageDice;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
