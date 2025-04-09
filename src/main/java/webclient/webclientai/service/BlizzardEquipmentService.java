package webclient.webclientai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import webclient.webclientai.blizzard_dto.Item_dto.EquippedItemDTO;
import webclient.webclientai.blizzard_dto.Item_dto.ItemEffectDTO;
import webclient.webclientai.blizzard_dto.Item_dto.PrimaryStatsDTO;
import webclient.webclientai.blizzard_dto.Item_dto.SecondaryStatsDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BlizzardEquipmentService {

    private final WebClient webClient;

    private final ObjectMapper objectMapper;

    public BlizzardEquipmentService(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("https://eu.api.blizzard.com").build();
        this.objectMapper = new ObjectMapper();
    }

    public List<EquippedItemDTO> fetchEquippedItems(String realm, String characterName, String accessToken) {
        String url = String.format("/profile/wow/character/%s/%s/equipment",
                realm.toLowerCase(), characterName.toLowerCase());

            JsonNode response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(url)
                            .queryParam("namespace", "profile-eu")
                            .queryParam("locale", "en_US")
                            .build())
                    .header("Authorization", "Bearer " + accessToken)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            if (response == null || !response.has("equipped_items")) {
                return Collections.emptyList();
            }

            List<EquippedItemDTO> equippedItems = new ArrayList<>();
            for (JsonNode itemNode : response.get("equipped_items")) {
                EquippedItemDTO equippedItem = parseEquippedItem(itemNode, accessToken);
                equippedItems.add(equippedItem);
            }

            return equippedItems;

    }


    private EquippedItemDTO parseEquippedItem(JsonNode itemNode, String accessToken) {
        EquippedItemDTO dto = new EquippedItemDTO();
        dto.setItemId(itemNode.path("item").path("id").asInt());
        dto.setName(itemNode.path("name").asText());
        dto.setSlot(itemNode.path("slot").path("name").asText());
        dto.setQuality(itemNode.path("quality").path("name").asText());
        dto.setItemLevel(itemNode.path("level").path("value").asInt());
        //since the first response we get is the call to the render api, we need to call that, and then call the render api
        String mediaApiUrl = itemNode.path("media").path("key").path("href").asText();
        String imageUrl = fetchIconFromMediaUrl(mediaApiUrl, accessToken);
        dto.setMediaUrl(imageUrl);

        PrimaryStatsDTO primary = new PrimaryStatsDTO();
        SecondaryStatsDTO secondary = new SecondaryStatsDTO();
        JsonNode statsNode = itemNode.path("stats");

        populateStatsHelper(statsNode, primary, secondary);

        dto.setPrimaryStats(primary);
        dto.setSecondaryStats(secondary);
        dto.setEquipEffects(parseItemEffects(itemNode));
        return dto;
    }

    //helper method to map & populate stats
    private void populateStatsHelper(JsonNode statsNode, PrimaryStatsDTO primary, SecondaryStatsDTO secondary) {
        if (statsNode != null && statsNode.isArray()) {
            for (JsonNode statNode : statsNode) {
                String statType = statNode.get("type").get("type").asText();
                int value = statNode.get("value").asInt();
                //some stats are negated aka. not relevant for a character, and should be skipped
                boolean isNegated = statNode.has("is_negated") && statNode.get("is_negated").asBoolean();
                //skip and continue
                if(isNegated) continue;

                switch (statType) {
                    case "STRENGTH" -> primary.setStrength(value);
                    case "AGILITY" -> primary.setAgility(value);
                    case "INTELLECT" -> primary.setIntellect(value);
                    case "STAMINA" -> primary.setStamina(value);
                    case "CRIT_RATING" ->secondary.setCrit(value);
                    case "HASTE_RATING" ->secondary.setHaste(value);
                    case "MASTERY_RATING" ->secondary.setMastery(value);
                    case "VERSATILITY" -> secondary.setVersatility(value);
                    case "LEECH" -> secondary.setLeech(value);
                    case "AVOIDANCE" -> secondary.setAvoidance(value);
                    case "SPEED" -> secondary.setSpeed(value);
                }
            }
        }
    }

    private List<ItemEffectDTO> parseItemEffects(JsonNode itemNode) {
        List<ItemEffectDTO> effects = new ArrayList<>();

        JsonNode spellsNode = itemNode.get("spells");
        if(spellsNode != null && spellsNode.isArray()) {
            for (JsonNode spellNode : spellsNode) {
                String spellName = spellNode.path("spell").path("name").asText(null); //to avoid null-pointer as not all items have on-equip effect
                String description = spellNode.path("description").asText(null);

                if (spellName != null || description != null) {
                    ItemEffectDTO itemEffect = new ItemEffectDTO();
                    itemEffect.setSpellName(spellName);
                    itemEffect.setDescription(description);
                    effects.add(itemEffect);
                }

            }
        }
        return effects;
    }

    //render api helper
    private String fetchIconFromMediaUrl(String mediaUrl, String accessToken) {
        try {
            JsonNode mediaResponse = webClient.get()
                    .uri(mediaUrl)
                    .header("Authorization", "Bearer " + accessToken)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            for(JsonNode asset : mediaResponse.path("assets")) {
                if("icon".equals(asset.path("key").asText())) {
                    return asset.path("value").asText();
                }
            }
        } catch(WebClientResponseException e) {
            System.err.println("Failed to fetch media icon: " + e.getStatusCode() + " -> " + mediaUrl);
        }

        return ""; //this is a fallback
    }




    //TODO parse set-bonus as they are not a part of "spells" on-equip effect
    //private List<ItemEffectDTO> parseSetBonuses(JsonNode itemNode) {}

}
