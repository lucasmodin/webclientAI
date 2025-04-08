package webclient.webclientai.blizzard_dto;

public class CharacterDTO {
    private String name;
    private int level;
    private String faction;
    private String race;
    private String characterClass;
    private String realm;
    private String realmSlug;
    private String protectedHref; //for API calls
    private String characterMediaHref; //for implementation of character photos

    public CharacterDTO(String name, int level, String faction, String race, String characterClass, String realm, String realmSlug, String protectedHref, String characterMediaHref) {
        this.name = name;
        this.level = level;
        this.faction = faction;
        this.race = race;
        this.characterClass = characterClass;
        this.realm = realm;
        this.realmSlug = realmSlug;
        this.protectedHref = protectedHref;
        this.characterMediaHref = characterMediaHref;
    }

    public CharacterDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getProtectedHref() {
        return protectedHref;
    }

    public void setProtectedHref(String protectedHref) {
        this.protectedHref = protectedHref;
    }

    public String getCharacterMediaHref() {
        return characterMediaHref;
    }

    public void setCharacterMediaHref(String characterMediaHref) {
        this.characterMediaHref = characterMediaHref;
    }

    public String getRealmSlug() {
        return realmSlug;
    }

    public void setRealmSlug(String realmSlug) {
        this.realmSlug = realmSlug;
    }
}
