package toyproject.genshin.teybatguide.controller.dto.banner.request;

import toyproject.genshin.teybatguide.domain.value.BannerType;

import java.time.LocalDateTime;

public record WeaponBannerSaveRequest(
        String weaponId, BannerType bannerType, LocalDateTime startDate, LocalDateTime endDate
) { }
