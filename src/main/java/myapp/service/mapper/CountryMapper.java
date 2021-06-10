package myapp.service.mapper;


import myapp.domain.*;
import myapp.service.dto.CountryInputDTO;
import myapp.service.dto.CountryResultDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Country} and its DTO {@link CountryResultDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CountryMapper extends EntityMapper<CountryResultDTO, Country> {

    CountryResultDTO toDto(Country country);

    Country toEntity(CountryInputDTO countryInputDTO);

    default Country fromId(Long id) {
        if (id == null) {
            return null;
        }
        Country country = new Country();
        country.setId(id);
        return country;
    }
}
