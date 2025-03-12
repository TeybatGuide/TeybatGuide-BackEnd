package toyproject.genshin.teybatguide.banner.controller.dto.request;

import toyproject.genshin.teybatguide.banner.value.BannerType;

import java.time.LocalDateTime;

public record CharacterBannerSaveRequest(
        String characterId, BannerType bannerType, LocalDateTime startDate, LocalDateTime endDate
) { }
