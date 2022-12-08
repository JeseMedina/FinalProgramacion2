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
public class CajaVendedor {
    int idCajaVendedor;
    int idCaja;
    int idVendedor;
    String fecha;

    public CajaVendedor() {
    }

    public CajaVendedor(int idCajaVendedor, int idCaja, int idVendedor, String fecha) {
        this.idCajaVendedor = idCajaVendedor;
        this.idCaja = idCaja;
        this.idVendedor = idVendedor;
        this.fecha = fecha;
    }

    public int getIdCajaVendedor() {
        return idCajaVendedor;
    }

    public void setIdCajaVendedor(int idCajaVendedor) {
        this.idCajaVendedor = idCajaVendedor;
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
    
    
}
