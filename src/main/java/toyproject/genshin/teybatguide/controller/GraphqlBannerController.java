package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import toyproject.genshin.teybatguide.controller.dto.MainCharacterResourcesResponse;
import toyproject.genshin.teybatguide.controller.dto.main.BannerEventsDto;
import toyproject.genshin.teybatguide.controller.dto.main.CharacterBannerResponse;
import toyproject.genshin.teybatguide.controller.dto.main.WeaponBannerResponse;
import toyproject.genshin.teybatguide.domain.value.BannerType;
import toyproject.genshin.teybatguide.service.BannerService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GraphqlBannerController {

    private final BannerService bannerService;

    @QueryMapping
    public CharacterBannerResponse getCharacterBanner() {
        return bannerService.searchCharacterBanner(BannerType.CHARACTER);
    }

    @QueryMapping
    public WeaponBannerResponse getWeaponBanner() {
        return bannerService.searchWeaponBanner(BannerType.WEAPON);
    }

    @QueryMapping
    public List<BannerEventsDto> getEvents() {
        return bannerService.searchEvents();
    }

    @QueryMapping
    public List<MainCharacterResourcesResponse> getResourcesForBannerCharacter() {
        return bannerService.searchBannerCharacterResources();
    }

}
