package toyproject.genshin.teybatguide.banner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import toyproject.genshin.teybatguide.banner.controller.dto.response.CharacterResourcesResponse;
import toyproject.genshin.teybatguide.banner.event.dto.BannerEventsDto;
import toyproject.genshin.teybatguide.banner.controller.dto.response.CharacterBannerResponse;
import toyproject.genshin.teybatguide.banner.controller.dto.response.WeaponBannerResponse;
import toyproject.genshin.teybatguide.banner.service.BannerService;
import toyproject.genshin.teybatguide.banner.value.BannerType;

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
    public List<CharacterResourcesResponse> getResourcesForBannerCharacter() {
        return bannerService.searchBannerCharacterResources();
    }

}
