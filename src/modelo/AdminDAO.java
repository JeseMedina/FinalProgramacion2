/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Jesé
 */
public class AdminDAO implements CRUD{

    Connection con;
    Conexion cn = new Conexion();
    java.sql.PreparedStatement ps;
    ResultSet rs;

    public int actualizarAdmin(Object[] o){
        int r = 0;
        String sql = "update admin set user=?,contrasena=? where idAdmin=?";
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
    
    public Admin ValidarAdmin(String contrasena, String user) {
        Admin a = new Admin();
        String sql = "select * from admin where contrasena=? and user=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, contrasena);
            ps.setString(2, user);
            rs = ps.executeQuery();
            
            while (rs.next()){
                a.setId(rs.getInt(1));
                a.setUser(rs.getString(2));
                a.setContrasena(rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return a;
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
