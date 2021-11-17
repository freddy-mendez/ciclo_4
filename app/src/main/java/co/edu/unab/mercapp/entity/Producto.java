package co.edu.unab.mercapp.entity;

import java.util.Map;

public class Producto {
    private String idDocumento;
    private String codigo;
    private String nombre;
    private int precio;
    private boolean disponible;

    private Map<String, Object> referencias;

    public Producto() {

    }

    public Producto(String codigo, String nombre, int precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = true;
    }

    public String getId() {
        return idDocumento;
    }

    public void setId(String id) {
        this.idDocumento=id;
    }

    public Producto(String codigo, String nombre, int precio, boolean disponible) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Map<String, Object> getReferencias() {
        return referencias;
    }

    public void setReferencias(Map<String, Object> referencias) {
        this.referencias = referencias;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", disponible=" + disponible +
                '}';
    }
}
