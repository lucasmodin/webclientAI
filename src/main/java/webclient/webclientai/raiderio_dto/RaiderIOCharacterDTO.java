package webclient.webclientai.raiderio_dto;

import java.util.List;

public class RaiderIOCharacterDTO {
    private String name;
    private String realm;
    private String region;
    private MythicPlusScoreDTO mythicPlusScores;
    private MythicPlusRanksDTO mythicPlusRanks;
    private List<MythicPlusRunDTO> bestRuns;
    private List<MythicPlusRunDTO> recentRuns;


    public RaiderIOCharacterDTO() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public MythicPlusScoreDTO getMythicPlusScores() {
        return mythicPlusScores;
    }

    public void setMythicPlusScores(MythicPlusScoreDTO mythicPlusScores) {
        this.mythicPlusScores = mythicPlusScores;
    }

    public MythicPlusRanksDTO getMythicPlusRanks() {
        return mythicPlusRanks;
    }

    public void setMythicPlusRanks(MythicPlusRanksDTO mythicPlusRanks) {
        this.mythicPlusRanks = mythicPlusRanks;
    }

    public List<MythicPlusRunDTO> getBestRuns() {
        return bestRuns;
    }

    public void setBestRuns(List<MythicPlusRunDTO> bestRuns) {
        this.bestRuns = bestRuns;
    }

    public List<MythicPlusRunDTO> getRecentRuns() {
        return recentRuns;
    }

    public void setRecentRuns(List<MythicPlusRunDTO> recentRuns) {
        this.recentRuns = recentRuns;
    }
}
