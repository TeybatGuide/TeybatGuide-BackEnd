package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.controller.dto.characters.CharacterDetailsResponse;
import toyproject.genshin.teybatguide.controller.dto.characters.CharacterListResponse;
import toyproject.genshin.teybatguide.controller.dto.characters.CharacterWeaponResponse;
import toyproject.genshin.teybatguide.controller.dto.characters.CharacterWeaponSaveRequest;
import toyproject.genshin.teybatguide.service.CharactersService;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharactersController {

    private final CharactersService charactersService;

    @PostMapping
    public ResponseEntity<List<CharacterListResponse>> getCharacterList() {
        return ResponseEntity.ok(charactersService.findAndCreateCharacterList());
    }

    @GetMapping("/{character_id}")
    public ResponseEntity<CharacterDetailsResponse> getCharacterDetails(@PathVariable(name = "character_id") String characterId) {
        return ResponseEntity.ok(charactersService.findAndBuildCharacterDetails(characterId));
    }

    @GetMapping("/{character_id}/weapons")
    public ResponseEntity<CharacterWeaponResponse> getCharacterDetailsForWeapon(@PathVariable(name = "character_id") String characterId) {
        return ResponseEntity.ok(charactersService.findAndBuildCharacterWeapon(characterId));
    }

    @PostMapping("/weapons/add")
    public ResponseEntity<String> save(@RequestBody CharacterWeaponSaveRequest request) {
        return ResponseEntity.ok(charactersService.save(request));
    }

}
