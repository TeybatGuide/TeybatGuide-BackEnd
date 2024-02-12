package toyproject.genshin.teybatguide.repository.querydsl;

import toyproject.genshin.teybatguide.domain.CharacterBanner;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomCharacterBannerRepository {

    List<CharacterBanner> findByDateTimeBetween(LocalDateTime localDateTime);
}
