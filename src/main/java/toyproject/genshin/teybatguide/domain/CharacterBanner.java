package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.Domain;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "character_banner")
public class CharacterBanner extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private Characters characters;

    private LocalDateTime bannerEndDate;

    protected CharacterBanner() {
        super(Domain.CHARACTER_BANNER);
    }

    @Builder
    public CharacterBanner(Characters characters, LocalDateTime bannerEndDate) {
        this();
        this.characters = characters;
        this.bannerEndDate = bannerEndDate;
    }
}
