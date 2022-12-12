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
import modelo.Caja;
import modelo.FlujoCaja;
import vistas.Login;

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

    Caja ca = new Caja();
    CajaDAO caDAO = new CajaDAO();

    public boolean cajaAbierta(FlujoCaja fc) {
        String sql = "select idflujocaja from flujocaja where idCaja=? and idVendedor=? and fecha=? and estado=1";
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

    public List datosCajaAbierta(FlujoCaja fc) {
        List<FlujoCaja> lista = new ArrayList<>();
        String sql = "select * from flujocaja where idCaja=? and idVendedor=? and fecha=? and estado=1";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, fc.getIdCaja());
            ps.setInt(2, fc.getIdVendedor());
            ps.setString(3, fc.getFecha());

            rs = ps.executeQuery();
            while (rs.next()) {
                FlujoCaja f = new FlujoCaja();
                f.setIdFLujoCaja(rs.getInt(1));
                f.setIdCaja(rs.getInt(2));
                f.setIdVendedor(rs.getInt(3));
                f.setFecha(rs.getString(4));
                f.setInicial(rs.getDouble(5));
                f.setIngreso(rs.getDouble(6));
                f.setEgreso(rs.getDouble(7));
                f.setTotal(rs.getDouble(8));
                f.setEstado(rs.getInt(9));
                lista.add(f);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    public int abrirCaja(FlujoCaja fc) {
        String sql = "insert into flujocaja(idCaja,idVendedor,fecha,inicial,ingreso,egreso,total,estado) values(?,?,?,?,?,?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, fc.getIdCaja());
            ps.setInt(2, fc.getIdVendedor());
            ps.setString(3, fc.getFecha());
            ps.setDouble(4, fc.getInicial());
            ps.setDouble(5, 0);
            ps.setDouble(6, 0);
            ps.setDouble(7, 0);
            ps.setInt(8, 1);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }

    public FlujoCaja efectivo(FlujoCaja fc) {
        FlujoCaja f = new FlujoCaja();
        int idCaja = caDAO.listarId(Integer.parseInt(Login.nCaja));
        double total = 0;
        String sql = "select * from flujocaja where idCaja=? and idVendedor=? and fecha=? and estado=1";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCaja);
            ps.setInt(2, fc.getIdVendedor());
            ps.setString(3, fc.getFecha());

            rs = ps.executeQuery();
            while (rs.next()) {
                f.setIdFLujoCaja(rs.getInt(1));
                f.setIdCaja(rs.getInt(2));
                f.setIdVendedor(rs.getInt(3));
                f.setFecha(rs.getString(4));
                f.setInicial(rs.getDouble(5));
                f.setIngreso(rs.getDouble(6));
                f.setEgreso(rs.getDouble(7));
                f.setTotal(rs.getDouble(8));
                f.setEstado(rs.getInt(9));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return f;
    }
    
    public int detalleRetirarEfetivo(int idFlujo,double ingreso,double egreso,String descripcion){
        int r = 0;
        String sql = "insert into detalleflujocaja (idFlujoCaja,ingreso,egreso,descripcion) values(?,?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, idFlujo);
            ps.setDouble(2, ingreso);
            ps.setDouble(3, egreso);
            ps.setString(4, descripcion);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }
    
    public int retirarEfectivo(FlujoCaja fc) {
        int r = 0;
        String sql = "update flujocaja set egreso=?,total=? where idCaja=? and idVendedor=? and fecha=? and estado=1";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            
            ps.setDouble(1, fc.getEgreso());
            ps.setDouble(2, fc.getTotal());
            ps.setInt(3, fc.getIdCaja());
            ps.setInt(4, fc.getIdVendedor());
            ps.setString(5, fc.getFecha());
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
