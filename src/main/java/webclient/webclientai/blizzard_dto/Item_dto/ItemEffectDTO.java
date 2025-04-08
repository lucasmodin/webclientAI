package webclient.webclientai.blizzard_dto.Item_dto;

public class ItemEffectDTO {
    private String spellName;
    private String description;

    public ItemEffectDTO(String spellName, String description) {
        this.spellName = spellName;
        this.description = description;
    }

    public ItemEffectDTO() {

    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
