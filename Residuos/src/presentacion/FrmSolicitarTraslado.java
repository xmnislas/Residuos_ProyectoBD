/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import entidades.Productor;
import entidades.Residuo;
import entidades.ResiduoSolicitud;
import entidades.SolicitudTraslado;
import entidades.UnidadMedida;
import interfaces.INegocios;
import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;
import presentacion.utils.CeldaRenderer;

/**
 *
 * @author xmnislas
 */
public class FrmSolicitarTraslado extends javax.swing.JFrame {
    private INegocios iNegocios;
    private static FrmSolicitarTraslado instancia;
    private DefaultListModel modeloListDisponibles;
    /**
     * Creates new form FrmSolicitarTraslado
     */
    public FrmSolicitarTraslado(){
        initComponents();
        this.getContentPane().setBackground(new Color(202, 240, 248));
        
    }
    
    /**
     * Metodo para obtener FrmSolicitarTraslado
     * @param parent
     * @return 
     */
    public static FrmSolicitarTraslado getInstance(java.awt.Frame parent) {
        if (instancia == null) {
            instancia = new FrmSolicitarTraslado();
        }
        return instancia;
    }
    
    /**
     * Metodo para llenar datos
     * @param iNegocios 
     */
    public void llenarDatos(INegocios iNegocios){
        this.iNegocios = iNegocios;
        List<Productor> productores = this.iNegocios.buscarProductores();
        if(productores.isEmpty()){
            this.mostrarMsjError("No se pudieron consultar los productores.");
            this.dispose();
            return;
        }
        
        DefaultComboBoxModel modelo = new DefaultComboBoxModel(productores.toArray());
        this.cbxProductores.setModel(modelo);
        
        this.llenarTablaResiduos();
        this.deshabilitarCampos();
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
    
    /**
     * Metodo para buscar
     */
    private void buscar(){
        ObjectId idProductor = ((Productor)this.cbxProductores.getSelectedItem()).getId();
        List<Residuo> residuos = iNegocios.buscarResiduosProductor(idProductor);
        
        if(residuos.isEmpty()){
            JOptionPane.showMessageDialog(this, "No hay residuos registrados por el productor.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        modeloListDisponibles = new DefaultListModel();
        residuos.forEach(residuo -> {
            modeloListDisponibles.addElement(residuo);
        });
        this.listResiduosDisponibles.setModel(modeloListDisponibles);
        this.habilitarCampos();
    }
    
    /**
     * Metodo para guardar
     */
    private void guardar(){
        if(this.validarCampos()){
            DefaultTableModel modeloTablaResiduos = (DefaultTableModel) this.tblResiduosSeleccionados.getModel();
            List<ResiduoSolicitud> residuos = new ArrayList<>();
            LocalDate stringFecha = this.dtFecha.getDate();
            Date fecha = new Date(stringFecha.getYear() - 1900, stringFecha.getMonthValue() - 1, stringFecha.getDayOfMonth());
            
            for(int i = 0; i < modeloTablaResiduos.getRowCount(); i++){
                ObjectId idResiduo = (ObjectId) modeloTablaResiduos.getValueAt(i, 0);
                Residuo residuo = iNegocios.buscarResiduo(idResiduo);
                float cantidad = Float.parseFloat(modeloTablaResiduos.getValueAt(i, 3).toString());
                UnidadMedida unidad = (UnidadMedida) modeloTablaResiduos.getValueAt(i, 4);
                ResiduoSolicitud residuoSolicitud = new ResiduoSolicitud(residuo, cantidad, unidad, false);
                residuos.add(residuoSolicitud);
            }
            Productor productor = iNegocios.buscarProductor(((Productor)(this.cbxProductores.getSelectedItem())).getId());
            SolicitudTraslado solicitud = new SolicitudTraslado(fecha, residuos, productor, false);
            if(!this.verificarSolicitudes(solicitud)) return;
            iNegocios.guadarSolicitudTraslado(solicitud);
            mostrarMsjConfirmacion();
            this.limpiarFormulario();   
        }
    }
    
    /**
     * Metodo para agregarResiduoSeleccionado
     */
    private void agregarResiduoSeleccionado(){
        DefaultTableModel modeloTablaResiduos = (DefaultTableModel)this.tblResiduosSeleccionados.getModel();
        int filaSeleccionada = listResiduosDisponibles.getSelectedIndex();
        if (filaSeleccionada > -1) {
            Residuo residuo = (Residuo) modeloListDisponibles.getElementAt(filaSeleccionada);
            Object[] fila = new Object[5];
            fila[0] = residuo.getId();
            fila[1] = residuo.getCodigo();
            fila[2] = residuo.getNombre();
            modeloTablaResiduos.addRow(fila);
           
            modeloListDisponibles = (DefaultListModel) listResiduosDisponibles.getModel();
            modeloListDisponibles.remove(filaSeleccionada);            
            listResiduosDisponibles.setModel(modeloListDisponibles);
        }
    }
    
    /**
     * Metodo para eliminarResiduoSeleccionado
     */
    private void eliminarResiduoSeleccionado(){
        DefaultTableModel modeloTablaResiduosSeleccionados = (DefaultTableModel)this.tblResiduosSeleccionados.getModel();
        this.tblResiduosSeleccionados.getSelectedRow();
        int filaSeleccionada = this.tblResiduosSeleccionados.getSelectedRow();
        ObjectId idResiduo = (ObjectId) this.tblResiduosSeleccionados.getModel().getValueAt(filaSeleccionada, 0);
        Residuo residuo = iNegocios.buscarResiduo(idResiduo);
        if (filaSeleccionada > -1) {
            modeloListDisponibles.addElement(residuo);
            listResiduosDisponibles.setModel(modeloListDisponibles);
            
            modeloTablaResiduosSeleccionados.removeRow(filaSeleccionada);
            tblResiduosSeleccionados.setModel(modeloTablaResiduosSeleccionados);
        }
    }
    
    /**
     * Metodo para llenarTablaResiduos
     */
    private void llenarTablaResiduos(){
        JComboBox cbxUnidadMedida = new JComboBox();
        cbxUnidadMedida.setModel(new DefaultComboBoxModel(UnidadMedida.values()));
        
        this.tblResiduosSeleccionados.setRowHeight(25);
        this.tblResiduosSeleccionados.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cbxUnidadMedida));
        this.tblResiduosSeleccionados.setDefaultRenderer(Object.class, new CeldaRenderer(4));
        
        tblResiduosSeleccionados.getColumnModel().getColumn(0).setMaxWidth(0);
        tblResiduosSeleccionados.getColumnModel().getColumn(0).setMinWidth(0);
        tblResiduosSeleccionados.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    
    /**
     * Metodo para limpiarFormulario
     */
    private void limpiarFormulario(){
        DefaultTableModel modeloTblSeleccionados = (DefaultTableModel) this.tblResiduosSeleccionados.getModel();
        this.dtFecha.setText("");
        this.cbxProductores.setSelectedIndex(0);
        this.modeloListDisponibles.removeAllElements();
        this.listResiduosDisponibles.setModel(modeloListDisponibles);
        for (int i = 0; i < tblResiduosSeleccionados.getRowCount(); i++) {
            modeloTblSeleccionados.removeRow(i);
            i-=1;
        }
        this.deshabilitarCampos();
    }
    
    /**
     * Metodo para deshabilitarCampos
     */
    private void deshabilitarCampos(){
        this.dtFecha.setEnabled(false);
        this.btnSolicitar.setEnabled(false);
        this.btnCancelar.setEnabled(false);
        this.listResiduosDisponibles.setEnabled(false);
        this.tblResiduosSeleccionados.setEnabled(false);
        this.btnBuscar.setEnabled(true);
        this.cbxProductores.setEnabled(true);
    }
    
    /**
     * Metodo para habilitarCampos
     */
    private void habilitarCampos(){
        this.dtFecha.setEnabled(true);
        this.btnSolicitar.setEnabled(true);
        this.btnCancelar.setEnabled(true);
        this.listResiduosDisponibles.setEnabled(true);
        this.tblResiduosSeleccionados.setEnabled(true);
        this.btnBuscar.setEnabled(false);
        this.cbxProductores.setEnabled(false);
    }
    
    /**
     * Metodo para validarCampos
     * @return 
     */
    private boolean validarCampos(){
        if(this.dtFecha.getDate() == null){
            this.mostrarMsjAdvertencia("Ingrese la fecha de traslado.");
            return false;
        }
        
        DefaultTableModel model = (DefaultTableModel) this.tblResiduosSeleccionados.getModel();
        LocalDate stringFecha = this.dtFecha.getDate();
        Date fecha = new Date(stringFecha.getYear() - 1900, stringFecha.getMonthValue() - 1, stringFecha.getDayOfMonth());
        Date fechaHoy = new Date();
        
        if(fecha.compareTo(fechaHoy) <= 0){
            this.mostrarMsjAdvertencia("La fecha de traslado debe ser mayor a la actual.");
            return false;
        }
        
        if(this.tblResiduosSeleccionados.getRowCount() == 0){
            this.mostrarMsjAdvertencia("Seleccione al menos un residuo.");
            return false;
        }
        
        for (int i = 0; i < tblResiduosSeleccionados.getRowCount(); i++) {
            String cantidad = (String) model.getValueAt(i, 3);
            if(cantidad == null){
                this.mostrarMsjAdvertencia("Ingrese la cantidad de cada residuo.");
                return false;
            }
            
            try {  
                Float.parseFloat(cantidad);
            } catch(NumberFormatException e){  
                this.mostrarMsjAdvertencia("La cantidad de residuo debe ser de caracteres numéricos.");
                return false;  
            }
            
            if(model.getValueAt(i, 4) == null){
                this.mostrarMsjAdvertencia("Ingrese la unidad de medida de cada residuo.");
                return false;
            }
        }
        return true;
    }
    
    /**
     * Metodo para verificarSolicitudes
     * @param solicitudTraslado
     * @return 
     */
    private boolean verificarSolicitudes(SolicitudTraslado solicitudTraslado){
        List<SolicitudTraslado> solicitudes = iNegocios.buscarSolicitudesTraslado();
        int contFecha = 0;
        
        for(int i = 0; i < solicitudes.size(); i++){
            if(solicitudes.get(i).getFecha() == solicitudTraslado.getFecha()){
                contFecha++;
            }
        }
 
        if(contFecha >= 5) {
            this.mostrarMsjAdvertencia("No hay disponibilidad para la fecha seleccionada.");
            return false;
        }
        return true;
    }
    
    /**
     * Metodo para mostrarMsjConfirmacion
     */
    private void mostrarMsjConfirmacion(){
         JOptionPane.showMessageDialog(this, "Se ha registrado la solicitud de traslado. ", "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Metodo para mostrarMsjAdvertencia
     * @param mensaje 
     */
    private void mostrarMsjAdvertencia(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Metodo para mostrarMsjError
     * @param mensaje 
     */
    private void mostrarMsjError(String mensaje){
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

        lblTitulo = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        btnSolicitar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        pnlResiduos = new javax.swing.JPanel();
        pnlResiduosSeleccionados = new javax.swing.JScrollPane();
        tblResiduosSeleccionados = new javax.swing.JTable();
        pnlResiduosDisponibles = new javax.swing.JScrollPane();
        listResiduosDisponibles = new javax.swing.JList<>();
        lblProductor = new javax.swing.JLabel();
        cbxProductores = new javax.swing.JComboBox<>();
        dtFecha = new com.github.lgooddatepicker.components.DatePicker();
        btnBuscar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Solicitar Traslado");

        lblTitulo.setText("Solicitar Traslado de Residuos");
        lblTitulo.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(3, 4, 94));

        lblFecha.setText("Fecha");
        lblFecha.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(2, 62, 138));

        btnSolicitar.setText("Solicitar");
        btnSolicitar.setBackground(new java.awt.Color(247, 249, 249));
        btnSolicitar.setFont(new java.awt.Font("Arial Narrow", 1, 13)); // NOI18N
        btnSolicitar.setForeground(new java.awt.Color(3, 4, 94));
        btnSolicitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.setBackground(new java.awt.Color(247, 249, 249));
        btnCancelar.setFont(new java.awt.Font("Arial Narrow", 1, 13)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(3, 4, 94));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver");
        btnVolver.setBackground(new java.awt.Color(247, 249, 249));
        btnVolver.setFont(new java.awt.Font("Arial Narrow", 1, 13)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(3, 4, 94));
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        pnlResiduos.setBackground(new java.awt.Color(202, 240, 248));
        pnlResiduos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Residuos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 14), new java.awt.Color(2, 62, 138))); // NOI18N

        pnlResiduosSeleccionados.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Seleccionados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 12), new java.awt.Color(3, 4, 94))); // NOI18N

        tblResiduosSeleccionados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Código", "Nombre", "Cantidad", "Unidad de Medida"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblResiduosSeleccionados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblResiduosSeleccionadosMouseClicked(evt);
            }
        });
        pnlResiduosSeleccionados.setViewportView(tblResiduosSeleccionados);

        pnlResiduosDisponibles.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 12), new java.awt.Color(3, 4, 94))); // NOI18N

        listResiduosDisponibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listResiduosDisponiblesMouseClicked(evt);
            }
        });
        pnlResiduosDisponibles.setViewportView(listResiduosDisponibles);

        javax.swing.GroupLayout pnlResiduosLayout = new javax.swing.GroupLayout(pnlResiduos);
        pnlResiduos.setLayout(pnlResiduosLayout);
        pnlResiduosLayout.setHorizontalGroup(
            pnlResiduosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResiduosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlResiduosDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlResiduosSeleccionados, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        pnlResiduosLayout.setVerticalGroup(
            pnlResiduosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResiduosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResiduosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlResiduosSeleccionados, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(pnlResiduosDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                .addContainerGap())
        );

        lblProductor.setText("Productor");
        lblProductor.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        lblProductor.setForeground(new java.awt.Color(2, 62, 138));

        dtFecha.setBackground(new java.awt.Color(202, 240, 248));

        btnBuscar.setText("Buscar");
        btnBuscar.setBackground(new java.awt.Color(247, 249, 249));
        btnBuscar.setFont(new java.awt.Font("Arial Narrow", 1, 13)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(3, 4, 94));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(btnSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlResiduos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblProductor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxProductores, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(lblFecha)
                        .addGap(18, 18, 18)
                        .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(144, 144, 144))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductor)
                    .addComponent(cbxProductores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFecha)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addComponent(pnlResiduos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSolicitar)
                    .addComponent(btnCancelar)
                    .addComponent(btnVolver))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        this.buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void listResiduosDisponiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listResiduosDisponiblesMouseClicked
        if(evt.getClickCount() == 2){
            this.agregarResiduoSeleccionado();
        }
    }//GEN-LAST:event_listResiduosDisponiblesMouseClicked

    private void tblResiduosSeleccionadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblResiduosSeleccionadosMouseClicked
        if(evt.getClickCount() == 2){
            this.eliminarResiduoSeleccionado();
        }
    }//GEN-LAST:event_tblResiduosSeleccionadosMouseClicked

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.instancia = null;
        this.dispose();;
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.limpiarFormulario();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSolicitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitarActionPerformed
        this.guardar();
    }//GEN-LAST:event_btnSolicitarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSolicitar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cbxProductores;
    private com.github.lgooddatepicker.components.DatePicker dtFecha;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblProductor;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<String> listResiduosDisponibles;
    private javax.swing.JPanel pnlResiduos;
    private javax.swing.JScrollPane pnlResiduosDisponibles;
    private javax.swing.JScrollPane pnlResiduosSeleccionados;
    private javax.swing.JTable tblResiduosSeleccionados;
    // End of variables declaration//GEN-END:variables
}
