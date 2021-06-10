package myapp.web.rest;

import myapp.MyProjectApp;
import myapp.domain.Director;
import myapp.domain.Teacher;
import myapp.domain.Country;
import myapp.repository.DirectorRepository;
import myapp.service.DirectorService;
import myapp.service.dto.DirectorResultDTO;
import myapp.service.mapper.DirectorMapper;
import myapp.service.DirectorQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DirectorResource} REST controller.
 */
@SpringBootTest(classes = MyProjectApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DirectorResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SURNAME = "AAAAAAAAAA";
    private static final String UPDATED_SURNAME = "BBBBBBBBBB";

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private DirectorMapper directorMapper;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private DirectorQueryService directorQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDirectorMockMvc;

    private Director director;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Director createEntity(EntityManager em) {
        Director director = new Director()
            .name(DEFAULT_NAME)
            .surname(DEFAULT_SURNAME);
        return director;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Director createUpdatedEntity(EntityManager em) {
        Director director = new Director()
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME);
        return director;
    }

    @BeforeEach
    public void initTest() {
        director = createEntity(em);
    }

    @Test
    @Transactional
    public void createDirector() throws Exception {
        int databaseSizeBeforeCreate = directorRepository.findAll().size();
        // Create the Director
        DirectorResultDTO directorResultDTO = directorMapper.toDto(director);
        restDirectorMockMvc.perform(post("/api/directors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(directorResultDTO)))
            .andExpect(status().isCreated());

        // Validate the Director in the database
        List<Director> directorList = directorRepository.findAll();
        assertThat(directorList).hasSize(databaseSizeBeforeCreate + 1);
        Director testDirector = directorList.get(directorList.size() - 1);
        assertThat(testDirector.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDirector.getSurname()).isEqualTo(DEFAULT_SURNAME);
    }

    @Test
    @Transactional
    public void createDirectorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = directorRepository.findAll().size();

        // Create the Director with an existing ID
        director.setId(1L);
        DirectorResultDTO directorResultDTO = directorMapper.toDto(director);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDirectorMockMvc.perform(post("/api/directors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(directorResultDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Director in the database
        List<Director> directorList = directorRepository.findAll();
        assertThat(directorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDirectors() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);

        // Get all the directorList
        restDirectorMockMvc.perform(get("/api/directors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(director.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DEFAULT_SURNAME)));
    }

    @Test
    @Transactional
    public void getDirector() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);

        // Get the director
        restDirectorMockMvc.perform(get("/api/directors/{id}", director.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(director.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.surname").value(DEFAULT_SURNAME));
    }


    @Test
    @Transactional
    public void getDirectorsByIdFiltering() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);

        Long id = director.getId();

        defaultDirectorShouldBeFound("id.equals=" + id);
        defaultDirectorShouldNotBeFound("id.notEquals=" + id);

        defaultDirectorShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDirectorShouldNotBeFound("id.greaterThan=" + id);

        defaultDirectorShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDirectorShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDirectorsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);

        // Get all the directorList where name equals to DEFAULT_NAME
        defaultDirectorShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the directorList where name equals to UPDATED_NAME
        defaultDirectorShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllDirectorsByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);

        // Get all the directorList where name not equals to DEFAULT_NAME
        defaultDirectorShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the directorList where name not equals to UPDATED_NAME
        defaultDirectorShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllDirectorsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);

        // Get all the directorList where name in DEFAULT_NAME or UPDATED_NAME
        defaultDirectorShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the directorList where name equals to UPDATED_NAME
        defaultDirectorShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllDirectorsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);

        // Get all the directorList where name is not null
        defaultDirectorShouldBeFound("name.specified=true");

        // Get all the directorList where name is null
        defaultDirectorShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllDirectorsByNameContainsSomething() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);

        // Get all the directorList where name contains DEFAULT_NAME
        defaultDirectorShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the directorList where name contains UPDATED_NAME
        defaultDirectorShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllDirectorsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);

        // Get all the directorList where name does not contain DEFAULT_NAME
        defaultDirectorShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the directorList where name does not contain UPDATED_NAME
        defaultDirectorShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllDirectorsBySurnameIsEqualToSomething() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);

        // Get all the directorList where surname equals to DEFAULT_SURNAME
        defaultDirectorShouldBeFound("surname.equals=" + DEFAULT_SURNAME);

        // Get all the directorList where surname equals to UPDATED_SURNAME
        defaultDirectorShouldNotBeFound("surname.equals=" + UPDATED_SURNAME);
    }

    @Test
    @Transactional
    public void getAllDirectorsBySurnameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);

        // Get all the directorList where surname not equals to DEFAULT_SURNAME
        defaultDirectorShouldNotBeFound("surname.notEquals=" + DEFAULT_SURNAME);

        // Get all the directorList where surname not equals to UPDATED_SURNAME
        defaultDirectorShouldBeFound("surname.notEquals=" + UPDATED_SURNAME);
    }

    @Test
    @Transactional
    public void getAllDirectorsBySurnameIsInShouldWork() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);

        // Get all the directorList where surname in DEFAULT_SURNAME or UPDATED_SURNAME
        defaultDirectorShouldBeFound("surname.in=" + DEFAULT_SURNAME + "," + UPDATED_SURNAME);

        // Get all the directorList where surname equals to UPDATED_SURNAME
        defaultDirectorShouldNotBeFound("surname.in=" + UPDATED_SURNAME);
    }

    @Test
    @Transactional
    public void getAllDirectorsBySurnameIsNullOrNotNull() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);

        // Get all the directorList where surname is not null
        defaultDirectorShouldBeFound("surname.specified=true");

        // Get all the directorList where surname is null
        defaultDirectorShouldNotBeFound("surname.specified=false");
    }
                @Test
    @Transactional
    public void getAllDirectorsBySurnameContainsSomething() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);

        // Get all the directorList where surname contains DEFAULT_SURNAME
        defaultDirectorShouldBeFound("surname.contains=" + DEFAULT_SURNAME);

        // Get all the directorList where surname contains UPDATED_SURNAME
        defaultDirectorShouldNotBeFound("surname.contains=" + UPDATED_SURNAME);
    }

    @Test
    @Transactional
    public void getAllDirectorsBySurnameNotContainsSomething() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);

        // Get all the directorList where surname does not contain DEFAULT_SURNAME
        defaultDirectorShouldNotBeFound("surname.doesNotContain=" + DEFAULT_SURNAME);

        // Get all the directorList where surname does not contain UPDATED_SURNAME
        defaultDirectorShouldBeFound("surname.doesNotContain=" + UPDATED_SURNAME);
    }


    @Test
    @Transactional
    public void getAllDirectorsByTeacherIsEqualToSomething() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);
        Teacher teacher = TeacherResourceIT.createEntity(em);
        em.persist(teacher);
        em.flush();
        director.setTeacher(teacher);
        directorRepository.saveAndFlush(director);
        Long teacherId = teacher.getId();

        // Get all the directorList where teacher equals to teacherId
        defaultDirectorShouldBeFound("teacherId.equals=" + teacherId);

        // Get all the directorList where teacher equals to teacherId + 1
        defaultDirectorShouldNotBeFound("teacherId.equals=" + (teacherId + 1));
    }


    @Test
    @Transactional
    public void getAllDirectorsByCountryIsEqualToSomething() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);
        Country country = CountryResourceIT.createEntity(em);
        em.persist(country);
        em.flush();
        director.setCountry(country);
        directorRepository.saveAndFlush(director);
        Long countryId = country.getId();

        // Get all the directorList where country equals to countryId
        defaultDirectorShouldBeFound("countryId.equals=" + countryId);

        // Get all the directorList where country equals to countryId + 1
        defaultDirectorShouldNotBeFound("countryId.equals=" + (countryId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDirectorShouldBeFound(String filter) throws Exception {
        restDirectorMockMvc.perform(get("/api/directors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(director.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DEFAULT_SURNAME)));

        // Check, that the count call also returns 1
        restDirectorMockMvc.perform(get("/api/directors/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDirectorShouldNotBeFound(String filter) throws Exception {
        restDirectorMockMvc.perform(get("/api/directors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDirectorMockMvc.perform(get("/api/directors/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingDirector() throws Exception {
        // Get the director
        restDirectorMockMvc.perform(get("/api/directors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDirector() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);

        int databaseSizeBeforeUpdate = directorRepository.findAll().size();

        // Update the director
        Director updatedDirector = directorRepository.findById(director.getId()).get();
        // Disconnect from session so that the updates on updatedDirector are not directly saved in db
        em.detach(updatedDirector);
        updatedDirector
            .name(UPDATED_NAME)
            .surname(UPDATED_SURNAME);
        DirectorResultDTO directorResultDTO = directorMapper.toDto(updatedDirector);

        restDirectorMockMvc.perform(put("/api/directors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(directorResultDTO)))
            .andExpect(status().isOk());

        // Validate the Director in the database
        List<Director> directorList = directorRepository.findAll();
        assertThat(directorList).hasSize(databaseSizeBeforeUpdate);
        Director testDirector = directorList.get(directorList.size() - 1);
        assertThat(testDirector.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDirector.getSurname()).isEqualTo(UPDATED_SURNAME);
    }

    @Test
    @Transactional
    public void updateNonExistingDirector() throws Exception {
        int databaseSizeBeforeUpdate = directorRepository.findAll().size();

        // Create the Director
        DirectorResultDTO directorResultDTO = directorMapper.toDto(director);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDirectorMockMvc.perform(put("/api/directors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(directorResultDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Director in the database
        List<Director> directorList = directorRepository.findAll();
        assertThat(directorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDirector() throws Exception {
        // Initialize the database
        directorRepository.saveAndFlush(director);

        int databaseSizeBeforeDelete = directorRepository.findAll().size();

        // Delete the director
        restDirectorMockMvc.perform(delete("/api/directors/{id}", director.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Director> directorList = directorRepository.findAll();
        assertThat(directorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
