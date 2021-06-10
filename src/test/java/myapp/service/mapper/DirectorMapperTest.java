package myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DirectorMapperTest {

    private DirectorMapper directorMapper;

    @BeforeEach
    public void setUp() {
        directorMapper = new DirectorMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(directorMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(directorMapper.fromId(null)).isNull();
    }
}
