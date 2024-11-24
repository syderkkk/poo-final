package model;

import java.util.Date;

public class Reserva {

    private int idReserva;
    private int idDepartamento;
    private int idInquilino;
    private Date fechaInicio;
    private Date fechaFin;
    private String estadoReserva; // Ejemplo: "Pendiente", "Confirmada", "Cancelada"
    private Date fechaReserva;

    // Constructor vacío
    public Reserva() {}

    // Constructor completo

    public Reserva(int idReserva, int idDepartamento, int idInquilino, Date fechaInicio, Date fechaFin, String estadoReserva, Date fechaReserva) {
        this.idReserva = idReserva;
        this.idDepartamento = idDepartamento;
        this.idInquilino = idInquilino;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadoReserva = estadoReserva;
        this.fechaReserva = fechaReserva;
    }

    // Getters y Setters

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
