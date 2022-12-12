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
public class FlujoCaja {

    int idFLujoCaja;
    int idCaja;
    int idVendedor;
    String fecha;
    double inicial;
    double ingreso;
    double egreso;
    double total;
    int estado;

    public FlujoCaja() {
    }

    public FlujoCaja(int idFLujoCaja, int idCaja, int idVendedor, String fecha, double inicial, double ingreso, double egreso, double total, int estado) {
        this.idFLujoCaja = idFLujoCaja;
        this.idCaja = idCaja;
        this.idVendedor = idVendedor;
        this.fecha = fecha;
        this.inicial = inicial;
        this.ingreso = ingreso;
        this.egreso = egreso;
        this.total = total;
        this.estado = estado;
    }

    public int getIdFLujoCaja() {
        return idFLujoCaja;
    }

    public void setIdFLujoCaja(int idFLujoCaja) {
        this.idFLujoCaja = idFLujoCaja;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getInicial() {
        return inicial;
    }

    public void setInicial(double inicial) {
        this.inicial = inicial;
    }

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingrteso) {
        this.ingreso = ingrteso;
    }

    public double getEgreso() {
        return egreso;
    }

    public void setEgreso(double egreso) {
        this.egreso = egreso;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
   
}
