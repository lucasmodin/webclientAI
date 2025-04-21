package webclient.webclientai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;
import webclient.webclientai.ai_dto.*;
import webclient.webclientai.blizzard_dto.Item_dto.EquippedItemDTO;
import webclient.webclientai.raiderio_dto.RaiderIOCharacterDTO;

import java.util.*;

@Service
public class MistralService {

    private final WebClient webClient;

    @Autowired
    public MistralService (WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1/chat/completions").build();

    }

    @Value("${openai.api.key}")
    private String openapikey;
    public Map<String,Object> promptMistral() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setModel("gpt-4o-mini");
        requestDTO.setTemperature(1.0);
        List<Message> lstMessages = new ArrayList<>(); //en liste af messages med roller
        lstMessages.add(new Message("system", "You are a helpful assistant and an expert in World of Warcraft (WoW)."));
        lstMessages.add(new Message("user", "Give me a list of the 10 best zones in wow according to you"));
        requestDTO.setMessages(lstMessages);

        ResponseDTO response = webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .headers(h -> h.setBearerAuth(openapikey))
                .bodyValue(requestDTO)
                .retrieve()
                .bodyToMono(ResponseDTO.class)
                .block();

        List<Choice> lst = response.getChoices();
        Usage usg = response.getUsage();
        Map<String, Object> map = new HashMap<>();
        map.put("Usage", usg);
        map.put("Choices", lst);
        return map; }



    public Map<String, Object> promptMistralWithCharacterContext(
            RaiderIOCharacterDTO character,
            List<EquippedItemDTO> gear,
            String userInput,
            PromptContext context
    ) {
        String systemPrompt = "You are a World of Warcraft PvE expert with up-to-date knowledge of Mythic+ content for The War Within – Season 2." +
                              " This week's dungeon pool: Operation: Floodgate, Cinderbrew Meadery, Darkflame Cleft, The Rookery, Priory of the Sacred Flame, The MOTHERLODE!!, Theater of Pain, Operation: Mechagon – Workshop." +
                              " Active affixes (April 16–22, 2025): Xal’atath’s Bargain: Devour – applies shields that must be healed or dispelled to avoid enemy healing; removal grants +2% max HP and +4% Crit for 30s. " +
                              "Tyrannical – bosses have +30% health and +15% damage. " +
                              "Fortified – non-boss mobs have +20% health and +30% damage. Use this context to provide expert-level advice and analysis.\n";
        String combinedPrompt = PromptBuilder.buildPromptWithContext(context, character, gear, userInput);

        //debugging
        System.out.println("=== Mistral Prompt Sent ===");
        System.out.println(combinedPrompt);
        System.out.println("===========================");

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setModel("gpt-4o-mini");
        requestDTO.setTemperature(1.0);


        List<Message> messages = new ArrayList<>();
        messages.add(new Message("system", systemPrompt));
        messages.add(new Message("user", combinedPrompt));
        requestDTO.setMessages(messages);

        ResponseDTO response = webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .headers(h -> h.setBearerAuth(openapikey))
                .bodyValue(requestDTO)
                .retrieve()
                .bodyToMono(ResponseDTO.class)
                .block();

        Map<String, Object> map = new HashMap<>();
        map.put("Choices", response.getChoices());
        map.put("Usage", response.getUsage());
        return map;
    }

    public PromptContext detectContextFromMessage(String userInput) {
        userInput = userInput.toLowerCase();
        if(userInput.contains("gear") || userInput.contains("ilvl")) return PromptContext.GEAR_ONLY;
        if(userInput.contains("mythic") || userInput.contains("rio") || userInput.contains("m+")) return PromptContext.MYTHIC_PLUS_ANALYSIS;
        return PromptContext.GENERAL_CHARACTER_OVERVIEW;
    }


}
