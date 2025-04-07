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
            String encodedRealm = URLEncoder.encode(realmSlug.toLowerCase(), StandardCharsets.UTF_8);
            String encodedName = URLEncoder.encode(characterName.toLowerCase(), StandardCharsets.UTF_8);

            Map<String, Object> response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .host("eu.api.blizzard.com")
                            .path("/profile/wow/character/" + encodedRealm + "/" + encodedName + "/character-media")
                            .queryParam("namespace", "profile-eu")
                            .queryParam("locale", "en_US")
                            .build())
                    .header("Authorization", "Bearer " + accessToken)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            if (response != null && response.containsKey("assets")) {
                List<Map<String, Object>> assets = (List<Map<String, Object>>) response.get("assets");
                for (Map<String, Object> asset : assets) {
                    if ("avatar".equals(asset.get("key"))) {
                        return asset.get("value").toString();
                    }
                }
            }
        } catch (WebClientResponseException e) {
            System.out.println("Image API error: " + e.getRawStatusCode() + " - " + e.getResponseBodyAsString());
        }

        System.out.println("her kommer ingenting");
        return null;
    }




}
