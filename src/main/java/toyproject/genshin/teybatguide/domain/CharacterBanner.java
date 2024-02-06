package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.Domain;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "character_banner")
public class CharacterBanner extends BaseEntity {

    @OneToOne
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
