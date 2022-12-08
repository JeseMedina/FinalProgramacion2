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
import modelo.CajaVendedor;

/**
 *
 * @author Jesé
 */
public class CajaVendedorDAO implements CRUD {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    public boolean cajaAbierta(CajaVendedor cv) {
        String sql = "select idcaja_vendedor from cajavendedor where idCaja=? and idVendedor=? and fecha=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cv.getIdCaja());
            ps.setInt(2, cv.getIdVendedor());
            ps.setString(3, cv.getFecha());
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public int abrirCaja(CajaVendedor cv) {
        String sql = "insert into cajavendedor(idCaja,idVendedor,fecha) values(?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cv.getIdCaja());
            ps.setInt(2, cv.getIdVendedor());
            ps.setString(3, cv.getFecha());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }

    @Override
    public List Listar() {
        List<CajaVendedor> lista = new ArrayList<>();
        String sql = "select * from cajavendedor";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CajaVendedor cv = new CajaVendedor();
                cv.setIdCajaVendedor(rs.getInt(1));
                cv.setIdCaja(rs.getInt(2));
                cv.setIdVendedor(rs.getInt(3));
                cv.setFecha(rs.getString((4)));
                lista.add(cv);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = "insert int cajavendedor(idcaja,idvendedor,fecha) values(?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
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
