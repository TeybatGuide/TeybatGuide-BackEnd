package toyproject.genshin.teybatguide.banner.character.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.base.BaseEntity;
import toyproject.genshin.teybatguide.character.entity.Characters;
import toyproject.genshin.teybatguide.banner.controller.dto.request.CharacterBannerSaveRequest;
import toyproject.genshin.teybatguide.banner.value.BannerType;
import toyproject.genshin.teybatguide.base.value.Domain;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "character_banner")
public class CharacterBanner extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private Characters characters;

    @Enumerated(EnumType.STRING)
    private BannerType bannerType;

    @Column
    private LocalDateTime bannerStartDate;

    @Column
    private LocalDateTime bannerEndDate;

    protected CharacterBanner() {
        super(Domain.CHARACTER_BANNER);
    }

    @Builder
    public CharacterBanner(Characters characters, BannerType bannerType, LocalDateTime bannerStartDate, LocalDateTime bannerEndDate) {
        this();
        this.characters = characters;
        this.bannerType = bannerType;
        this.bannerStartDate = bannerStartDate;
        this.bannerEndDate = bannerEndDate;
    }

    public static CharacterBanner of(Characters characters, CharacterBannerSaveRequest request) {
        return CharacterBanner.builder()
                .characters(characters)
                .bannerType(request.bannerType())
                .bannerStartDate(request.startDate())
                .bannerEndDate(request.endDate())
                .build();
    }
}
