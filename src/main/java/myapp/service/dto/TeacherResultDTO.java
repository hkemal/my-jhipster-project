package myapp.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link myapp.domain.Teacher} entity.
 */
public class TeacherResultDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    private CountryResultDTO country;

    private StudentResultDTO student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public CountryResultDTO getCountry() {
        return country;
    }

    public void setCountry(CountryResultDTO country) {
        this.country = country;
    }

    public StudentResultDTO getStudent() {
        return student;
    }

    public void setStudent(StudentResultDTO student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TeacherResultDTO)) {
            return false;
        }

        return id != null && id.equals(((TeacherResultDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TeacherResultDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", country=" + country +
            ", student=" + student +
            '}';
    }
}
