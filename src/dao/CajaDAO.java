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
import javax.swing.JTextField;
import modelo.Caja;

/**
 *
 * @author Jesé
 */
public class CajaDAO implements CRUD {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    public int listarId(int nCaja) {
        String sql = "select idCaja from caja where numeroCaja=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, nCaja);
            rs = ps.executeQuery();
            while (rs.next()) {
                r = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }

    public int listarN(int idCaja) {
        String sql = "select numeroCaja from caja where idCaja=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCaja);
            rs = ps.executeQuery();
            while (rs.next()) {
                r = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }

    @Override
    public List Listar() {
        List<Caja> lista = new ArrayList<>();
        String sql = "select * from caja";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Caja c = new Caja();
                c.setIdCaja(rs.getInt(1));
                c.setnCaja(rs.getInt(2));
                lista.add(c);
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
