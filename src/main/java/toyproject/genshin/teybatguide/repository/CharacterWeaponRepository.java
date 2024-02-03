package toyproject.genshin.teybatguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.genshin.teybatguide.domain.CharacterWeapon;
import toyproject.genshin.teybatguide.domain.Characters;

import java.util.Optional;

@Repository
public interface CharacterWeaponRepository extends JpaRepository<CharacterWeapon, String> {

    Optional<CharacterWeapon> findByCharacters(Characters characters);

}
