package webclient.webclientai.raiderio_dto;

import java.util.List;

public class RaiderIOCharacterDTO {
    private String name;
    private String realm;
    private String region;
    private List<MythicPlusScoreBySeasonDTO> mythic_plus_scores_by_season;
    private MythicPlusRanksDTO mythic_plus_ranks;
    private List<MythicPlusRunDTO> mythic_plus_best_runs;
    private List<MythicPlusRunDTO> mythic_plus_recent_runs;


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRealm() { return realm; }
    public void setRealm(String realm) { this.realm = realm; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public List<MythicPlusScoreBySeasonDTO> getMythic_plus_scores_by_season() { return mythic_plus_scores_by_season; }
    public void setMythic_plus_scores_by_season(List<MythicPlusScoreBySeasonDTO> scores) { this.mythic_plus_scores_by_season = scores; }

    public MythicPlusRanksDTO getMythic_plus_ranks() { return mythic_plus_ranks; }
    public void setMythic_plus_ranks(MythicPlusRanksDTO ranks) { this.mythic_plus_ranks = ranks; }

    public List<MythicPlusRunDTO> getMythic_plus_best_runs() { return mythic_plus_best_runs; }
    public void setMythic_plus_best_runs(List<MythicPlusRunDTO> bestRuns) { this.mythic_plus_best_runs = bestRuns; }

    public List<MythicPlusRunDTO> getMythic_plus_recent_runs() { return mythic_plus_recent_runs; }
    public void setMythic_plus_recent_runs(List<MythicPlusRunDTO> recentRuns) { this.mythic_plus_recent_runs = recentRuns; }
}

