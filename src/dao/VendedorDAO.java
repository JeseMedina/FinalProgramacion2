/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Conexion.Conexion;
import modelo.Vendedor;

/**
 *
 * @author Jesé
 */
public class VendedorDAO implements CRUD {

    Connection con;
    Conexion cn = new Conexion();
    java.sql.PreparedStatement ps;
    ResultSet rs;

    public Vendedor ValidarVendedor(String dni, String user) {
        Vendedor v = new Vendedor();
        String sql = "select * from vendedor where dni=? and user=?";
        try {
            con = cn.Conectar();
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, dni);
            ps.setString(2, user);
            rs = ps.executeQuery();

            while (rs.next()) {
                v.setId(rs.getInt(1));
                v.setDni(rs.getString(2));
                v.setNombre(rs.getString(3));
                v.setTel(rs.getString(4));
                v.setUser(rs.getString(5));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return v;
    }

    public List listarDni(int dni) {
        List<Vendedor> lista = new ArrayList<>();
        String sql = "select * from vendedor where dni=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vendedor v = new Vendedor();
                v.setId(rs.getInt(1));
                v.setDni(rs.getString(2));
                v.setNombre(rs.getString(3));
                v.setTel(rs.getString(4));
                v.setUser(rs.getString(5));
                lista.add(v);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
    
    public String listarId(int id){
        String nombre = null;
        String sql = "select nombre from vendedor where idVendedor = ?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                nombre = rs.getString(1);
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return nombre;
    }

    public List listarNombre(String nombre) {
        List<Vendedor> lista = new ArrayList<>();
        String filtro = "%" + nombre + "%";
        String sql = "select * from vendedor where nombre like" + '"' + filtro + '"';
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vendedor v = new Vendedor();
                v.setId(rs.getInt(1));
                v.setDni(rs.getString(2));
                v.setNombre(rs.getString(3));
                v.setTel(rs.getString(4));
                v.setUser(rs.getString(5));
                lista.add(v);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    @Override
    public List Listar() {
        List<Vendedor> lista = new ArrayList<>();
        String sql = "select * from vendedor";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vendedor v = new Vendedor();
                v.setId(rs.getInt(1));
                v.setDni(rs.getString(2));
                v.setNombre(rs.getString(3));
                v.setTel(rs.getString(4));
                v.setUser(rs.getString(5));
                lista.add(v);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = "insert into vendedor(dni,nombre,tel,user)values(?,?,?,?)";
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
        String sql = "update vendedor set dni=?,nombre=?,tel=?,user=? where idVendedor=?";
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
        String sql = "delete from vendedor where idVendedor=?";
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
