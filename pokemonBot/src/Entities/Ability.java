package Entities;

public class Ability {

    private String abilityName;
    private String abilityEffect;

    public Ability(){
        abilityEffect = "";
        abilityEffect = "";
    }

    public Ability(String name, String abilityEffect){
        abilityName = name;
        this.abilityEffect = abilityEffect;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public String getAbilityEffect() {
        return abilityEffect;
    }

    public void setAbilityEffect(String abilityEffect) {
        this.abilityEffect = abilityEffect;
    }
}
