package com.isil.efinal.entity;

public class Plato {
    private String idPlato;
    private String nomPlato;
    private Double precio;
    private Integer nomImagen;

    public Plato() {
    }

    public Plato(String idPlato, String nomPlato, Double precio, Integer nomImagen) {
        this.idPlato = idPlato;
        this.nomPlato = nomPlato;
        this.precio = precio;
        this.nomImagen = nomImagen;
    }

    public String getId() {
        return idPlato;
    }

    public void setId(String idPlato) {
        this.idPlato = idPlato;
    }

    public String getNomPlato() {
        return nomPlato;
    }

    public void setNomPlato(String nomPlato) {
        this.nomPlato = nomPlato;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getNomImagen() {
        return nomImagen;
    }

    public void setNomImagen(Integer nomImagen) {
        this.nomImagen = nomImagen;
    }
}
