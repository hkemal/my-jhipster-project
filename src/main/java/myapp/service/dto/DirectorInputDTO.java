package myapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link myapp.domain.Director} entity.
 */
public class DirectorInputDTO implements Serializable {

    private String name;

    private String surname;

    private Long teacherId;

    private Long countryId;

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

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "DirectorDTO{" +
            ", name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", teacherId=" + getTeacherId() +
            ", countryId=" + getCountryId() +
            "}";
    }
}
