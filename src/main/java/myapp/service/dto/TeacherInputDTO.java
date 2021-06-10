package myapp.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link myapp.domain.Teacher} entity.
 */
public class TeacherInputDTO implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    private Long countryId;

    private Long studentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "TeacherInputDTO{" +
            "name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", countryId=" + countryId +
            ", studentId=" + studentId +
            '}';
    }
}
