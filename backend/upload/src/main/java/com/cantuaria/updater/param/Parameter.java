package com.cantuaria.updater.param;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "PAR_PARAMETER")
public class Parameter {

    @Id
    @Column(name = "PAR_ID", length = 15)
    private String id;

    @Column(name = "PAR_VALUE", nullable = false)
    private String value;

    public Parameter() {
    }

    public Parameter(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
