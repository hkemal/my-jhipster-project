package myapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link myapp.domain.Director} entity.
 */
public class DirectorResultDTO implements Serializable {

    private Long id;

    private String name;

    private String surname;

    private TeacherResultDTO teacher;

    private CountryResultDTO country;

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

    public TeacherResultDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherResultDTO teacher) {
        this.teacher = teacher;
    }

    public CountryResultDTO getCountry() {
        return country;
    }

    public void setCountry(CountryResultDTO country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DirectorResultDTO)) {
            return false;
        }

        return id != null && id.equals(((DirectorResultDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DirectorResultDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", teacher=" + teacher +
            ", country=" + country +
            '}';
    }
}
