package toyproject.genshin.teybatguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.genshin.teybatguide.domain.CharacterSpecifications;
import toyproject.genshin.teybatguide.domain.Characters;

import java.util.Optional;

@Repository
public interface CharacterSpecificationsRepository extends JpaRepository<CharacterSpecifications, String> {

    Optional<CharacterSpecifications> findByCharacters(Characters characters);

}
