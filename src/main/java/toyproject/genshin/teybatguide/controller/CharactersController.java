package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.controller.dto.base.PageDto;
import toyproject.genshin.teybatguide.controller.dto.base.PageResponseData;
import toyproject.genshin.teybatguide.controller.dto.characters.*;
import toyproject.genshin.teybatguide.service.CharactersService;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharactersController {

    private final CharactersService charactersService;

    @PostMapping
    public PageResponseData<List<CharacterListResponse>> getCharacterList(
            @PageableDefault(size = 20) Pageable pageable,
            @RequestBody CharacterListRequest request
    ) {
        Page<CharacterListResponse> responses = charactersService.findAndCreateCharacterList(request, pageable);
        return PageResponseData.of(responses.toList(), PageDto.of(responses));
    }

    @GetMapping("/{character_id}")
    public ResponseEntity<CharacterDetailsResponse> getCharacterDetails(@PathVariable(name = "character_id") String characterId) {
        return ResponseEntity.ok(charactersService.findAndBuildCharacterDetails(characterId));
    }

    @GetMapping("/{character_id}/weapons")
    public ResponseEntity<CharacterWeaponResponse> getCharacterDetailsForWeapon(@PathVariable(name = "character_id") String characterId) {
        return ResponseEntity.ok(charactersService.findAndBuildCharacterWeapon(characterId));
    }

//    @PostMapping("/weapons/add")
//    public ResponseEntity<String> save(@RequestBody CharacterWeaponSaveRequest request) {
//        return ResponseEntity.ok(charactersService.save(request));
//    }

}
