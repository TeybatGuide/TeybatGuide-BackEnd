package toyproject.genshin.teybatguide.character.characterWeapon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.genshin.teybatguide.character.characterWeapon.entity.CharacterWeapon;
import toyproject.genshin.teybatguide.character.entity.Characters;

import java.util.List;

@Repository
public interface CharacterWeaponRepository extends JpaRepository<CharacterWeapon, String> {

    List<CharacterWeapon> findByCharacters(Characters characters);

}
