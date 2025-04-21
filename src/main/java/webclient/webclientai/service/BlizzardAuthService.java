package webclient.webclientai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import webclient.webclientai.blizzard_dto.AccountDTO;

import java.util.ArrayList;
import java.util.Map;

@Service
public class BlizzardAuthService {

    @Value("${blizzard.client.id}")
    private String clientId;

    @Value("${blizzard.client.secret}")
    private String clientSecret;

    @Value("${blizzard.redirect.uri}")
    private String redirectUri;

    @Value("${blizzard.token.uri}")
    private String tokenUri;

    private final WebClient webClient;

    private final String accountInfo = "https://oauth.battle.net/oauth/userinfo";

    @Autowired
    public BlizzardAuthService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    public String exchangeCodeForToken(String code) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("code", code);
        formData.add("redirect_uri", redirectUri);


        //form to blizzards API - these are required by blizzard to retrieve the access token
        Map<String, Object> response = webClient.post()
                .uri(tokenUri)
                .headers(h -> h.setBasicAuth(clientId, clientSecret))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        return (String) response.get("access_token");
    }

    public AccountDTO getBlizzardAccount(String accessToken) {
        Map<String, Object> accountRaw = webClient.get()
                .uri(accountInfo)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        long accountId = ((Number) accountRaw.get("id")).longValue();
        String battletag =(String) accountRaw.get("battletag");
        return new AccountDTO(accountId, battletag, new ArrayList<>());
    }




}
