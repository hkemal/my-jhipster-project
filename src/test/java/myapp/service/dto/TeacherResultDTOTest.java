package myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import myapp.web.rest.TestUtil;

public class TeacherResultDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TeacherResultDTO.class);
        TeacherResultDTO teacherResultDTO1 = new TeacherResultDTO();
        teacherResultDTO1.setId(1L);
        TeacherResultDTO teacherResultDTO2 = new TeacherResultDTO();
        assertThat(teacherResultDTO1).isNotEqualTo(teacherResultDTO2);
        teacherResultDTO2.setId(teacherResultDTO1.getId());
        assertThat(teacherResultDTO1).isEqualTo(teacherResultDTO2);
        teacherResultDTO2.setId(2L);
        assertThat(teacherResultDTO1).isNotEqualTo(teacherResultDTO2);
        teacherResultDTO1.setId(null);
        assertThat(teacherResultDTO1).isNotEqualTo(teacherResultDTO2);
    }
}
