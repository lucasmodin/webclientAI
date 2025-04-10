package webclient.webclientai.service;

import org.springframework.web.reactive.function.client.WebClient;
import webclient.webclientai.blizzard_dto.Item_dto.EquippedItemDTO;
import webclient.webclientai.raiderio_dto.MythicPlusRunDTO;
import webclient.webclientai.raiderio_dto.RaiderIOCharacterDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Top100GearFetcherService {

    private final WebClient webClient;

    public Top100GearFetcherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://raider.io")
                .build();
    }

    //this method uses to flux and CompletableFuture which means it runs async task parralel - and wait for them to all complete instead of waiting for 1 to complete and then doing the next
    public List<RaiderIOCharacterDTO> fetchTop100WithGear(String season, String region, String wowClass, String spec) {
        List<MythicPlusRunDTO> runs = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/mythic-plus/runs")
                        .queryParam("season", season)
                        .queryParam("region", region)
                        .queryParam("class", wowClass)
                        .queryParam("spec", spec)
                        .queryParam("limit", 100)
                        .build())
                .retrieve()
                .bodyToFlux(MythicPlusRunDTO.class)
                .collectList()
                .block();

        if (runs == null) return List.of();

        List<CompletableFuture<RaiderIOCharacterDTO>> futures = runs.stream()
                .map(run -> {
                    String name = run.getCharacter().getName();
                    String realm = run.getCharacter().getRealm();

                    return webClient.get()
                            .uri(uriBuilder -> uriBuilder
                                    .path("/api/v1/characters/profile")
                                    .queryParam("region", region)
                                    .queryParam("realm", realm)
                                    .queryParam("name", name)
                                    .queryParam("fields", "gear")
                                    .build())
                            .retrieve()
                            .bodyToMono(RaiderIOCharacterDTO.class)
                            .toFuture();
                })
                .collect(Collectors.toList());

        List<RaiderIOCharacterDTO> result = new ArrayList<>();
        for(CompletableFuture<RaiderIOCharacterDTO> future : futures) {
            try {
                RaiderIOCharacterDTO dto = future.get();
                if(dto != null) result.add(dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //TODO extract gear for the ai to use in prompts
    public List<List<EquippedItemDTO>> extractGear(List<RaiderIOCharacterDTO> characters) {
        return null;
    }









}
