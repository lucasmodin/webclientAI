package webclient.webclientai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webclient.webclientai.blizzard_dto.AccountDTO;
import webclient.webclientai.blizzard_dto.CharacterDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BlizzardService {

    @Autowired
    private BlizzardAuthService blizzardAuthService;

    @Autowired
    private BlizzardCharacterService blizzardCharacterService;

    public AccountDTO buildFullAccount(String accessToken) {
        AccountDTO account = blizzardAuthService.getBlizzardAccount(accessToken);

        Map<String, Object> characterData = blizzardCharacterService.getCharacters(accessToken);
        List<CharacterDTO> characters = extractCharacterList(characterData);

        account.setCharacters(characters);
        return account;
    }

    private List<CharacterDTO> extractCharacterList(Map<String, Object> characterData) {
        List<CharacterDTO> characters = new ArrayList<>();

        List<Map<String, Object>> accounts =(List<Map<String, Object>>) characterData.get("wow_accounts");
        if(accounts == null) return characters;

        for (Map<String, Object> account : accounts) {
            List<Map<String, Object>> chars = (List<Map<String, Object>>) account.get("characters");
            if(chars == null) continue;

            for (Map<String, Object> charMap : chars) {
                String name = (String) charMap.get("name");
                int level = (int) charMap.get("level");

                String faction = (String) ((Map<String, Object>) charMap.get("faction")).get("type");
                String race = (String) ((Map<String, Object>) ((Map<String, Object>) charMap.get("playable_race")).get("name")).get("en_US");
                String characterClass = (String) ((Map<String, Object>) ((Map<String, Object>) charMap.get("playable_class")).get("name")).get("en_US");
                String realm = (String) ((Map<String, Object>) ((Map<String, Object>) charMap.get("realm")).get("name")).get("en_US");

                String protectedHref = (String) ((Map<String, Object>) charMap.get("protected_character")).get("href");
                String mediaHref = (String) ((Map<String, Object>) charMap.get("character")).get("href");

                characters.add(new CharacterDTO(name, level, faction, race, characterClass, realm, protectedHref, mediaHref));
            }
        }
        return characters;
    }


}
