package com.sitas.gestionvuelos.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Vuelo {

    @Id
    private String numeroVuelo;

    @Column(nullable = false)
    private String tipoVuelo;

    @Column(nullable = false)
    private String ciudadOrigen;

    @Column(nullable = false)
    private String ciudadDestino;

    @ManyToOne
    @JoinColumn(name = "id_aeronave", nullable = false)
    private Aeronave aeronave;

    @Column(nullable = false)
    private LocalDate fechaSalida;

    @Column(nullable = false)
    private LocalDate fechaLlegada;

    @Column(nullable = false)
    private LocalTime horaSalida;

    @Column(nullable = false)
    private LocalTime horaLlegada;

    @Column(nullable = false)
    private BigDecimal precio;

    @Column(nullable = false)
    private BigDecimal porcentajeImpuestos;

    @Column(nullable = false)
    private BigDecimal sobretasa;

    @Column(nullable = false)
    private String estadoVuelo;

    public Vuelo() {
    }

    // Constructor sin argumentos
    public Vuelo(String v001, String comercial, String bogotá, String medellín, Object o, Object object, Object o1, Object object1, Object o2, Object object2, Object o3) {
    }

    // Constructor con argumentos
    public Vuelo(String numeroVuelo, String tipoVuelo, String ciudadOrigen, String ciudadDestino,
                 Aeronave aeronave, LocalDate fechaSalida, LocalDate fechaLlegada, LocalTime horaSalida,
                 LocalTime horaLlegada, BigDecimal precio, BigDecimal porcentajeImpuestos, BigDecimal sobretasa) {
        this.numeroVuelo = numeroVuelo;
        this.tipoVuelo = tipoVuelo;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.aeronave = aeronave;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.precio = precio;
        this.porcentajeImpuestos = porcentajeImpuestos;
        this.sobretasa = sobretasa;
        this.estadoVuelo = "Programado"; // Valor por defecto
    }

    // Getters y setters
    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public String getTipoVuelo() {
        return tipoVuelo;
    }

    public void setTipoVuelo(String tipoVuelo) {
        this.tipoVuelo = tipoVuelo;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public Aeronave getAeronave() {
        return aeronave;
    }

    public void setAeronave(Aeronave aeronave) {
        this.aeronave = aeronave;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(LocalTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getPorcentajeImpuestos() {
        return porcentajeImpuestos;
    }

    public void setPorcentajeImpuestos(BigDecimal porcentajeImpuestos) {
        this.porcentajeImpuestos = porcentajeImpuestos;
    }

    public BigDecimal getSobretasa() {
        return sobretasa;
    }

    public void setSobretasa(BigDecimal sobretasa) {
        this.sobretasa = sobretasa;
    }

    public String getEstadoVuelo() {
        return estadoVuelo;
    }

    public void setEstadoVuelo(String estadoVuelo) {
        this.estadoVuelo = estadoVuelo;
    }
}
