package toyproject.genshin.teybatguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;
import org.springframework.stereotype.Repository;
import toyproject.genshin.teybatguide.domain.CharacterAscend;
import toyproject.genshin.teybatguide.repository.querydsl.CustomCharacterAscendRepository;

@GraphQlRepository
public interface CharacterAscendRepository extends JpaRepository<CharacterAscend, String>, CustomCharacterAscendRepository {
}
