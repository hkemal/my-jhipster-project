package myapp.service;

import myapp.service.dto.StudentInputDTO;
import myapp.service.dto.StudentResultDTO;


import java.util.Optional;

/**
 * Service Interface for managing {@link myapp.domain.Student}.
 */
public interface StudentService {

    /**
     * Save a student.
     *
     * @param studentInputDTO the entity to save.
     * @return the persisted entity.
     */
    StudentResultDTO save(StudentInputDTO studentInputDTO);

    /**
     * Update a student.
     *
     * @param studentInputDTO the entity to save.
     * @return the persisted entity.
     */
    StudentResultDTO update(Long id, StudentInputDTO studentInputDTO);

    /**
     * Get the "id" student.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StudentResultDTO> findOne(Long id);

    /**
     * Delete the "id" student.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
