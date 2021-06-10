package myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import myapp.web.rest.TestUtil;

public class StudentResultDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudentResultDTO.class);
        StudentResultDTO studentResultDTO1 = new StudentResultDTO();
        studentResultDTO1.setId(1L);
        StudentResultDTO studentResultDTO2 = new StudentResultDTO();
        assertThat(studentResultDTO1).isNotEqualTo(studentResultDTO2);
        studentResultDTO2.setId(studentResultDTO1.getId());
        assertThat(studentResultDTO1).isEqualTo(studentResultDTO2);
        studentResultDTO2.setId(2L);
        assertThat(studentResultDTO1).isNotEqualTo(studentResultDTO2);
        studentResultDTO1.setId(null);
        assertThat(studentResultDTO1).isNotEqualTo(studentResultDTO2);
    }
}
