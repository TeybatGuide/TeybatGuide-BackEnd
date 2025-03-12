package toyproject.genshin.teybatguide.character.specifications.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.genshin.teybatguide.character.entity.Characters;
import toyproject.genshin.teybatguide.character.specifications.entity.CharacterSpecifications;

import java.util.Optional;

@Repository
public interface CharacterSpecificationsRepository extends JpaRepository<CharacterSpecifications, String> {

    Optional<CharacterSpecifications> findByCharacters(Characters characters);

}
