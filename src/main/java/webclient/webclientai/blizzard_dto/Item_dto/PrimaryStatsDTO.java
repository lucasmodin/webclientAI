package webclient.webclientai.blizzard_dto.Item_dto;

public class PrimaryStatsDTO {
    private Integer strength;
    private Integer agility;
    private Integer intellect;
    private Integer stamina;

    public PrimaryStatsDTO() {

    }

    public PrimaryStatsDTO(Integer strength, Integer agility, Integer intellect, Integer stamina) {
        this.strength = strength;
        this.agility = agility;
        this.intellect = intellect;
        this.stamina = stamina;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getAgility() {
        return agility;
    }

    public void setAgility(Integer agility) {
        this.agility = agility;
    }

    public Integer getIntellect() {
        return intellect;
    }

    public void setIntellect(Integer intellect) {
        this.intellect = intellect;
    }

    public Integer getStamina() {
        return stamina;
    }

    public void setStamina(Integer stamina) {
        this.stamina = stamina;
    }
}
