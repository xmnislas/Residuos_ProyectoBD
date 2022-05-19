/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import interfaces.INegocios;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import negocios.FabricaNegocios;

/**
 *
 * @author xmnislas
 */
public class FrmMenu extends javax.swing.JFrame {
    INegocios iNegocios;
    /**
     * Creates new form FrmMenu
     */
    public FrmMenu() {
        initComponents();
        this.getContentPane().setBackground(new Color(202, 240, 248));
        setLocationRelativeTo(null);
        iNegocios = FabricaNegocios.crearNegocios();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor. 
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegistrarResiduo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnSolicitarTraslado = new javax.swing.JButton();
        btnAsignarTraslados = new javax.swing.JButton();
        btnRegistrarTraslado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Residuos Peligrosos");

        btnRegistrarResiduo.setBackground(new java.awt.Color(247, 249, 249));
        btnRegistrarResiduo.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        btnRegistrarResiduo.setForeground(new java.awt.Color(2, 62, 138));
        btnRegistrarResiduo.setText("Registrar Residuo");
        btnRegistrarResiduo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarResiduoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(3, 4, 94));
        jLabel1.setText("Menú");

        btnSalir.setBackground(new java.awt.Color(247, 249, 249));
        btnSalir.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(2, 62, 138));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnSolicitarTraslado.setBackground(new java.awt.Color(247, 249, 249));
        btnSolicitarTraslado.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        btnSolicitarTraslado.setForeground(new java.awt.Color(2, 62, 138));
        btnSolicitarTraslado.setText("Solicitar Traslado");
        btnSolicitarTraslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitarTrasladoActionPerformed(evt);
            }
        });

        btnAsignarTraslados.setBackground(new java.awt.Color(247, 249, 249));
        btnAsignarTraslados.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        btnAsignarTraslados.setForeground(new java.awt.Color(2, 62, 138));
        btnAsignarTraslados.setText("Asignar Traslados");
        btnAsignarTraslados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarTrasladosActionPerformed(evt);
            }
        });

        btnRegistrarTraslado.setBackground(new java.awt.Color(247, 249, 249));
        btnRegistrarTraslado.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        btnRegistrarTraslado.setForeground(new java.awt.Color(2, 62, 138));
        btnRegistrarTraslado.setText("Registrar Traslado");
        btnRegistrarTraslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarTrasladoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRegistrarTraslado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(34, 34, 34))
                    .addComponent(btnAsignarTraslados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSolicitarTraslado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrarResiduo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegistrarResiduo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSolicitarTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAsignarTraslados, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegistrarTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarResiduoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarResiduoActionPerformed
        FrmRegistrarResiduo frmRegistrarResiduo = FrmRegistrarResiduo.getInstance(this);
        frmRegistrarResiduo.llenarDatos(iNegocios);
    }//GEN-LAST:event_btnRegistrarResiduoActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnSolicitarTrasladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitarTrasladoActionPerformed
        FrmSolicitarTraslado frmSolicitarTraslado = FrmSolicitarTraslado.getInstance(this);
        frmSolicitarTraslado.llenarDatos(iNegocios);
    }//GEN-LAST:event_btnSolicitarTrasladoActionPerformed

    private void btnAsignarTrasladosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarTrasladosActionPerformed
        FrmAsignarTraslados frmAsignarTraslados = FrmAsignarTraslados.getInstance(this);
        frmAsignarTraslados.llenarDatos(iNegocios);
    }//GEN-LAST:event_btnAsignarTrasladosActionPerformed

    private void btnRegistrarTrasladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarTrasladoActionPerformed
        FrmRegistrarTraslado frmRegistrarTraslado = FrmRegistrarTraslado.getInstance(this);
        frmRegistrarTraslado.llenarDatos(iNegocios);
    }//GEN-LAST:event_btnRegistrarTrasladoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FrmMenu().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignarTraslados;
    private javax.swing.JButton btnRegistrarResiduo;
    private javax.swing.JButton btnRegistrarTraslado;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSolicitarTraslado;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
