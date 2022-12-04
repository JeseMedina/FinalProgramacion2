/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Conexion;
import modelo.Proveedor;

/**
 *
 * @author Jesé
 */
public class ProveedorDAO implements CRUD {

    Connection con;
    Conexion cn = new Conexion();
    java.sql.PreparedStatement ps;
    ResultSet rs;
    
    public Proveedor listarDni(int id) {
        Proveedor p = new Proveedor();
        String sql = "select * from proveedor where dni=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setDni(rs.getString(2));
                p.setNombre(rs.getString(3));
                p.setTel(rs.getString(4));
                p.setRazonSocial(rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return p;
    }
    
    public List listarDniFiltro(int dni) {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "select * from proveedor where dni=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId(rs.getInt(1));
                p.setDni(rs.getString(2));
                p.setNombre(rs.getString(3));
                p.setTel(rs.getString(4));
                p.setRazonSocial(rs.getString(5));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
    
    public List listarNombre(String nombre) {
        List<Proveedor> lista = new ArrayList<>();
        String filtro = "%" + nombre + "%";
        String sql = "select * from proveedor where nombre like" + '"' + filtro + '"';
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId(rs.getInt(1));
                p.setDni(rs.getString(2));
                p.setNombre(rs.getString(3));
                p.setTel(rs.getString(4));
                p.setRazonSocial(rs.getString(5));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
    
    public List listarRazonSocial(String razon) {
        List<Proveedor> lista = new ArrayList<>();
        String filtro = "%" + razon + "%";
        String sql = "select * from proveedor where razonSocial like" + '"' + filtro + '"';
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId(rs.getInt(1));
                p.setDni(rs.getString(2));
                p.setNombre(rs.getString(3));
                p.setTel(rs.getString(4));
                p.setRazonSocial(rs.getString(5));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    @Override
    public List Listar() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "select * from proveedor";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId(rs.getInt(1));
                p.setDni(rs.getString(2));
                p.setNombre(rs.getString(3));
                p.setTel(rs.getString(4));
                p.setRazonSocial(rs.getString(5));
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
        String sql = "insert into proveedor(dni,nombre,tel,razonSocial)values(?,?,?,?)";
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
        String sql = "update proveedor set dni=?,nombre=?,tel=?,razonSocial=? where idProveedor=?";
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

    @Override
    public void eliminar(int id) {
        String sql = "delete from proveedor where idProveedor=?";
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
