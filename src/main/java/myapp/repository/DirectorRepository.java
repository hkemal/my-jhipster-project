package myapp.repository;

import myapp.domain.Director;

import myapp.domain.Teacher;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Director entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DirectorRepository extends JpaRepository<Director, Long>, JpaSpecificationExecutor<Director> {
    @EntityGraph(
        attributePaths = {
            "teacher",
            "country"
        }, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Director> findById(Long id);
}
