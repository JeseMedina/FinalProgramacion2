/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import modelo.FlujoCaja;
import modelo.Caja;
import dao.FlujoCajaDAO;
import dao.CajaDAO;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.DetalleFlujoCaja;

/**
 *
 * @author Jesé
 */
public class CajaForm extends javax.swing.JInternalFrame {

    /**
     * Creates new form CajaForm
     */
    FlujoCaja fc = new FlujoCaja();
    FlujoCajaDAO fcdao = new FlujoCajaDAO();
    Caja ca = new Caja();
    CajaDAO caDAO = new CajaDAO();
    int nCaja;
    DefaultTableModel modelo = new DefaultTableModel();
    ImageIcon icon = new ImageIcon("");

    public CajaForm() {
        initComponents();
        this.setFrameIcon(icon);
        cajaAbierta();
        efectivo();
    }
    
    void listar(List<DetalleFlujoCaja> lista) {
        modelo = (DefaultTableModel) tabla.getModel();
        Object[] ob = new Object[3];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getIngreso();
            ob[1] = lista.get(i).getEgreso();
            ob[2] = lista.get(i).getDescripcion();
            modelo.addRow(ob);
        }
        tabla.setModel(modelo);
    }
    
    void limpiarTabla() {
        modelo.getDataVector().removeAllElements();
        modelo.fireTableDataChanged();
    }

    
    void cajaAbierta() {
        int idCaja = caDAO.listarId(Integer.parseInt(Login.nCaja));

        fc.setIdCaja(idCaja);
        fc.setIdVendedor(Login.idVendedor);
        fc.setFecha(fecha());

        if (fcdao.cajaAbierta(fc)) {
            btnAbrir.setEnabled(false);
            txtInicial.setEnabled(false);
            jLabel1.setEnabled(false);

            btnCerrar.setEnabled(true);
            btnDepositar.setEnabled(true);
            btnRetirar.setEnabled(true);
            txtDinero.setEnabled(true);
            listar(fcdao.listarDetalle(fcdao.idCaja(fc)));
        } else {
            btnCerrar.setEnabled(false);
            btnDepositar.setEnabled(false);
            btnRetirar.setEnabled(false);
            txtDinero.setEnabled(false);

            btnAbrir.setEnabled(true);
            txtInicial.setEnabled(true);
            jLabel1.setEnabled(true);
        }
    }

    boolean esNumerico(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    double montoRetiro;
    double efectivo;

    void retirarEfectivo() {
        efectivo = Double.parseDouble(txtEfectivo.getText());

        if (esNumerico(txtDinero.getText())) {
            if (Double.parseDouble(txtDinero.getText()) > efectivo) {
                JOptionPane.showMessageDialog(this, "Monto a retirar insuficiente");
            } else {
                montoRetiro = Double.parseDouble(txtDinero.getText());
                int idCaja = caDAO.listarId(Integer.parseInt(Login.nCaja));
                fc.setIdCaja(idCaja);
                fc.setIdVendedor(Login.idVendedor);
                fc.setFecha(fecha());
                FlujoCaja f = fcdao.efectivo(fc);
                double total = f.getTotal() - montoRetiro;
                double egreso = f.getEgreso() + montoRetiro;
                double ingreso = f.getIngreso();

                FlujoCaja retiro = new FlujoCaja();
                retiro.setIdCaja(idCaja);
                retiro.setIdVendedor(Login.idVendedor);
                retiro.setFecha(fecha());
                retiro.setIngreso(ingreso);
                retiro.setEgreso(egreso);
                retiro.setTotal(total);
                fcdao.actualizarEfectivo(retiro);

                List<FlujoCaja> flujo = fcdao.datosCajaAbierta(fc);
                int idFlujo = flujo.get(0).getIdFLujoCaja();

                fcdao.detalleMovimientoEfetivo(idFlujo, 0, montoRetiro, "Retiro");
                JOptionPane.showMessageDialog(this, "Efectivo retirado");
                limpiar();
                efectivo();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un monto numerico");
        }
    }

    double montoDeposito;

    void depositarEfectivo() {
        if (esNumerico(txtDinero.getText())) {
            montoDeposito = Double.parseDouble(txtDinero.getText());
            int idCaja = caDAO.listarId(Integer.parseInt(Login.nCaja));

            fc.setIdCaja(idCaja);
            fc.setIdVendedor(Login.idVendedor);
            fc.setFecha(fecha());

            FlujoCaja f = fcdao.efectivo(fc);
            double total = f.getTotal() + montoDeposito;
            double ingreso = f.getIngreso() + montoDeposito;
            double egreso = f.getEgreso();

            FlujoCaja deposito = new FlujoCaja();
            deposito.setIdCaja(idCaja);
            deposito.setIdVendedor(Login.idVendedor);
            deposito.setFecha(fecha());
            deposito.setEgreso(egreso);
            deposito.setIngreso(ingreso);
            deposito.setTotal(total);
            fcdao.actualizarEfectivo(deposito);

            List<FlujoCaja> flujo = fcdao.datosCajaAbierta(fc);
            int idFlujo = flujo.get(0).getIdFLujoCaja();

            fcdao.detalleMovimientoEfetivo(idFlujo, montoDeposito, 0, "Deposito");
            JOptionPane.showMessageDialog(this, "Efectivo depositado");
            limpiar();
            efectivo();
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un monto numerico");
        }
    }

    void limpiar() {
        txtDinero.setText("");
        txtEfectivo.setText("");
        txtInicial.setText("");
    }

    void cerrarCaja() {
        int idCaja = caDAO.listarId(Integer.parseInt(Login.nCaja));

        fc.setIdCaja(idCaja);
        fc.setIdVendedor(Login.idVendedor);
        fc.setFecha(fecha());

        List<FlujoCaja> flujo = fcdao.datosCajaAbierta(fc);
        int idFlujo = flujo.get(0).getIdFLujoCaja();
        double total = flujo.get(0).getTotal();
        double inicio = flujo.get(0).getInicial();
        double totalfinal = total - inicio;
        try {
            fcdao.cerrarCaja(idFlujo, totalfinal);
            JOptionPane.showMessageDialog(this, "Caja cerrada");
            limpiar();
            cajaAbierta();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cerrar caja");
        }

    }

    void abrirCaja() {
        if (esNumerico(txtInicial.getText())) {
            double inicio = Double.parseDouble(txtInicial.getText());
            int idCaja = caDAO.listarId(Integer.parseInt(Login.nCaja));

            fc.setIdCaja(idCaja);
            fc.setIdVendedor(Login.idVendedor);
            fc.setFecha("" + fecha());
            fc.setInicial(inicio);
            fc.setIngreso(0.0);
            fc.setEgreso(0.0);
            fc.setTotal(inicio);
            fc.setEstado(1);

            try {
                fcdao.abrirCaja(fc);
                JOptionPane.showMessageDialog(this, "Caja abierta");
                cajaAbierta();
                limpiar();
                efectivo();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un monto numerico");
        }
    }

    String fecha() {
        Calendar calendar = new GregorianCalendar();
        int anno = calendar.get(Calendar.YEAR);
        int mes = calendar.get(calendar.MONTH) + 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        return (anno + "-" + mes + "-" + dia);
    }

    void efectivo() {
        int idCaja = caDAO.listarId(Integer.parseInt(Login.nCaja));
        fc.setIdCaja(idCaja);
        fc.setIdVendedor(Login.idVendedor);
        fc.setFecha("" + fecha());
        FlujoCaja efe = fcdao.efectivo(fc);
        txtEfectivo.setText("" + efe.getTotal());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAbrir = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        txtInicial = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtDinero = new javax.swing.JTextField();
        btnRetirar = new javax.swing.JButton();
        btnDepositar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtEfectivo = new javax.swing.JTextField();

        setClosable(true);
        setForeground(java.awt.Color.gray);
        setTitle("Caja");

        btnAbrir.setText("Abrir Caja");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        jLabel1.setText("Monto Inicial:");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ingreso", "Egreso", "Descripcion"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        btnCerrar.setText("Cerrar Caja");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnRetirar.setText("Retirar");
        btnRetirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetirarActionPerformed(evt);
            }
        });

        btnDepositar.setText("Depositar");
        btnDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRetirar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDepositar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDinero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRetirar)
                    .addComponent(btnDepositar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Efectivo:");

        txtEfectivo.setEditable(false);
        txtEfectivo.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrir)
                    .addComponent(jLabel1)
                    .addComponent(txtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        abrirCaja();
        limpiarTabla();
        listar(fcdao.listarDetalle(fcdao.idCaja(fc)));
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnRetirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarActionPerformed
        retirarEfectivo();
        limpiarTabla();
        listar(fcdao.listarDetalle(fcdao.idCaja(fc)));
    }//GEN-LAST:event_btnRetirarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        cerrarCaja();
        limpiarTabla();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositarActionPerformed
        depositarEfectivo();
        limpiarTabla();
        listar(fcdao.listarDetalle(fcdao.idCaja(fc)));
    }//GEN-LAST:event_btnDepositarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnAbrir;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnDepositar;
    private javax.swing.JButton btnRetirar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtDinero;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtInicial;
    // End of variables declaration//GEN-END:variables
}
