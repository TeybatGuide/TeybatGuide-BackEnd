package toyproject.genshin.teybatguide.repository.querydsl;

import toyproject.genshin.teybatguide.domain.CharacterBanner;
import toyproject.genshin.teybatguide.domain.Characters;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CustomCharacterBannerRepository {

    List<CharacterBanner> findByDateTimeBetween(LocalDateTime localDateTime);

    Optional<Characters> findCharactersById(String id);

}
