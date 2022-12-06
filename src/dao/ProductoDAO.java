/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Conexion.Conexion;
import modelo.Producto;

/**
 *
 * @author Jesé
 */
public class ProductoDAO implements CRUD {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public int actualizarStock(int cant, int idp) {
        int r = 0;
        String sql = "update producto set stock=? where idProducto=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, idp);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }

    public Producto listarId(int id) {
        Producto p = new Producto();
        String sql = "select * from producto where idProducto=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setCategoria(rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return p;
    }

    public List listarIdFiltro(int id) {
        List<Producto> lista = new ArrayList();
        String sql = "select * from producto where idProducto=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setCategoria(rs.getString(5));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    public List listarNombre(String nombre) {
        List<Producto> lista = new ArrayList();
        String filtro = "%" + nombre + "%";
        String sql = "select * from producto where nombre like" + '"' + filtro + '"';
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setCategoria(rs.getString(5));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    @Override
    public List Listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "select * from producto";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setPrecio(rs.getDouble(3));
                p.setStock(rs.getInt(4));
                p.setCategoria(rs.getString(5));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = "insert into producto(nombre,precio,stock,categoria)values(?,?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }

    @Override
    public int actualizar(Object[] o) {
        int r = 0;
        String sql = "update producto set nombre=?,precio=?,stock=?,categoria=? where idProducto=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }
    public int actualizarPrecio(Producto p) {
        Producto producto = new Producto();
        int r = 0;
        String sql = "update producto set precio=? where idProducto=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, p.getPrecio());
            ps.setInt(2,p.getId());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }

    @Override
    public void eliminar(int id) {
        String sql = "delete from producto where idProducto=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
