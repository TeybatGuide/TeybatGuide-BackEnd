package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.controller.dto.CharacterListResponse;
import toyproject.genshin.teybatguide.service.CharactersService;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharactersController {

    private final CharactersService charactersService;

    @GetMapping
    public ResponseEntity<List<CharacterListResponse>> getCharacterList() {
        return ResponseEntity.ok(charactersService.findAndCreateCharacterList());
    }

}
