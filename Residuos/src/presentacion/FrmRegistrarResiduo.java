/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import entidades.Productor;
import entidades.Quimico;
import entidades.Residuo;
import interfaces.INegocios;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class FrmRegistrarResiduo extends javax.swing.JFrame {

    private INegocios iNegocios;
    private static FrmRegistrarResiduo instancia;
    private DefaultListModel modeloListDisponibles;
    private DefaultListModel modeloListSeleccionados = new DefaultListModel();

    /**
     * Creates new form FrmRegistrarResiduo
     */
    public FrmRegistrarResiduo() {
        initComponents();
        this.getContentPane().setBackground(new Color(202, 240, 248));
    }

    /**
     * Metodo para obtener FrmRegistrarResiduo
     *
     * @param parent
     * @return
     */
    public static FrmRegistrarResiduo getInstance(java.awt.Frame parent) {
        if (instancia == null) {
            instancia = new FrmRegistrarResiduo();
        }
        return instancia;
    }

    /**
     * Metodo para llenar Datos
     *
     * @param iNegocios
     */
    public void llenarDatos(INegocios iNegocios) {
        this.iNegocios = iNegocios;

        List<Productor> productores = this.iNegocios.buscarProductores();
        if (productores.isEmpty()) {
            this.mostrarMsjError("No se pudieron consultar los productores.");
            this.dispose();
            return;
        }
        DefaultComboBoxModel modelo = new DefaultComboBoxModel(productores.toArray());
        this.cbxProductores.setModel(modelo);

        List<Quimico> quimicos = this.iNegocios.buscarQuimicos();

        if (quimicos.isEmpty()) {
            this.mostrarMsjError("No se pudieron consultar los quimicos.");
            this.dispose();
            return;
        }

        modeloListDisponibles = new DefaultListModel();
        quimicos.forEach(quimico -> {
            modeloListDisponibles.addElement(quimico);
        });
        this.listQuimicosDisponibles.setModel(modeloListDisponibles);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Metodo para agregarQuimico
     */
    private void agregarQuimico() {
        int filaSeleccionada = listQuimicosDisponibles.getSelectedIndex();
        if (filaSeleccionada > -1) {
            modeloListSeleccionados.addElement(listQuimicosDisponibles.getSelectedValue());
            modeloListDisponibles = (DefaultListModel) listQuimicosDisponibles.getModel();
            modeloListDisponibles.remove(filaSeleccionada);
            listQuimicosDisponibles.setModel(modeloListDisponibles);
            listQuimicosSeleccionados.setModel(modeloListSeleccionados);
        }
    }

    /**
     * Metodo para eliminarQuimico
     */
    private void eliminarQuimico() {
        int filaSeleccionada = listQuimicosSeleccionados.getSelectedIndex();
        if (filaSeleccionada > -1) {
            modeloListDisponibles.addElement(listQuimicosSeleccionados.getSelectedValue());
            modeloListSeleccionados = (DefaultListModel) listQuimicosSeleccionados.getModel();
            modeloListSeleccionados.remove(filaSeleccionada);
            listQuimicosSeleccionados.setModel(modeloListSeleccionados);
            listQuimicosDisponibles.setModel(modeloListDisponibles);
        }
    }

    /**
     * Metodo para guardar
     */
    private void guardar() {
        if (this.validarResiduo()) {
            String nombre = this.txtNombre.getText();
            String codigo = this.txtCodigo.getText();
            List<ObjectId> idsQuimicos = new ArrayList<>();
            ObjectId idProductor = ((Productor) this.cbxProductores.getSelectedItem()).getId();

            for (int i = 0; i < this.modeloListSeleccionados.getSize(); i++) {
                idsQuimicos.add(((Quimico) this.modeloListSeleccionados.getElementAt(i)).getId());
            }

            Residuo residuoNuevo = new Residuo(nombre, codigo, idsQuimicos, idProductor);

            if (this.verificarExistencia(residuoNuevo)) {
                iNegocios.guadarResiduo(residuoNuevo);
                mostrarMsjConfirmacion(nombre);
                this.limpiarFormulario();
            }

        }
    }

    /**
     * Metodo para limpiarFormulario
     */
    private void limpiarFormulario() {
        this.txtCodigo.setText("");
        this.txtNombre.setText("");
        this.cbxProductores.setSelectedIndex(0);
        for (int i = 0; i < modeloListSeleccionados.getSize(); i++) {
            this.modeloListDisponibles.addElement(modeloListSeleccionados.get(i));
        }
        this.modeloListSeleccionados.removeAllElements();
        this.listQuimicosSeleccionados.setModel(modeloListSeleccionados);
        this.listQuimicosDisponibles.setModel(modeloListDisponibles);
    }

    /**
     * Metodo para validarResiduo
     *
     * @return
     */
    private boolean validarResiduo() {
        if (this.txtNombre.getText().isEmpty() || this.txtCodigo.getText().isEmpty()) {
            this.mostrarMsjAdvertencia("No es posible registrar campos vacíos.");
            return false;
        }

        if (this.modeloListSeleccionados.isEmpty()) {
            this.mostrarMsjAdvertencia("Seleccione al menos un químico.");
            return false;
        }

        return true;
    }

    /**
     * Metodo para verificarExistencia
     *
     * @param residuoNuevo
     * @return
     */
    private boolean verificarExistencia(Residuo residuoNuevo) {
        List<Residuo> residuos = iNegocios.buscarResiduos();

        for (int i = 0; i < residuos.size(); i++) {
            if (residuos.get(i).getNombre().equals(residuoNuevo.getNombre())) {
                this.mostrarMsjAdvertencia("Ya existe un residuo con este nombre.");
                return false;
            }
            if (residuos.get(i).getIdsQuimicos().equals(residuoNuevo.getIdsQuimicos())) {
                this.mostrarMsjAdvertencia("Ya existe un residuo con los mismos quimicos");
                return false;
            }
            if (residuos.get(i).getCodigo().equals(residuoNuevo.getCodigo())) {
                this.mostrarMsjAdvertencia("Ya existe un residuo con este codigo.");
                return false;
            }
//            if (!validarNumeros(residuoNuevo.getCodigo())) {
//                this.mostrarMsjAdvertencia("El código solo puede contener valores numéricos y máximo 6 numeros.");
//                return false;
//            } else {
//                return true;
//            }
        }
        return true;
    }

    /**
     * Metodo para mostrarMsjConfirmacion
     * @param nombre 
     */
    private void mostrarMsjConfirmacion(String nombre) {
        JOptionPane.showMessageDialog(this, "Se ha registrado el residuo " + nombre + ".", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Metodo para mostrarMsjAdvertencia
     * @param mensaje 
     */
    private void mostrarMsjAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Metodo para mostrarMsjError
     * @param mensaje 
     */
    private void mostrarMsjError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombre = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        pnlQuimicos = new javax.swing.JPanel();
        pnlQuimicosDisponibles = new javax.swing.JScrollPane();
        listQuimicosDisponibles = new javax.swing.JList<>();
        pnlQuimicosSeleccionados = new javax.swing.JScrollPane();
        listQuimicosSeleccionados = new javax.swing.JList<>();
        lblProductor = new javax.swing.JLabel();
        cbxProductores = new javax.swing.JComboBox<>();
        btnVolver = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrar Residuo");

        lblNombre.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(2, 62, 138));
        lblNombre.setText("Nombre");

        lblCodigo.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(2, 62, 138));
        lblCodigo.setText("Código");

        txtNombre.setToolTipText("");

        btnGuardar.setText("Guardar");
        btnGuardar.setBackground(new java.awt.Color(247, 249, 249));
        btnGuardar.setFont(new java.awt.Font("Arial Narrow", 1, 13)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(3, 4, 94));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(247, 249, 249));
        btnCancelar.setFont(new java.awt.Font("Arial Narrow", 1, 13)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(3, 4, 94));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(3, 4, 94));
        lblTitulo.setText("Registro de Residuos");

        pnlQuimicos.setBackground(new java.awt.Color(202, 240, 248));
        pnlQuimicos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Químicos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 14), new java.awt.Color(2, 62, 138))); // NOI18N

        pnlQuimicosDisponibles.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 12), new java.awt.Color(3, 4, 94))); // NOI18N

        listQuimicosDisponibles.setBackground(new java.awt.Color(250, 245, 245));
        listQuimicosDisponibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listQuimicosDisponiblesMouseClicked(evt);
            }
        });
        pnlQuimicosDisponibles.setViewportView(listQuimicosDisponibles);

        pnlQuimicosSeleccionados.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Seleccionados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 12), new java.awt.Color(3, 4, 94))); // NOI18N

        listQuimicosSeleccionados.setBackground(new java.awt.Color(249, 246, 246));
        listQuimicosSeleccionados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listQuimicosSeleccionadosMouseClicked(evt);
            }
        });
        pnlQuimicosSeleccionados.setViewportView(listQuimicosSeleccionados);

        javax.swing.GroupLayout pnlQuimicosLayout = new javax.swing.GroupLayout(pnlQuimicos);
        pnlQuimicos.setLayout(pnlQuimicosLayout);
        pnlQuimicosLayout.setHorizontalGroup(
            pnlQuimicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuimicosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlQuimicosDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlQuimicosSeleccionados, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlQuimicosLayout.setVerticalGroup(
            pnlQuimicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlQuimicosLayout.createSequentialGroup()
                .addGroup(pnlQuimicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlQuimicosSeleccionados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(pnlQuimicosDisponibles, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        lblProductor.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        lblProductor.setForeground(new java.awt.Color(2, 62, 138));
        lblProductor.setText("Productor");

        btnVolver.setBackground(new java.awt.Color(247, 249, 249));
        btnVolver.setFont(new java.awt.Font("Arial Narrow", 1, 13)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(3, 4, 94));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(209, 209, 209))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblCodigo)
                                .addGap(35, 35, 35)
                                .addComponent(txtCodigo))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblProductor)
                                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(txtNombre))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(cbxProductores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(18, 18, 18)
                        .addComponent(pnlQuimicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxProductores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProductor))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCodigo))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnVolver)
                            .addComponent(btnCancelar)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlQuimicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listQuimicosDisponiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listQuimicosDisponiblesMouseClicked
        if (evt.getClickCount() == 1) {
            this.agregarQuimico();
        }
    }//GEN-LAST:event_listQuimicosDisponiblesMouseClicked

    private void listQuimicosSeleccionadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listQuimicosSeleccionadosMouseClicked
        if (evt.getClickCount() == 1) {
            this.eliminarQuimico();
        }
    }//GEN-LAST:event_listQuimicosSeleccionadosMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        this.guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.limpiarFormulario();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.instancia = null;
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cbxProductores;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblProductor;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<String> listQuimicosDisponibles;
    private javax.swing.JList<String> listQuimicosSeleccionados;
    private javax.swing.JPanel pnlQuimicos;
    private javax.swing.JScrollPane pnlQuimicosDisponibles;
    private javax.swing.JScrollPane pnlQuimicosSeleccionados;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
