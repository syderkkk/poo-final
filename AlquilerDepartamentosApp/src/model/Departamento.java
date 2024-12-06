package model;

import java.sql.Timestamp;

public class Departamento {

    private int idDepartamento;
    private int idPropietario;
    private String descripcion;
    private String estado;
    private double precioAlquiler;
    private String ciudad;
    private int numeroHabitaciones;
    private int numeroBanos;
    private int capacidad;
    private String direccion;
    private Timestamp fechaAlquiler;
    private Timestamp fechaVencimiento;

    public Departamento() {}

    public Departamento(int idDepartamento, int idPropietario, String descripcion, String estado, double precioAlquiler, String ciudad, int numeroHabitaciones, int numeroBanos, int capacidad, String direccion) {
        this.idDepartamento = idDepartamento;
        this.idPropietario = idPropietario;
        this.descripcion = descripcion;
        this.estado = estado;
        this.precioAlquiler = precioAlquiler;
        this.ciudad = ciudad;
        this.numeroHabitaciones = numeroHabitaciones;
        this.numeroBanos = numeroBanos;
        this.capacidad = capacidad;
        this.direccion = direccion;
    }

    public Departamento(int idDepartamento, int idPropietario, String descripcion, String estado, double precioAlquiler, String ciudad, int numeroHabitaciones, int numeroBanos, int capacidad, String direccion, Timestamp fechaAlquiler, Timestamp fechaVencimiento) {
        this.idDepartamento = idDepartamento;
        this.idPropietario = idPropietario;
        this.descripcion = descripcion;
        this.estado = estado;
        this.precioAlquiler = precioAlquiler;
        this.ciudad = ciudad;
        this.numeroHabitaciones = numeroHabitaciones;
        this.numeroBanos = numeroBanos;
        this.capacidad = capacidad;
        this.direccion = direccion;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(double precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getNumeroHabitaciones() {
        return numeroHabitaciones;
    }

    public void setNumeroHabitaciones(int numeroHabitaciones) {
        this.numeroHabitaciones = numeroHabitaciones;
    }

    public int getNumeroBanos() {
        return numeroBanos;
    }

    public void setNumeroBanos(int numeroBanos) {
        this.numeroBanos = numeroBanos;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Timestamp getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Timestamp fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public Timestamp getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Timestamp fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
