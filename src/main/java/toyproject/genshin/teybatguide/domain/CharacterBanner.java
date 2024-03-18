package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.controller.dto.main.CharacterBannerSaveRequest;
import toyproject.genshin.teybatguide.domain.value.BannerType;
import toyproject.genshin.teybatguide.domain.value.Domain;

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
