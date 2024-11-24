package model;

import java.util.Date;

public class Pago {

    private int idPago;
    private int idReserva;
    private double monto;
    private Date fechaPago;
    private String metodoPago; // Ejemplo: "Tarjeta", "Transferencia", "Efectivo"
    private String estadoPago; // Ejemplo: "Pagado", "Pendiente", "Cancelado"

    // Constructor vacío
    public Pago() {}

    // Constructor completo

    public Pago(int idPago, int idReserva, double monto, Date fechaPago, String metodoPago, String estadoPago) {
        this.idPago = idPago;
        this.idReserva = idReserva;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
        this.estadoPago = estadoPago;
    }

    // Getters y Setters

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }
}
