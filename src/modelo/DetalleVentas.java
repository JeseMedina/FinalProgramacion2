/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Jesé
 */
public class DetalleVentas {
    int id;
    int idventa;
    int idProducto;
    int cantidad;
    double preventa;

    public DetalleVentas() {
    }

    public DetalleVentas(int id, int idventa, int idProducto, int cantidad, double preventa) {
        this.id = id;
        this.idventa = idventa;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.preventa = preventa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPreventa() {
        return preventa;
    }

    public void setPreventa(double preventa) {
        this.preventa = preventa;
    }
    
    
    
}
