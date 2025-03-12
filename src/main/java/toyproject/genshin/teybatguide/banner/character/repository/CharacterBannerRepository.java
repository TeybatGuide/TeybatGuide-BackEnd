package toyproject.genshin.teybatguide.banner.character.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;
import toyproject.genshin.teybatguide.banner.character.entity.CharacterBanner;
import toyproject.genshin.teybatguide.banner.character.repository.querydsl.CustomCharacterBannerRepository;

@GraphQlRepository
public interface CharacterBannerRepository extends JpaRepository<CharacterBanner, String>, CustomCharacterBannerRepository {
}
