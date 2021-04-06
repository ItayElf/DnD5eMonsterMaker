package MonsterMakerPackage;

public class Action {
    private String name;
    private int mode; // 0 - no attack, 1 - melee, 2 - ranged
    private int bonusToHit;
    private String reach;
    private String targets;
    private Dice damageDice;
    private String damageType;
    private String description = "";

    public Action(String name, int mode, int bonusToHit, String reach, String targets, Dice damageDice, String damageType, String description) {
        this.name = name;
        this.mode = mode;
        this.bonusToHit = bonusToHit;
        this.reach = reach;
        this.targets = targets;
        this.damageDice = damageDice;
        this.damageType = damageType;
        this.description = description;
    }

    public Action(String name, int mode, int bonusToHit, String reach, String targets, Dice damageDice, String damageType) {
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

    @Override
    public String toString() {
        if (mode == 0) {
            return "<html><strong>" + name + "</strong> - " + description + "</html>";
        }
        return "<html><strong>%s.</strong> %s Weapon Attack: %s to hit, reach %s ft., %s. Hit: %s (%s) %s damage%s.</html>".formatted(
                name,
                mode == 1 ? "Melee" : "Ranged",
                bonusToHit >= 0 ? "+" + bonusToHit : bonusToHit + "",
                reach + "",
                targets + (targets.equals("one") ? " target" : " targets"),
                damageDice.diceAverage() + "",
                damageDice.toString(),
                damageType,
                description
        );
    }

    public int getBonusToHit() {
        return bonusToHit;
    }

    public void setBonusToHit(int bonusToHit) {
        this.bonusToHit = bonusToHit;
    }

    public String getReach() {
        return reach;
    }

    public void setReach(String reach) {
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
