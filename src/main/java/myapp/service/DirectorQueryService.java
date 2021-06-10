package myapp.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import myapp.domain.Director;
import myapp.domain.*; // for static metamodels
import myapp.repository.DirectorRepository;
import myapp.service.dto.DirectorCriteria;
import myapp.service.dto.DirectorResultDTO;
import myapp.service.mapper.DirectorMapper;

/**
 * Service for executing complex queries for {@link Director} entities in the database.
 * The main input is a {@link DirectorCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DirectorResultDTO} or a {@link Page} of {@link DirectorResultDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DirectorQueryService extends QueryService<Director> {

    private final Logger log = LoggerFactory.getLogger(DirectorQueryService.class);

    private final DirectorRepository directorRepository;

    private final DirectorMapper directorMapper;

    public DirectorQueryService(DirectorRepository directorRepository, DirectorMapper directorMapper) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
    }

    /**
     * Return a {@link Page} of {@link DirectorResultDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DirectorResultDTO> findByCriteria(DirectorCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Director> specification = createSpecification(criteria);
        return directorRepository.findAll(specification, page)
            .map(directorMapper::toDto);
    }

    /**
     * Function to convert {@link DirectorCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Director> createSpecification(DirectorCriteria criteria) {
        Specification<Director> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Director_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Director_.name));
            }
            if (criteria.getSurname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSurname(), Director_.surname));
            }
            if (criteria.getTeacherId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherId(),
                    root -> root.join(Director_.teacher, JoinType.LEFT).get(Teacher_.id)));
            }
            if (criteria.getCountryId() != null) {
                specification = specification.and(buildSpecification(criteria.getCountryId(),
                    root -> root.join(Director_.country, JoinType.LEFT).get(Country_.id)));
            }
        }
        return specification;
    }
}
