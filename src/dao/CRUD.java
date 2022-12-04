/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Jesé
 */
public interface CRUD {
    public List Listar();
    public int add(Object[] o);
    public int actualizar(Object[] o);
    public void eliminar (int id);
    
}
