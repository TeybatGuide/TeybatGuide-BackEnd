package toyproject.genshin.teybatguide.controller.dto.main;

import toyproject.genshin.teybatguide.domain.value.BannerType;

import java.time.LocalDateTime;

public record CharacterBannerSaveRequest(String characterId, BannerType bannerType, LocalDateTime startDate, LocalDateTime endDate) {
}
