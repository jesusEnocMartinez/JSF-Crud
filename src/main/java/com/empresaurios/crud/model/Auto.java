package com.empresaurios.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_auto")
public class Auto implements Serializable {

    @Id
    @GenericGenerator(name = "id_auto", strategy = "increment")
    @GeneratedValue(generator = "id_auto")
    @Column(name = "id")
    private Integer id;
    @Column(name = "modelo")
    private String model;
    @Column(name = "capacidad")
    private Integer capacity;
    @Column(name = "puertas")
    private Integer doors;

    public Auto() {
    }

    public Auto(Integer id, String model, Integer capacity, Integer doors) {
        this.id = id;
        this.model = model;
        this.capacity = capacity;
        this.doors = doors;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public Auto copy() {
        return new Auto(id, model, capacity, doors);
    }

    public void restore(Auto auto) {
        this.id = auto.getId();
        this.model = auto.getModel();
        this.capacity = auto.getCapacity();
        this.doors = auto.getDoors();
    }
}
