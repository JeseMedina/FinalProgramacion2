/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import modelo.Compras;
import modelo.Conexion;
import modelo.DetalleCompras;

/**
 *
 * @author Jesé
 */
public class ComprasDAO implements CRUD{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;
    
    public String nroSeriecompras(){
        String serie = "";
        String sql = "select max(numeroSerie) from compras";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while(rs.next()){
                serie = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return serie;
    }
    
    public String idCompras(){
        String idv = "";
        String sql = "select max(idCompra) from compras";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                idv = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return idv;
    }
    
    public int guardarCompras(Compras p){
        Compras compras = new Compras();
        String sql = "insert into compras(idProveedor,numeroSerie,FechaCompras,monto) values(?,?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getIdProveedor());
            ps.setString(2, p.getSerie());
            ps.setString(3, p.getFecha());
            ps.setDouble(4, p.getMonto());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }
    
    public int guardarDetalleCompras(DetalleCompras dc){
        String sql = "insert into detallecompra(idCompra,idProducto,cantidad,precioCompra) values(?,?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dc.getIdCompra());
            ps.setInt(2, dc.getIdProducto());
            ps.setInt(3, dc.getCantidad());
            ps.setDouble(4, dc.getPrecompra());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }
    
    @Override
    public List Listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int add(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(Object[] o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
