package com.eep.simarro.Drones;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Drones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String apellido;
    private String DE;

    public Drones() {
    }

    public Drones(Integer id, String name, String apellido, String DE) {
        this.id = id;
        this.name = name;
        this.apellido = apellido;
        this.DE = DE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDE() {
        return DE;
    }

    public void setDE(String DE) {
        this.DE = DE;
    }
}
