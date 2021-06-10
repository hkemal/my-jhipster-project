package myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import myapp.web.rest.TestUtil;

public class CountryResultDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CountryResultDTO.class);
        CountryResultDTO countryResultDTO1 = new CountryResultDTO();
        countryResultDTO1.setId(1L);
        CountryResultDTO countryResultDTO2 = new CountryResultDTO();
        assertThat(countryResultDTO1).isNotEqualTo(countryResultDTO2);
        countryResultDTO2.setId(countryResultDTO1.getId());
        assertThat(countryResultDTO1).isEqualTo(countryResultDTO2);
        countryResultDTO2.setId(2L);
        assertThat(countryResultDTO1).isNotEqualTo(countryResultDTO2);
        countryResultDTO1.setId(null);
        assertThat(countryResultDTO1).isNotEqualTo(countryResultDTO2);
    }
}
