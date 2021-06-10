package myapp.repository;

import myapp.domain.Teacher;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Teacher entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
//    @EntityGraph(attributePaths = {
//        //"student",
//        "country"
//    }, type = EntityGraph.EntityGraphType.FETCH)
    Optional<Teacher> findById(Long id);
}
