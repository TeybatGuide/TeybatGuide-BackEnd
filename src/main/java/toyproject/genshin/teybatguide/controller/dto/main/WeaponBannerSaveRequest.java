package toyproject.genshin.teybatguide.controller.dto.main;

import java.time.LocalDateTime;

public record WeaponBannerSaveRequest(String weaponId, LocalDateTime startDate, LocalDateTime endDate) {
}
