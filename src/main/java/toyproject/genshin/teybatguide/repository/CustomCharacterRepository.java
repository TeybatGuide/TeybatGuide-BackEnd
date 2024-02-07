package toyproject.genshin.teybatguide.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toyproject.genshin.teybatguide.controller.dto.characters.CharacterListRequest;
import toyproject.genshin.teybatguide.domain.Characters;

public interface CustomCharacterRepository {

    Page<Characters> findByStarsAndCountryAndElementAndWeaponType(CharacterListRequest request, Pageable pageable);

}
