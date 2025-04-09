package webclient.webclientai.raiderio_dto;

public class MythicPlusRunDTO {

    private String dungeon;
    private int mythicLevel;
    private String affixes;
    private String completedAt;
    private boolean isTimed;
    private double score;

    public MythicPlusRunDTO() {}

    public String getDungeon() {
        return dungeon;
    }

    public void setDungeon(String dungeon) {
        this.dungeon = dungeon;
    }

    public int getMythicLevel() {
        return mythicLevel;
    }

    public void setMythicLevel(int mythicLevel) {
        this.mythicLevel = mythicLevel;
    }

    public String getAffixes() {
        return affixes;
    }

    public void setAffixes(String affixes) {
        this.affixes = affixes;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }

    public boolean isTimed() {
        return isTimed;
    }

    public void setTimed(boolean timed) {
        isTimed = timed;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
