package webclient.webclientai.blizzard_dto;

import java.util.List;

public class AccountDTO {

    private long accountId;
    private String battletag;
    private List<CharacterDTO> characters;

    public AccountDTO(long accountId, String battletag, List<CharacterDTO> characters) {
        this.accountId = accountId;
        this.battletag = battletag;
        this.characters = characters;
    }

    public AccountDTO() {

    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public List<CharacterDTO> getCharacters() {
        return characters;
    }

    public void setCharacters(List<CharacterDTO> characters) {
        this.characters = characters;
    }

    public String getBattletag() {
        return battletag;
    }

    public void setBattletag(String battletag) {
        this.battletag = battletag;
    }
}
