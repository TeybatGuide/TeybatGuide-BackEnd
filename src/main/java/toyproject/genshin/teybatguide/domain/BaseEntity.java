package toyproject.genshin.teybatguide.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import toyproject.genshin.teybatguide.domain.value.Domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @Column(name = "id", updatable = false, unique = true, nullable = false, length = 50)
    private String id;

    public BaseEntity() {
    }

    public BaseEntity(Domain domain) {
        id = String.join(domain.toString().toLowerCase(), "_",
                UUID.randomUUID().toString().replace("-",""));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
