package toyproject.genshin.teybatguide.controller.dto.banner.response;

import lombok.Builder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.controller.dto.banner.WeaponBannerDto;
import toyproject.genshin.teybatguide.domain.WeaponBanner;
import toyproject.genshin.teybatguide.domain.value.BannerType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public record WeaponBannerResponse(
        LocalDateTime startDate, LocalDateTime endDate, String bannerType, List<WeaponBannerDto> weapons
) {

    @Contract("_, _ -> new")
    public static @NotNull WeaponBannerResponse of(@NotNull WeaponBanner banner, List<WeaponBannerDto> weapons) {
        return new WeaponBannerResponse(
                banner.getBannerStartDate(),
                banner.getBannerEndDate(),
                banner.getBannerType().getBannerTypeName(),
                weapons
        );
    }

    public static @NotNull WeaponBannerResponse of(@NotNull BannerType bannerType, @NotNull List<WeaponBanner> weapons) {
        WeaponBanner banner = weapons.get(0);

        List<WeaponBannerDto> weaponBannerDtoList = weapons.stream()
                .map(WeaponBannerDto::of)
                .toList();

        return new WeaponBannerResponse(
                banner.getBannerStartDate(),
                banner.getBannerEndDate(),
                bannerType.getBannerTypeName(),
                weaponBannerDtoList
        );
    }

    @Contract(" -> new")
    public static @NotNull WeaponBannerResponse empty() {
        return new WeaponBannerResponse(null, null, null, new ArrayList<>());
    }

}
