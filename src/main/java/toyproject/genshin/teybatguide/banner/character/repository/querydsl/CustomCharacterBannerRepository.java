package toyproject.genshin.teybatguide.banner.character.repository.querydsl;

import toyproject.genshin.teybatguide.banner.character.entity.CharacterBanner;
import toyproject.genshin.teybatguide.character.entity.Characters;
import toyproject.genshin.teybatguide.banner.value.BannerType;

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
