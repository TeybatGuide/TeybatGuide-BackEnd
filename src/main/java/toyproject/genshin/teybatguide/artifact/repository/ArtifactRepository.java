package toyproject.genshin.teybatguide.artifact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.genshin.teybatguide.artifact.entity.Artifact;
import toyproject.genshin.teybatguide.artifact.repository.querydsl.CustomArtifactRepository;

@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, String>, CustomArtifactRepository {
}
