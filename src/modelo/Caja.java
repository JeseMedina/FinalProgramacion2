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
public class Caja {
    int idCaja;
    int nCaja;

    public Caja() {
    }

    public Caja(int idCaja, int nCaja) {
        this.idCaja = idCaja;
        this.nCaja = nCaja;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public int getnCaja() {
        return nCaja;
    }

    public void setnCaja(int nCaja) {
        this.nCaja = nCaja;
    }
    
    
}
