package toyproject.genshin.teybatguide.character.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;
import toyproject.genshin.teybatguide.character.resource.entity.CharacterAscend;
import toyproject.genshin.teybatguide.character.resource.repository.querydsl.CustomCharacterAscendRepository;

@GraphQlRepository
public interface CharacterAscendRepository extends JpaRepository<CharacterAscend, String>, CustomCharacterAscendRepository {
}
