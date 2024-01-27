package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.genshin.teybatguide.controller.dto.CharacterListResponse;
import toyproject.genshin.teybatguide.domain.Characters;
import toyproject.genshin.teybatguide.repository.CharactersRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharactersService {

    private final CharactersRepository charactersRepository;

    public List<CharacterListResponse> findAndCreateCharacterList() {
        return charactersRepository.findAll()
                .stream()
                .map(CharacterListResponse::of)
                .toList();
    }
}
