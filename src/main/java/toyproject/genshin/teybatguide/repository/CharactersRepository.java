package toyproject.genshin.teybatguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.genshin.teybatguide.domain.Characters;
import toyproject.genshin.teybatguide.repository.querydsl.CustomCharacterRepository;

@Repository
public interface CharactersRepository extends JpaRepository<Characters, String>, CustomCharacterRepository {

}
