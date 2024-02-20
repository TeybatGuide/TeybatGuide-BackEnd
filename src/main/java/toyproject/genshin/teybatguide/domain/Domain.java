package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.Country;

import java.util.List;

import static toyproject.genshin.teybatguide.domain.value.Domain.DOMAIN;

@Getter
@Entity
@Table(name = "domain")
public class Domain extends BaseEntity {

    @Column(length = 100, nullable = false)
    private String domainName;

    @Enumerated(EnumType.STRING)
    private Country country;

    @OneToMany(mappedBy = "domain")
    List<Artifact> artifactList;

    public Domain() {
        super(DOMAIN);
    }

    @Builder
    public Domain(String domainName, Country country) {
        this();
        this.domainName = domainName;
        this.country = country;
    }
}
