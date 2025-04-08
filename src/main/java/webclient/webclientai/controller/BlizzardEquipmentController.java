package webclient.webclientai.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webclient.webclientai.blizzard_dto.Item_dto.EquippedItemDTO;
import webclient.webclientai.service.BlizzardEquipmentService;

import java.util.List;

@RestController
public class BlizzardEquipmentController {

    private final BlizzardEquipmentService equipmentService;

    public BlizzardEquipmentController(BlizzardEquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }


    @GetMapping("/{realm}/{characterName}")
    public ResponseEntity<List<EquippedItemDTO>> getEquippedItems(
            @PathVariable String realm,
            @PathVariable String characterName,
            HttpServletRequest request) {

        String access_token = null;
        if(request.getCookies() != null) {
            for(Cookie cookie : request.getCookies()) {
                if("access_token".equals(cookie.getName())) {
                    access_token = cookie.getValue();
                    break;
                }
            }
        }

        if (access_token == null) {
            throw new RuntimeException("No access token(cookie) found");
        }


        List<EquippedItemDTO> items = equipmentService.fetchEquippedItems(realm, characterName, access_token);
        return ResponseEntity.ok(items);

    }

}
