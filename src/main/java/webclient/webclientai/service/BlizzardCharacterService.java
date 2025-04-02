package webclient.webclientai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
        return webClient.get()
                .uri("/profile/user/wow")
                .header("Authorization", "Bearer " + accessToken)
                .header("Battlenet-Namespace", "profile-eu")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

}
