package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.base.ResponseData;
import toyproject.genshin.teybatguide.base.dto.PageDto;
import toyproject.genshin.teybatguide.base.PageResponseData;
import toyproject.genshin.teybatguide.controller.dto.characters.*;
import toyproject.genshin.teybatguide.controller.dto.characters.request.CharacterListRequest;
import toyproject.genshin.teybatguide.controller.dto.characters.request.CharacterWeaponSaveRequest;
import toyproject.genshin.teybatguide.controller.dto.characters.response.CharacterDetailsResponse;
import toyproject.genshin.teybatguide.controller.dto.characters.response.CharacterListResponse;
import toyproject.genshin.teybatguide.controller.dto.characters.response.CharacterWeaponResponse;
import toyproject.genshin.teybatguide.service.CharactersService;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharactersController {

    private final CharactersService charactersService;

    @GetMapping
    public PageResponseData<List<CharacterListResponse>> getCharacterList(
            @PageableDefault(size = 20) Pageable pageable,
            @ModelAttribute CharacterListRequest request
    ) {
        Page<CharacterListResponse> responses = charactersService.findAndCreateCharacterList(request, pageable);
        return PageResponseData.of(responses.toList(), PageDto.of(responses));
    }

    @GetMapping("/{character_id}")
    public ResponseData<CharacterDetailsResponse> getCharacterDetails(@PathVariable(name = "character_id") String characterId) {
        return ResponseData.of(charactersService.findAndBuildCharacterDetails(characterId));
    }

    @GetMapping("/{character_id}/weapons")
    public ResponseData<CharacterWeaponResponse> getCharacterDetailsForWeapon(@PathVariable(name = "character_id") String characterId) {
        return ResponseData.of(charactersService.findAndBuildCharacterWeapon(characterId));
    }

    @PostMapping("/weapons/add")
    public ResponseData<CharacterWeaponDto> save(@RequestBody CharacterWeaponSaveRequest request) {
        return ResponseData.of(charactersService.save(request));
    }

}
