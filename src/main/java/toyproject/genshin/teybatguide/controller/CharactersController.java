package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.controller.dto.SaveCharacterRequest;
import toyproject.genshin.teybatguide.service.CharactersService;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharactersController {

    private final CharactersService charactersService;

}
