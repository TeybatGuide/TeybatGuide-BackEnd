package toyproject.genshin.teybatguide.repository.querydsl;

import toyproject.genshin.teybatguide.domain.Characters;
import toyproject.genshin.teybatguide.domain.Resources;

import java.util.List;
import java.util.Map;

public interface CustomCharacterAscendRepository {

    Map<Characters, List<Resources>> findByCharacters(List<Characters> characters);
}
