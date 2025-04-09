package webclient.webclientai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import webclient.webclientai.raiderio_dto.RaiderIOCharacterDTO;

import java.util.List;

@Service
public class RaiderIOService {

    @Value("${raiderio.client.secret}")
    private String secret;


    //raider.io requires the fields to be comma-seperated
    private String fields = String.join(",", List.of(
            "mythic_plus_scores_by_season:current",
            "mythic_plus_ranks",
            "mythic_plus_best_runs",
            "mythic_plus_recent_runs"
    ));



    private final WebClient webClient;

    public RaiderIOService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://raider.io")
                .build();
    }

    public RaiderIOCharacterDTO fetchMythicPlusData(String region, String realm, String name) {
        System.out.println("Calling RaiderIO with: region=" + region + ", realm=" + realm + ", name=" + name);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/characters/profile")
                        .queryParam("region", region)
                        .queryParam("realm", realm)
                        .queryParam("name", name)
                        .queryParam("access_key", secret)
                        .queryParam("fields", fields)
                        .build())
                .retrieve()
                .bodyToMono(RaiderIOCharacterDTO.class)
                .block();
    }
}
