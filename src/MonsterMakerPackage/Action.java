package MonsterMakerPackage;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Action action = (Action) o;

        if (mode != action.mode) return false;
        if (bonusToHit != action.bonusToHit) return false;
        if (!Objects.equals(name, action.name)) return false;
        if (!Objects.equals(reach, action.reach)) return false;
        if (!Objects.equals(targets, action.targets)) return false;
        if (!Objects.equals(damageDice, action.damageDice)) return false;
        if (!Objects.equals(damageType, action.damageType)) return false;
        return Objects.equals(description, action.description);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + mode;
        result = 31 * result + bonusToHit;
        result = 31 * result + (reach != null ? reach.hashCode() : 0);
        result = 31 * result + (targets != null ? targets.hashCode() : 0);
        result = 31 * result + (damageDice != null ? damageDice.hashCode() : 0);
        result = 31 * result + (damageType != null ? damageType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
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
