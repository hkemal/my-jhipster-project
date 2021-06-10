package myapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link myapp.domain.Country} entity.
 */
public class CountryInputDTO implements Serializable {

    private String name;

    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return "CountryDTO{" +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
