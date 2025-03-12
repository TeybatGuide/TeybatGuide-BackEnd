package toyproject.genshin.teybatguide.character.resource.repository.querydsl;

import toyproject.genshin.teybatguide.character.entity.Characters;
import toyproject.genshin.teybatguide.resource.entity.Resources;

import java.util.List;
import java.util.Map;

public interface CustomCharacterAscendRepository {

    Map<Characters, List<Resources>> findByCharacters(List<Characters> characters);
}
