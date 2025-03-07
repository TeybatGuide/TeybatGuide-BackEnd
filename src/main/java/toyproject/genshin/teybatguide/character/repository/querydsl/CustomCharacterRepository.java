package toyproject.genshin.teybatguide.character.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toyproject.genshin.teybatguide.character.controller.dto.request.CharacterListRequest;
import toyproject.genshin.teybatguide.character.entity.Characters;

public interface CustomCharacterRepository {

    Page<Characters> findByStarsAndCountryAndElementAndWeaponType(CharacterListRequest request, Pageable pageable);

}
