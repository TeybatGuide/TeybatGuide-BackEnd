package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.controller.dto.CharacterListResponse;
import toyproject.genshin.teybatguide.service.CharactersService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    @GetMapping("/details")
    public ResponseEntity<Resource> getCharacterDetails() {

        Resource resource = charactersService.findImage("character_1ffe893ce78e4e688cfa553ea5689268");
        String contentType = "image/webp";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_ENCODING, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
