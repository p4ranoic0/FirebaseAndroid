package com.isil.efinal.entity;

public class Orden {
    private String idOrden;
    private String idUsuario;
    private String idPlato;
    private String nomPlato;
    private Double precioPlato;
    private int cantidad;
    private Double precioTotalPlato;

    public Orden() {
    }

    public Orden(String idOrden, String idUsuario, String idPlato, String nomPlato, Double precioPlato, int cantidad, Double precioTotalPlato) {
        this.idOrden = idOrden;
        this.idUsuario = idUsuario;
        this.idPlato = idPlato;
        this.nomPlato = nomPlato;
        this.precioPlato = precioPlato;
        this.cantidad = cantidad;
        this.precioTotalPlato = precioTotalPlato;
    }

    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(String idPlato) {
        this.idPlato = idPlato;
    }

    public String getNomPlato() {
        return nomPlato;
    }

    public void setNomPlato(String nomPlato) {
        this.nomPlato = nomPlato;
    }

    public Double getPrecioPlato() {
        return precioPlato;
    }

    public void setPrecioPlato(Double precioPlato) {
        this.precioPlato = precioPlato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioTotalPlato() {
        return precioTotalPlato;
    }

    public void setPrecioTotalPlato(Double precioTotalPlato) {
        this.precioTotalPlato = precioTotalPlato;
    }
}
