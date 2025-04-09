package webclient.webclientai.raiderio_dto;

public class MythicPlusScoreBySeasonDTO {
    private String season;
    private MythicPlusScoreDTO scores;

    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }

    public MythicPlusScoreDTO getScores() { return scores; }
    public void setScores(MythicPlusScoreDTO scores) { this.scores = scores; }
}
