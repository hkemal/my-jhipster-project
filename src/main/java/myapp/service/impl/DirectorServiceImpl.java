package myapp.service.impl;

import myapp.service.DirectorService;
import myapp.domain.Director;
import myapp.repository.DirectorRepository;
import myapp.service.dto.DirectorInputDTO;
import myapp.service.dto.DirectorResultDTO;
import myapp.service.mapper.DirectorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Director}.
 */
@Service
@Transactional
public class DirectorServiceImpl implements DirectorService {

    private final Logger log = LoggerFactory.getLogger(DirectorServiceImpl.class);

    private final DirectorRepository directorRepository;

    private final DirectorMapper directorMapper;

    public DirectorServiceImpl(DirectorRepository directorRepository, DirectorMapper directorMapper) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
    }

    @Override
    public DirectorResultDTO save(DirectorInputDTO directorInputDTO) {
        log.debug("Request to save Director : {}", directorInputDTO);
        Director director = directorMapper.toEntity(directorInputDTO);
        director = directorRepository.save(director);
        return directorMapper.toDto(director);
    }

    @Override
    public DirectorResultDTO update(Long id, DirectorInputDTO directorInputDTO) {
        log.debug("Request to save Director : {}", directorInputDTO);
        Director director = directorMapper.toEntity(directorInputDTO);
        director.setId(id);
        director = directorRepository.save(director);
        return directorMapper.toDto(director);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DirectorResultDTO> findOne(Long id) {
        log.debug("Request to get Director : {}", id);
        return directorRepository.findById(id)
            .map(directorMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Director : {}", id);
        directorRepository.deleteById(id);
    }
}
