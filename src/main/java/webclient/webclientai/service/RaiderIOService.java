package webclient.webclientai.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import webclient.webclientai.raiderio_dto.RaiderIOCharacterDTO;

import java.util.List;

@Service
public class RaiderIOService {
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
                .baseUrl("https://raider.io/api/v1/characters/profile")
                .build();
    }

    public RaiderIOCharacterDTO fetchMythicPlusData(String region, String realm, String name) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("region", region)
                        .queryParam("realm", realm)
                        .queryParam("name", name)
                        .queryParam("fields", fields)
                        .build())
                .retrieve()
                .bodyToMono(RaiderIOCharacterDTO.class)
                .block();
    }
}
