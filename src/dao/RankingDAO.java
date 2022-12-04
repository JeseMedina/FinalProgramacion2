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
import modelo.Ranking;

/**
 *
 * @author Jesé
 */
public class RankingDAO implements CRUD {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List Listar() {
        List<Ranking> lista = new ArrayList<>();
        String sql = "SELECT detalleventa.idProducto,  producto.nombre ,producto.categoria,SUM(cantidad)as ranking\n"
                + "from detalleventa,producto\n"
                + "WHERE detalleventa.idProducto = producto.idProducto\n"
                + "GROUP BY idProducto\n"
                + "ORDER BY sum(cantidad) DESC";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ranking r = new Ranking();
                r.setIdProducto(rs.getInt(1));
                r.setNombre(rs.getString(2));
                r.setCategoria(rs.getString(3));
                r.setCantidad(rs.getInt(4));
                lista.add(r);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    public List ListarFecha(String in, String fi) {
        List<Ranking> lista = new ArrayList<>();
        String sql = "SELECT D.idProducto,  P.nombre, P.categoria, SUM(D.cantidad)as ranking\n"
                + "from detalleventa D \n"
                + "join producto P ON D.idProducto = p.idProducto\n"
                + "join ventas V ON D.idVenta = V.idVenta\n"
                + "WHERE V.fechaVentas >= ? and V.fechaVentas <=?\n"
                + "GROUP BY idProducto\n"
                + "ORDER BY sum(cantidad) DESC";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, in);
            ps.setString(2, fi);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ranking r = new Ranking();
                r.setIdProducto(rs.getInt(1));
                r.setNombre(rs.getString(2));
                r.setCategoria(rs.getString(3));
                r.setCantidad(rs.getInt(4));
                lista.add(r);
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
