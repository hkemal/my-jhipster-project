package myapp.service;

import myapp.service.dto.DirectorInputDTO;
import myapp.service.dto.DirectorResultDTO;

import java.util.Optional;

/**
 * Service Interface for managing {@link myapp.domain.Director}.
 */
public interface DirectorService {

    /**
     * Save a director.
     *
     * @param directorInputDTO the entity to save.
     * @return the persisted entity.
     */
    DirectorResultDTO save(DirectorInputDTO directorInputDTO);

    /**
     * Update a director.
     *
     * @param directorInputDTO the entity to save.
     * @return the persisted entity.
     */
    DirectorResultDTO update(Long id, DirectorInputDTO directorInputDTO);

    /**
     * Get the "id" director.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DirectorResultDTO> findOne(Long id);

    /**
     * Delete the "id" director.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
