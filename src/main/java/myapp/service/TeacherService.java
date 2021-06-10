package myapp.service;

import myapp.service.dto.TeacherInputDTO;
import myapp.service.dto.TeacherResultDTO;

import java.util.Optional;

/**
 * Service Interface for managing {@link myapp.domain.Teacher}.
 */
public interface TeacherService {

    /**
     * Save a teacher.
     *
     * @param teacherInputDTO the entity to save.
     * @return the persisted entity.
     */
    TeacherResultDTO save(TeacherInputDTO teacherInputDTO);

    /**
     * Update a teacher.
     *
     * @param teacherInputDTO the entity to save.
     * @return the persisted entity.
     */
    TeacherResultDTO update(Long id, TeacherInputDTO teacherInputDTO);

    /**
     * Get the "id" teacher.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TeacherResultDTO> findOne(Long id);

    /**
     * Delete the "id" teacher.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
