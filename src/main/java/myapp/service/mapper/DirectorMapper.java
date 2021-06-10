package myapp.service.mapper;


import myapp.domain.*;
import myapp.service.dto.DirectorInputDTO;
import myapp.service.dto.DirectorResultDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Director} and its DTO {@link DirectorResultDTO}.
 */
@Mapper(componentModel = "spring", uses = {TeacherMapper.class, CountryMapper.class})
public interface DirectorMapper extends EntityMapper<DirectorResultDTO, Director> {

    DirectorResultDTO toDto(Director director);

    @Mapping(source = "teacherId", target = "teacher")
    @Mapping(source = "countryId", target = "country")
    Director toEntity(DirectorInputDTO directorInputDTO);

    default Director fromId(Long id) {
        if (id == null) {
            return null;
        }
        Director director = new Director();
        director.setId(id);
        return director;
    }
}
