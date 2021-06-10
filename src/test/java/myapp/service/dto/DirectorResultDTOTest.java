package myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import myapp.web.rest.TestUtil;

public class DirectorResultDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DirectorResultDTO.class);
        DirectorResultDTO directorResultDTO1 = new DirectorResultDTO();
        directorResultDTO1.setId(1L);
        DirectorResultDTO directorResultDTO2 = new DirectorResultDTO();
        assertThat(directorResultDTO1).isNotEqualTo(directorResultDTO2);
        directorResultDTO2.setId(directorResultDTO1.getId());
        assertThat(directorResultDTO1).isEqualTo(directorResultDTO2);
        directorResultDTO2.setId(2L);
        assertThat(directorResultDTO1).isNotEqualTo(directorResultDTO2);
        directorResultDTO1.setId(null);
        assertThat(directorResultDTO1).isNotEqualTo(directorResultDTO2);
    }
}
