package toyproject.genshin.teybatguide.character.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.genshin.teybatguide.character.entity.Characters;
import toyproject.genshin.teybatguide.character.repository.querydsl.CustomCharacterRepository;

@Repository
public interface CharactersRepository extends JpaRepository<Characters, String>, CustomCharacterRepository {

}
