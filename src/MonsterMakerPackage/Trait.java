package MonsterMakerPackage;

import java.util.Objects;

public class Trait {
    private String name;
    private String description;

    public Trait(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Trait() {
    }

    @Override
    public String toString() {
        return "<html><strong>" + name + "</strong> - " + description + "</html>";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trait trait = (Trait) o;

        if (!Objects.equals(name, trait.name)) return false;
        return Objects.equals(description, trait.description);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
