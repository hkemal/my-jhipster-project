package myapp.service.impl;

import myapp.service.CountryService;
import myapp.domain.Country;
import myapp.repository.CountryRepository;
import myapp.service.dto.CountryInputDTO;
import myapp.service.dto.CountryResultDTO;
import myapp.service.mapper.CountryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Country}.
 */
@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private final Logger log = LoggerFactory.getLogger(CountryServiceImpl.class);

    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    public CountryResultDTO save(CountryInputDTO countryInputDTO) {
        log.debug("Request to save Country : {}", countryInputDTO);
        Country country = countryMapper.toEntity(countryInputDTO);
        country = countryRepository.save(country);
        return countryMapper.toDto(country);
    }

    @Override
    public CountryResultDTO update(Long id, CountryInputDTO countryInputDTO) {
        log.debug("Request to save Country : {}", countryInputDTO);
        Country country = countryMapper.toEntity(countryInputDTO);
        country.setId(id);
        country = countryRepository.save(country);
        return countryMapper.toDto(country);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CountryResultDTO> findOne(Long id) {
        log.debug("Request to get Country : {}", id);
        return countryRepository.findById(id)
            .map(countryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Country : {}", id);
        countryRepository.deleteById(id);
    }
}
