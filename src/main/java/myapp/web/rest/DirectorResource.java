package myapp.web.rest;

import myapp.service.DirectorService;
import myapp.service.dto.DirectorInputDTO;
import myapp.service.dto.DirectorResultDTO;
import myapp.service.dto.DirectorCriteria;
import myapp.service.DirectorQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link myapp.domain.Director}.
 */
@RestController
@RequestMapping("/api")
public class DirectorResource {

    private final Logger log = LoggerFactory.getLogger(DirectorResource.class);

    private static final String ENTITY_NAME = "director";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DirectorService directorService;

    private final DirectorQueryService directorQueryService;

    public DirectorResource(DirectorService directorService, DirectorQueryService directorQueryService) {
        this.directorService = directorService;
        this.directorQueryService = directorQueryService;
    }

    /**
     * {@code POST  /directors} : Create a new director.
     *
     * @param directorInputDTO the directorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new directorDTO, or with status {@code 400 (Bad Request)} if the director has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/directors")
    public ResponseEntity<DirectorResultDTO> createDirector(@RequestBody DirectorInputDTO directorInputDTO) throws URISyntaxException {
        log.debug("REST request to save Director : {}", directorInputDTO);
        DirectorResultDTO result = directorService.save(directorInputDTO);
        return ResponseEntity.created(new URI("/api/directors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /directors} : Updates an existing director.
     *
     * @param directorInputDTO the directorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated directorDTO,
     * or with status {@code 400 (Bad Request)} if the directorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the directorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/directors")
    public ResponseEntity<DirectorResultDTO> updateDirector(Long id, @RequestBody DirectorInputDTO directorInputDTO) throws URISyntaxException {
        log.debug("REST request to update Director : {}", directorInputDTO);
        DirectorResultDTO result = directorService.update(id,directorInputDTO );
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, directorInputDTO.getName().toString()))
            .body(result);
    }

    /**
     * {@code GET  /directors} : get all the directors.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of directors in body.
     */
    @GetMapping("/directors")
    public ResponseEntity<List<DirectorResultDTO>> getAllCountries(DirectorCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Countries by criteria: {}", criteria);
        Page<DirectorResultDTO> page = directorQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /directors/:id} : get the "id" director.
     *
     * @param id the id of the directorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the directorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/directors/{id}")
    public ResponseEntity<DirectorResultDTO> getDirector(@PathVariable Long id) {
        log.debug("REST request to get Director : {}", id);
        Optional<DirectorResultDTO> directorDTO = directorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(directorDTO);
    }

    /**
     * {@code DELETE  /directors/:id} : delete the "id" director.
     *
     * @param id the id of the directorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/directors/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long id) {
        log.debug("REST request to delete Director : {}", id);
        directorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
