package com.banco.accounts.infrastructure.persistence.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "sala")
@Getter
@Setter
public class SalaEntity {

    @Id
    @Column(name = "sala_id")
    private String salaId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "capacidad")
    private Integer capacidad;
}
