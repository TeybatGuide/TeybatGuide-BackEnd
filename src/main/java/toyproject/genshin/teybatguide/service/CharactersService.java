package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.genshin.teybatguide.repository.CharactersRepository;

@Service
@RequiredArgsConstructor
public class CharactersService {

    private final CharactersRepository charactersRepository;
}
