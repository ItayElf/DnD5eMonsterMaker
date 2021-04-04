package MonsterMakerPackage;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

public class MonsterMaker {
    private String name; //done
    private String size; //done
    private String type; //done
    private String tag = ""; //done
    private String alignment; //done

    private int armorClass; //done
    private String armorClassDesc = ""; //done
    private Dice hitDice; //done
    private int speed;

    private int[] abilityScores; //done

    private int[] savingThrows = new int[] {0, 0, 0, 0, 0, 0};
    private String skills;
    private String damageVulnerabilities; //done
    private String damageImmunities; //done
    private String damageResistances; //done
    private String conditionImmunities; //done
    private String senses;
    private String languages;
    private double challenge; //done

    private Trait[] traits;
    private Action[] actions;
    private Action[] reactions;

    public MonsterMaker() {

    }

    public void saveAs5emon(File file) throws IOException {
        ObjectMapper mapper = new XmlMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.writeValue(file, this);
    }

    public static MonsterMaker from5emon(File file) throws IOException {
        ObjectMapper mapper = new XmlMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        InputStream inputStream = new FileInputStream(file);
        TypeReference<MonsterMaker> typeReference = new TypeReference<>() {
        };
        return mapper.readValue(inputStream, typeReference);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
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

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getDamageImmunities() {
        return damageImmunities;
    }

    public void setDamageImmunities(String damageImmunities) {
        this.damageImmunities = damageImmunities;
    }

    public String getConditionImmunities() {
        return conditionImmunities;
    }

    public void setConditionImmunities(String conditionImmunities) {
        this.conditionImmunities = conditionImmunities;
    }

    public String getSenses() {
        return senses;
    }

    public void setSenses(String senses) {
        this.senses = senses;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public double getChallenge() {
        return challenge;
    }

    public String challengeString() {
        if (challenge == 0.125) { return "1/8"; }
        else if (challenge == 0.25) { return "1/4"; }
        else if (challenge == 0.5) { return "1/2"; }
        else { return (challenge + "").split("\\.")[0]; }
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

    public String getDamageResistances() {
        return damageResistances;
    }

    public void setDamageResistances(String damageResistances) {
        this.damageResistances = damageResistances;
    }

    public String getDamageVulnerabilities() {
        return damageVulnerabilities;
    }

    public void setDamageVulnerabilities(String damageVulnerabilities) {
        this.damageVulnerabilities = damageVulnerabilities;
    }
}
