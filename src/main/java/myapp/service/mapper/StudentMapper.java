package myapp.service.mapper;


import myapp.domain.*;
import myapp.service.dto.StudentInputDTO;
import myapp.service.dto.StudentResultDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Student} and its DTO {@link StudentResultDTO}.
 */
@Mapper(componentModel = "spring", uses = {DirectorMapper.class})
public interface StudentMapper extends EntityMapper<StudentResultDTO, Student> {

    StudentResultDTO toDto(Student student);

    Student toEntity(StudentInputDTO studentInputDTO);

    default Student fromId(Long id) {
        if (id == null) {
            return null;
        }
        Student student = new Student();
        student.setId(id);
        return student;
    }
}
