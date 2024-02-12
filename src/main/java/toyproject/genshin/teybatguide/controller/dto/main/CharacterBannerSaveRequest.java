package toyproject.genshin.teybatguide.controller.dto.main;

import java.time.LocalDateTime;

public record CharacterBannerSaveRequest(String characterId, LocalDateTime startDate, LocalDateTime endDate) {
}
