package myapp.service;

import myapp.service.dto.CountryInputDTO;
import myapp.service.dto.CountryResultDTO;

import java.util.Optional;

/**
 * Service Interface for managing {@link myapp.domain.Country}.
 */
public interface CountryService {

    /**
     * Save a country.
     *
     * @param countryInputDTO the entity to save.
     * @return the persisted entity.
     */
    CountryResultDTO save(CountryInputDTO countryInputDTO);

    /**
     * Update a country.
     *
     * @param countryInputDTO the entity to save.
     * @return the persisted entity.
     */
    CountryResultDTO update(Long id, CountryInputDTO countryInputDTO);

    /**
     * Get the "id" country.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CountryResultDTO> findOne(Long id);

    /**
     * Delete the "id" country.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
