/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Conexion;
import modelo.Egresos;

/**
 *
 * @author Jesé
 */
public class EgresosDAO implements CRUD{
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List Listar() {
        List<Egresos> lista = new ArrayList<>();
        String sql = "SELECT sum(monto) from compras";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Egresos e = new Egresos();
                e.setTotal(rs.getDouble(1));
                lista.add(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return lista;
    }
    
    public List listarFecha(String in, String fi) {
        List<Egresos> lista = new ArrayList<>();
        String sql = "SELECT sum(monto) from compras where fechaCompras >= ? and fechaCompras <= ?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, in);
            ps.setString(2, fi);
            rs = ps.executeQuery();
            while (rs.next()) {
                Egresos e = new Egresos();
                e.setTotal(rs.getDouble(1));
                lista.add(e);
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
