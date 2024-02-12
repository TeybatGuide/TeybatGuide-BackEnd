package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.controller.dto.main.WeaponBannerSaveRequest;
import toyproject.genshin.teybatguide.domain.value.Domain;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "weapon_banner")
public class WeaponBanner extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "weapon_id", referencedColumnName = "id")
    private Weapon weapon;

    private LocalDateTime bannerStartDate;

    private LocalDateTime bannerEndDate;

    protected WeaponBanner() {
        super(Domain.WEAPON_BANNER);
    }

    @Builder
    public WeaponBanner(Weapon weapon, LocalDateTime bannerStartDate, LocalDateTime bannerEndDate) {
        this();
        this.weapon = weapon;
        this.bannerStartDate = bannerStartDate;
        this.bannerEndDate = bannerEndDate;
    }

    public static WeaponBanner of(Weapon weapon, WeaponBannerSaveRequest request) {
        return WeaponBanner.builder()
                .weapon(weapon)
                .bannerStartDate(request.startDate())
                .bannerEndDate(request.endDate())
                .build();
    }
}
