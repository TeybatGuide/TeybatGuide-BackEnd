package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.Domain;

import java.util.List;

@Getter
@Entity
@Table(name = "character_resources")
public class CharacterResources extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "charater_id", referencedColumnName = "id")
    private Characters characters;

    @OneToMany
    @JoinColumn(name = "resources_id", referencedColumnName = "id")
    private List<Resources> resourcesList;

    protected CharacterResources() {
        super(Domain.CHARACTER_RESOURCES);
    }

    @Builder
    public CharacterResources(Characters characters, List<Resources> resourcesList) {
        this();
        this.characters = characters;
        this.resourcesList = resourcesList;
    }
}
