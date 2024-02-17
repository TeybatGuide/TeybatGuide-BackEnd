package toyproject.genshin.teybatguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.genshin.teybatguide.domain.Artifact;

@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, String> {
}
