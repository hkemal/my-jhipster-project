package myapp.service.mapper;


import myapp.domain.*;
import myapp.service.dto.TeacherInputDTO;
import myapp.service.dto.TeacherResultDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Teacher} and its DTO {@link TeacherResultDTO}.
 */
@Mapper(componentModel = "spring", uses = {StudentMapper.class, CountryMapper.class})
public interface TeacherMapper extends EntityMapper<TeacherResultDTO, Teacher> {

    TeacherResultDTO toDto(Teacher teacher);

    @Mapping(source = "countryId", target = "country")
    @Mapping(source = "studentId", target = "student")
    Teacher toEntity(TeacherInputDTO teacherInputDTO);

    default Teacher fromId(Long id) {
        if (id == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setId(id);
        return teacher;
    }
}
