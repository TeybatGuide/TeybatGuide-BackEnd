package toyproject.genshin.teybatguide.banner.weapon.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.base.BaseEntity;
import toyproject.genshin.teybatguide.banner.controller.dto.request.WeaponBannerSaveRequest;
import toyproject.genshin.teybatguide.banner.value.BannerType;
import toyproject.genshin.teybatguide.base.value.Domain;
import toyproject.genshin.teybatguide.weapon.entity.Weapon;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "weapon_banner")
public class WeaponBanner extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "weapon_id", referencedColumnName = "id")
    private Weapon weapon;

    @Enumerated(EnumType.STRING)
    private BannerType bannerType;

    private LocalDateTime bannerStartDate;

    private LocalDateTime bannerEndDate;

    protected WeaponBanner() {
        super(Domain.WEAPON_BANNER);
    }

    @Builder
    public WeaponBanner(Weapon weapon, BannerType bannerType, LocalDateTime bannerStartDate, LocalDateTime bannerEndDate) {
        this();
        this.weapon = weapon;
        this.bannerType = bannerType;
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
