/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.FlujoCaja;

/**
 *
 * @author Jesé
 */
public class FlujoCajaDAO implements CRUD {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    public boolean cajaAbierta(FlujoCaja fc) {
        String sql = "select idFlujoCaja from flujocaja where idCaja=? and idVendedor=? and fecha=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, fc.getIdCaja());
            ps.setInt(2, fc.getIdVendedor());
            ps.setString(3, fc.getFecha());
            
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public int abrirCaja(FlujoCaja fc) {
        String sql = "insert into flujocaja(idCaja,idVendedor,fecha,inicial) values(?,?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, fc.getIdCaja());
            ps.setInt(2, fc.getIdVendedor());
            ps.setString(3, fc.getFecha());
            ps.setDouble(4, fc.getInicial());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }

    @Override
    public List Listar() {
        List<FlujoCaja> lista = new ArrayList<>();
        String sql = "select * from flujoCaja";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                FlujoCaja fc = new FlujoCaja();
                fc.setIdFLujoCaja(rs.getInt(1));
                fc.setIdCaja(rs.getInt(2));
                fc.setIdVendedor(rs.getInt(3));
                fc.setFecha(rs.getString((4)));
                lista.add(fc);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
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
