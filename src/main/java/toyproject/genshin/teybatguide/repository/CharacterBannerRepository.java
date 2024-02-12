package toyproject.genshin.teybatguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.genshin.teybatguide.domain.CharacterBanner;

@Repository
public interface CharacterBannerRepository extends JpaRepository<CharacterBanner, String> {


}
