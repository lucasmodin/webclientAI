package webclient.webclientai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Base64;
import java.util.Map;

@Service
public class BlizzardTokenInspector {


    //this is a debugging class since im having trouble with the character image rendering
    //im suspecting it's a scoping issue, so this is to test the authorities i have with the token


    private final WebClient webClient;

    @Value("${blizzard.client.id}")
    private String clientId;

    @Value("${blizzard.client.secret}")
    private String clientSecret;

    public BlizzardTokenInspector(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public void inspectAccessToken(String accessToken) {
        try {
            String creds = clientId + ":" + clientSecret;
            String basic = Base64.getEncoder().encodeToString(creds.getBytes());

            Map<String, Object> response = webClient.post()
                    .uri("https://oauth.battle.net/oauth/check_token")
                    .header("Authorization", "Basic " + basic)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .bodyValue("token=" + accessToken)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            System.out.println("=== Token Info ===");
            if (response != null) {
                for (Map.Entry<String, Object> entry : response.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            } else {
                System.out.println("No token data returned.");
            }
        } catch (WebClientResponseException e) {
            System.out.println("Error while checking token: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        }
    }
}
