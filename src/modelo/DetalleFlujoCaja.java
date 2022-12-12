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
public class DetalleFlujoCaja {
    int idDetalleFlujoCaja;
    int idFlujocaja;
    double ingreso;
    double egreso;
    String descripcion;

    public DetalleFlujoCaja() {
    }

    public DetalleFlujoCaja(int idDetalleFlujoCaja, int idFlujocaja, double ingreso, double egreso, String descripcion) {
        this.idDetalleFlujoCaja = idDetalleFlujoCaja;
        this.idFlujocaja = idFlujocaja;
        this.ingreso = ingreso;
        this.egreso = egreso;
        this.descripcion = descripcion;
    }

    public int getIdDetalleFlujoCaja() {
        return idDetalleFlujoCaja;
    }

    public void setIdDetalleFlujoCaja(int idDetalleFlujoCaja) {
        this.idDetalleFlujoCaja = idDetalleFlujoCaja;
    }

    public int getIdFlujocaja() {
        return idFlujocaja;
    }

    public void setIdFlujocaja(int idFlujocaja) {
        this.idFlujocaja = idFlujocaja;
    }

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }

    public double getEgreso() {
        return egreso;
    }

    public void setEgreso(double egreso) {
        this.egreso = egreso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
