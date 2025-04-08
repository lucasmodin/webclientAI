package webclient.webclientai.blizzard_dto.Item_dto;

public class SecondaryStatsDTO {
    private Integer crit;
    private Integer haste;
    private Integer mastery;
    private Integer versatility;
    private Integer leech;
    private Integer avoidance;
    private Integer speed;

    public SecondaryStatsDTO() {

    }

    public SecondaryStatsDTO(Integer crit, Integer haste, Integer mastery, Integer versatility, Integer leech, Integer avoidance, Integer speed) {
        this.crit = crit;
        this.haste = haste;
        this.mastery = mastery;
        this.versatility = versatility;
        this.leech = leech;
        this.avoidance = avoidance;
        this.speed = speed;
    }

    public Integer getCrit() {
        return crit;
    }

    public void setCrit(Integer crit) {
        this.crit = crit;
    }

    public Integer getHaste() {
        return haste;
    }

    public void setHaste(Integer haste) {
        this.haste = haste;
    }

    public Integer getMastery() {
        return mastery;
    }

    public void setMastery(Integer mastery) {
        this.mastery = mastery;
    }

    public Integer getVersatility() {
        return versatility;
    }

    public void setVersatility(Integer versatility) {
        this.versatility = versatility;
    }

    public Integer getLeech() {
        return leech;
    }

    public void setLeech(Integer leech) {
        this.leech = leech;
    }

    public Integer getAvoidance() {
        return avoidance;
    }

    public void setAvoidance(Integer avoidance) {
        this.avoidance = avoidance;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
}
