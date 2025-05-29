package com.api.facturacion.domain.models;

import com.api.facturacion.domain.dtos.clientDTOS.AdressDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Embeddable
@Validated
@Data
@AllArgsConstructor
public class Adress {
    private String city;
    private String neighborhood;
    private String street;
    private String complement;
    private String details;

    public Adress() {
    }
    public Adress(AdressDTO adress) {
        this.city = adress.city();
        this.neighborhood = adress.neighborhood();
        this.street = adress.street();
        this.complement = adress.complement();
        this.details = adress.details();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
