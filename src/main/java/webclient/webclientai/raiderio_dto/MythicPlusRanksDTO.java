package webclient.webclientai.raiderio_dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MythicPlusRanksDTO {
    private RankData overall;

    @JsonProperty("class")
    private RankData classRanks;

    @JsonProperty("class_dps")
    private RankData classDps;

    @JsonProperty("class_healer")
    private RankData classHealer;

    @JsonProperty("class_tank")
    private RankData classTank;

    public RankData getOverall() { return overall; }
    public void setOverall(RankData overall) { this.overall = overall; }

    public RankData getClassRanks() { return classRanks; }
    public void setClassRanks(RankData classRanks) { this.classRanks = classRanks; }

    public RankData getClassDps() { return classDps; }
    public void setClassDps(RankData classDps) { this.classDps = classDps; }

    public RankData getClassHealer() { return classHealer; }
    public void setClassHealer(RankData classHealer) { this.classHealer = classHealer; }

    public RankData getClassTank() { return classTank; }
    public void setClassTank(RankData classTank) { this.classTank = classTank; }

    public static class RankData {
        private int world;
        private int region;
        private int realm;

        public int getWorld() { return world; }
        public void setWorld(int world) { this.world = world; }

        public int getRegion() { return region; }
        public void setRegion(int region) { this.region = region; }

        public int getRealm() { return realm; }
        public void setRealm(int realm) { this.realm = realm; }
    }
}
