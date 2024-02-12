package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.genshin.teybatguide.repository.CharacterBannerRepository;

@Service
@RequiredArgsConstructor
public class MainService {

    private final CharacterBannerRepository bannerRepository;

}
