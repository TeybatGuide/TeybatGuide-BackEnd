package toyproject.genshin.teybatguide.repository.querydsl;

import toyproject.genshin.teybatguide.domain.CharacterBanner;
import toyproject.genshin.teybatguide.domain.Characters;
import toyproject.genshin.teybatguide.domain.value.BannerType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomCharacterBannerRepository {


    Map<BannerType, List<CharacterBanner>> findByDateTimeBetweenGroupBy(LocalDateTime localDateTime);

    List<CharacterBanner> findByDateTimeBetween(LocalDateTime localDateTime, BannerType bannerType);

    List<Characters> findCharactersByDateTimeBetween(LocalDateTime localDateTime);

    Optional<Characters> findCharactersById(String id);

}
