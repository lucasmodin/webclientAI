package webclient.webclientai.blizzard_dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

public class EquippedItemDTO {

    private int itemId; //not for JPA, but for making calls fx. item frames/renders
    private String name;
    private String slot;
    private String quality;
    private String mediaUrl;
    private int itemLevel;
    private PrimaryStatsDTO primaryStats;
    private SecondaryStatsDTO secondaryStats;
    private List<ItemEffectDTO> equipEffects;

    public EquippedItemDTO() {
        this.equipEffects = new ArrayList<>();
    }

    public EquippedItemDTO(int itemId, String name, String slot, String quality,
                           String mediaUrl, int itemLevel,
                           PrimaryStatsDTO primaryStats, SecondaryStatsDTO secondaryStats,
                           List<ItemEffectDTO> equipEffects) {
        this.itemId = itemId;
        this.name = name;
        this.slot = slot;
        this.quality = quality;
        this.mediaUrl = mediaUrl;
        this.itemLevel = itemLevel;
        this.primaryStats = primaryStats;
        this.secondaryStats = secondaryStats;
        this.equipEffects = equipEffects != null ? equipEffects : new ArrayList<>();
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public int getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(int itemLevel) {
        this.itemLevel = itemLevel;
    }

    public PrimaryStatsDTO getPrimaryStats() {
        return primaryStats;
    }

    public void setPrimaryStats(PrimaryStatsDTO primaryStats) {
        this.primaryStats = primaryStats;
    }

    public SecondaryStatsDTO getSecondaryStats() {
        return secondaryStats;
    }

    public void setSecondaryStats(SecondaryStatsDTO secondaryStats) {
        this.secondaryStats = secondaryStats;
    }

    public List<ItemEffectDTO> getEquipEffects() {
        return equipEffects;
    }

    public void setEquipEffects(List<ItemEffectDTO> equipEffects) {
        this.equipEffects = equipEffects;
    }
}
