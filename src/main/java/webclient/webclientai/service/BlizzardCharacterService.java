package webclient.webclientai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class BlizzardCharacterService {

    private final WebClient webClient;

    @Autowired
    public BlizzardCharacterService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://eu.api.blizzard.com")
                .build();
    }

    public Map<String, Object> getCharacters(String accessToken) {
        Map<String, Object> response = webClient.get()
                .uri("/profile/user/wow")
                .header("Authorization", "Bearer " + accessToken)
                .header("Battlenet-Namespace", "profile-eu")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        return response;

    }


    public String fetchCharacterImage(String realmSlug, String characterName, String accessToken) {

    try {
        Map<String, Object> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("profile", "wow", "character", realmSlug.toLowerCase(), characterName.toLowerCase(), "character-media")
                        .queryParam("namespace", "profile-eu")
                        .queryParam("locale", "en-US")
                        .build())
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        if (response != null && response.containsKey("assets")) {
            List<Map<String, Object>> assets = (List<Map<String, Object>>) response.get("assets");
            for (Map<String, Object> asset : assets) {
                if ("main".equals(asset.get("key"))) {
                    return asset.get("value").toString();
                }
            }
        }
    } catch(WebClientResponseException e) {
        //in the coincidence that by somehow something is wrong with blizzards rendering
        return e.getResponseBodyAsString();
    }
        return null;
    }



}
