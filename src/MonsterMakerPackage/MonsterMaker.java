package MonsterMakerPackage;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

public class MonsterMaker {
    private String name;
    private String subtitle;

    private int armorClass;
    private String armorClassDesc = "";
    private int hitpointsAvg;
    private Dice hitDice;
    private int speed;

    private int[] abilityScores;

    private int[] savingThrows = new int[] {0, 0, 0, 0, 0, 0};
    private String[] skills;
    private String[] damageImmunities;
    private String[] damageResistances;
    private String[] conditionImmunities;
    private String[] senses;
    private String[] languages;
    private double challenge;

    private Trait[] traits;
    private Action[] actions;
    private Action[] reactions;

    public MonsterMaker(String name, String subtitle, int armorClass, int hitpointsAvg, Dice hitDice, int speed, int[] abilityScores, String[] damageImmunities, String[] conditionImmunities, String[] senses, double challenge, Trait[] traits, Action[] actions) {
        this.name = name;
        this.subtitle = subtitle;
        this.armorClass = armorClass;
        this.hitpointsAvg = hitpointsAvg;
        this.hitDice = hitDice;
        this.speed = speed;
        this.abilityScores = abilityScores;
        this.damageImmunities = damageImmunities;
        this.conditionImmunities = conditionImmunities;
        this.senses = senses;
        this.challenge = challenge;
        this.traits = traits;
        this.actions = actions;
    }

    public MonsterMaker() {

    }

    public void saveAsXml(File file) throws IOException {
        ObjectMapper mapper = new XmlMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.writeValue(file, this);
    }

    public static MonsterMaker fromXml(File file) throws IOException {
        ObjectMapper mapper = new XmlMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        InputStream inputStream = new FileInputStream(file);
        TypeReference<MonsterMaker> typeReference = new TypeReference<MonsterMaker>() {};
        return mapper.readValue(inputStream, typeReference);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public String getArmorClassDesc() {
        return armorClassDesc;
    }

    public void setArmorClassDesc(String armorClassDesc) {
        this.armorClassDesc = armorClassDesc;
    }

    public int getHitpointsAvg() {
        return hitpointsAvg;
    }

    public void setHitpointsAvg(int hitpointsAvg) {
        this.hitpointsAvg = hitpointsAvg;
    }

    public Dice getHitDice() {
        return hitDice;
    }

    public void setHitDice(Dice hitDice) {
        this.hitDice = hitDice;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int[] getAbilityScores() {
        return abilityScores;
    }

    public void setAbilityScores(int[] abilityScores) {
        this.abilityScores = abilityScores;
    }

    public void  setAbilityScores(int index, int value) {
        this.abilityScores[index] = value;
    }

    public int[] getSavingThrows() {
        return savingThrows;
    }

    public void setSavingThrows(int[] savingThrows) {
        this.savingThrows = savingThrows;
    }

    public void setSavingThrows(int index, int value) {
        this.savingThrows[index] = value;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public String[] getDamageImmunities() {
        return damageImmunities;
    }

    public void setDamageImmunities(String[] damageImmunities) {
        this.damageImmunities = damageImmunities;
    }

    public String[] getConditionImmunities() {
        return conditionImmunities;
    }

    public void setConditionImmunities(String[] conditionImmunities) {
        this.conditionImmunities = conditionImmunities;
    }

    public String[] getSenses() {
        return senses;
    }

    public void setSenses(String[] senses) {
        this.senses = senses;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public double getChallenge() {
        return challenge;
    }

    public void setChallenge(double challenge) {
        this.challenge = challenge;
    }

    public Trait[] getTraits() {
        return traits;
    }

    public void setTraits(Trait[] traits) {
        this.traits = traits;
    }

    public Action[] getActions() {
        return actions;
    }

    public void setActions(Action[] actions) {
        this.actions = actions;
    }

    public Action[] getReactions() {
        return reactions;
    }

    public void setReactions(Action[] reactions) {
        this.reactions = reactions;
    }

    public String[] getDamageResistances() {
        return damageResistances;
    }

    public void setDamageResistances(String[] damageResistances) {
        this.damageResistances = damageResistances;
    }
}
