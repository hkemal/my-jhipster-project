package myapp.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link myapp.domain.Student} entity.
 */
public class StudentInputDTO implements Serializable {

    @NotNull
    @Size(min = 1, max = 10)
    private String name;

    @NotNull
    @Size(min = 1, max = 10)
    private String surname;

    @NotNull
    private String number;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "StudentInputDTO{" +
            "name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", number='" + number + '\'' +
            '}';
    }
}
