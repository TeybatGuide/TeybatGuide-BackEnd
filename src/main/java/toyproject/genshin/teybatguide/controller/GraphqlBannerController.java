package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import toyproject.genshin.teybatguide.controller.dto.MainCharacterResourcesResponse;
import toyproject.genshin.teybatguide.controller.dto.main.BannerEventsDto;
import toyproject.genshin.teybatguide.controller.dto.main.CharacterBannerResponse;
import toyproject.genshin.teybatguide.controller.dto.main.MainResourcesResponse;
import toyproject.genshin.teybatguide.controller.dto.main.WeaponBannerResponse;
import toyproject.genshin.teybatguide.domain.value.BannerType;
import toyproject.genshin.teybatguide.domain.value.SortDirection;
import toyproject.genshin.teybatguide.service.MainService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GraphqlBannerController {

    private final MainService mainService;

    @QueryMapping
    public CharacterBannerResponse getCharacterBanner() {
        return mainService.searchCharacterBanner(BannerType.CHARACTER);
    }

    @QueryMapping
    public WeaponBannerResponse getWeaponBanner() {
        return mainService.searchWeaponBanner(BannerType.WEAPON);
    }

    @QueryMapping
    public List<BannerEventsDto> getEvents() {
        return mainService.searchEvents();
    }

    @QueryMapping
    public List<MainResourcesResponse> getResourcesToday(
            @Argument int limit,
            @Argument int offset,
            @Argument String sortAttribute,
            @Argument String sortDirection
    ) {
        Sort sort = Sort.by(sortAttribute);
        sort = isSortDirectionAscending(sortDirection) ? sort.ascending() : sort.descending();

        return mainService.searchResources(PageRequest.of(offset, limit, sort)).wrapper();
    }

    @QueryMapping
    public List<MainCharacterResourcesResponse> getResourcesForBannerCharacter() {
        return mainService.searchBannerCharacterResources();
    }

    private boolean isSortDirectionAscending(String sortDirection) {
        return sortDirection.toLowerCase().equals(SortDirection.ASCENDING.toString());
    }

}
