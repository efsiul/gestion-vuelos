package com.sitas.gestionvuelos.entities;

import jakarta.persistence.*;

@Entity
public class Aeronave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAeronave;

    @Column(nullable = false)
    private String tipoAvion;

    @Column(nullable = false)
    private int cantidadMaxAsientos;

    @Column
    private String distribucionAsientos;

    // Constructor sin argumentos
    public Aeronave() {
    }

    // Constructor con argumentos
    public Aeronave(String tipoAvion, int cantidadMaxAsientos, String distribucionAsientos) {
        this.tipoAvion = tipoAvion;
        this.cantidadMaxAsientos = cantidadMaxAsientos;
        this.distribucionAsientos = distribucionAsientos;
    }

    // Getters y setters
    public Long getIdAeronave() {
        return idAeronave;
    }

    public void setIdAeronave(Long idAeronave) {
        this.idAeronave = idAeronave;
    }

    public String getTipoAvion() {
        return tipoAvion;
    }

    public void setTipoAvion(String tipoAvion) {
        this.tipoAvion = tipoAvion;
    }

    public int getCantidadMaxAsientos() {
        return cantidadMaxAsientos;
    }

    public void setCantidadMaxAsientos(int cantidadMaxAsientos) {
        this.cantidadMaxAsientos = cantidadMaxAsientos;
    }

    public String getDistribucionAsientos() {
        return distribucionAsientos;
    }

    public void setDistribucionAsientos(String distribucionAsientos) {
        this.distribucionAsientos = distribucionAsientos;
    }
}
