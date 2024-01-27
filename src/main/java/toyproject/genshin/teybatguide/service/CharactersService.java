package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.controller.dto.CharacterDetailsResponse;
import toyproject.genshin.teybatguide.controller.dto.CharacterListResponse;
import toyproject.genshin.teybatguide.domain.Characters;
import toyproject.genshin.teybatguide.domain.Resources;
import toyproject.genshin.teybatguide.exception.TeybatException;
import toyproject.genshin.teybatguide.repository.CharactersRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CharactersService {

    private final CharactersRepository charactersRepository;

    public List<CharacterListResponse> findAndCreateCharacterList() {
        return charactersRepository.findAll()
                .stream()
                .map(CharacterListResponse::of)
                .toList();
    }

    public FileSystemResource findImage(String id) {

        return openFile(id);

    }

    public FileSystemResource openFile(String id) {
        try {
            String path = "/Users/ahnnayeong/img/character/" + id + "_1.webp";
            FileSystemResource resource = new FileSystemResource(path);

            if (!resource.exists()) {
                throw new TeybatException("이미지 파일 없음");
            }

            return resource;
        } catch (Exception e) {
            throw new TeybatException("이미지 없음");
        }
    }
}
