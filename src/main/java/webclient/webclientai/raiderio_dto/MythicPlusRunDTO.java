package webclient.webclientai.raiderio_dto;

import java.util.List;

public class MythicPlusRunDTO {
    private String dungeon;
    private int mythic_level;
    private String completed_at;
    private double score;
    private List<AffixDTO> affixes;
    private String url;
    private long clear_time_ms;
    private long par_time_ms;

    public String getDungeon() { return dungeon; }
    public void setDungeon(String dungeon) { this.dungeon = dungeon; }

    public int getMythic_level() { return mythic_level; }
    public void setMythic_level(int mythic_level) { this.mythic_level = mythic_level; }

    public String getCompleted_at() { return completed_at; }
    public void setCompleted_at(String completed_at) { this.completed_at = completed_at; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    public List<AffixDTO> getAffixes() { return affixes; }
    public void setAffixes(List<AffixDTO> affixes) { this.affixes = affixes; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public long getClear_time_ms() { return clear_time_ms; }
    public void setClear_time_ms(long clear_time_ms) { this.clear_time_ms = clear_time_ms; }

    public long getPar_time_ms() { return par_time_ms; }
    public void setPar_time_ms(long par_time_ms) { this.par_time_ms = par_time_ms; }

    public boolean isTimed() { return clear_time_ms > 0 && par_time_ms > 0 && clear_time_ms <= par_time_ms; }
}
