/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dto.RelAsignacionEmpresa;
import entidades.Asignacion;
import entidades.AsignacionEmpresa;
import entidades.EmpresaTransportista;
import entidades.Productor;
import entidades.Residuo;
import entidades.Traslado;
import entidades.Tratamiento;
import entidades.Vehiculo;
import interfaces.INegocios;
import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public class FrmRegistrarTraslado extends javax.swing.JFrame {
    private INegocios iNegocios;
    private static FrmRegistrarTraslado instancia;
    private ObjectId idEmpresaTransportista;
    private Residuo residuo;
    private Asignacion asignacion;
    
    /**
     * Creates new form FrmRegistrarTraslado
     */
    public FrmRegistrarTraslado(){
        initComponents();
        this.getContentPane().setBackground(new Color(202, 240, 248));
    }
    
    /**
     * Metodo para obtener FrmRegistrarTraslado
     * @param parent
     * @return 
     */
    public static FrmRegistrarTraslado getInstance(java.awt.Frame parent) {
        if (instancia == null) {
            instancia = new FrmRegistrarTraslado();
        }
        return instancia;
    }
    
     /**
     * Metodo para llenar datos
     * @param iNegocios 
     */
    public void llenarDatos(INegocios iNegocios){
        this.iNegocios = iNegocios;
        List<EmpresaTransportista> empresas = this.iNegocios.buscarEmpresasTransportistas();
        if(empresas.isEmpty()){
            this.mostrarMsjError("No se pudieron consultar las empresas transportistas.");
            this.dispose();
            return;
        }
        
        DefaultComboBoxModel modelo = new DefaultComboBoxModel(empresas.toArray());
        this.cbxEmpresasTransportistas.setModel(modelo);
        
        tblTrasladosAsignados.getColumnModel().getColumn(0).setMaxWidth(0);
        tblTrasladosAsignados.getColumnModel().getColumn(0).setMinWidth(0);
        tblTrasladosAsignados.getColumnModel().getColumn(0).setPreferredWidth(0);
        tblTrasladosAsignados.getColumnModel().getColumn(2).setMaxWidth(0);
        tblTrasladosAsignados.getColumnModel().getColumn(2).setMinWidth(0);
        tblTrasladosAsignados.getColumnModel().getColumn(2).setPreferredWidth(0);
        tblVehiculos.getColumnModel().getColumn(0).setMaxWidth(0);
        tblVehiculos.getColumnModel().getColumn(0).setMinWidth(0);
        tblVehiculos.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        this.deshabilitarCampos();
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
    
    /**
     * Metodo para buscar
     */
    private void buscar(){
        idEmpresaTransportista = ((EmpresaTransportista) this.cbxEmpresasTransportistas.getSelectedItem()).getId();
        List<RelAsignacionEmpresa> listaTrasladosAsignados = iNegocios.buscarAsignacionesPorEmpresa(idEmpresaTransportista);
        
        if(listaTrasladosAsignados.isEmpty()){
            this.mostrarMsjAdvertencia("La empresa no tiene traslados asignados.");
            return;
        }
        
        DefaultTableModel modeloTabla = (DefaultTableModel)this.tblTrasladosAsignados.getModel();
        modeloTabla.setRowCount(0);
        listaTrasladosAsignados.forEach(trasladoAsignado -> {
            residuo = iNegocios.buscarResiduo(trasladoAsignado.getIdResiduo());
            Productor productor = iNegocios.buscarProductor(residuo.getIdProductor());
            Object[] fila = new Object[6];
            Date fecha = trasladoAsignado.getFecha();
            String formatoFecha = fecha.getDate() +"/" + (fecha.getMonth() + 1) + "/" + (fecha.getYear() + 1900);
            fila[0] = trasladoAsignado.getIdAsignacion();
            fila[1] = formatoFecha;
            fila[2] = trasladoAsignado.getIdResiduo();
            fila[3] = residuo.getNombre();
            fila[4] = trasladoAsignado.getCantidad();
            fila[5] = productor.getNombre();
            modeloTabla.addRow(fila);
        });
    }
    
    /**
     * Metodo para llenarDatos
     */
    public void llenarTblVehiculos(){
        List<Vehiculo> vehiculos = iNegocios.buscarVehiculosPorEmpresa(idEmpresaTransportista);
        
        if(vehiculos.isEmpty()){
            this.mostrarMsjError("No se pudieron consultar los vehículos de la empresa.");
            this.dispose();
            return;
        }
        
        DefaultTableModel modeloTabla = (DefaultTableModel)this.tblVehiculos.getModel();
        modeloTabla.setRowCount(0);
        vehiculos.forEach(vehiculo -> {
            Object[] fila = new Object[3];
            fila[0] = vehiculo.getId();
            fila[1] = vehiculo.getNombre();
            fila[2] = false;
            modeloTabla.addRow(fila);
        });
    }
    
    /**
     * Metodo para guardar
     */
    private void guardar(){
        if(this.validarTraslado()){
            LocalDate stringFecha = this.dtpFecha.getDate();
            Date fechaLlegada = new Date(stringFecha.getYear() - 1900, stringFecha.getMonthValue() - 1, stringFecha.getDayOfMonth());
            float kilometrosRecorridos = Float.parseFloat(this.txtKmRecorridos.getText());
            float costo = Float.parseFloat(this.txtCosto.getText());
            Tratamiento tratamiento = new Tratamiento(this.txtTratamiento.getText(), this.txtaDescripción.getText());
            List<ObjectId> idsVehiculos = new ArrayList<>();

            for(int i = 0; i < this.tblVehiculos.getRowCount(); i++){

                if(this.tblVehiculos.getValueAt(i, 2).equals(true)){
                    idsVehiculos.add((ObjectId) this.tblVehiculos.getValueAt(i, 0));
                }
            }
            Traslado traslado = new Traslado(idEmpresaTransportista, residuo.getId(), fechaLlegada, kilometrosRecorridos, costo, tratamiento, idsVehiculos);
            iNegocios.guadarTraslado(traslado);
            this.mostrarMsjConfirmacion();
            this.actualizarAsignaciones();
            this.limpiarFormulario();
        }
    }
    
    private void actualizarAsignaciones(){
        List<AsignacionEmpresa> asignaciones = asignacion.getEmpresasTransportistas();
        asignaciones.forEach(asig ->{
            if(asig.getIdEmpresaTransportista().equals(idEmpresaTransportista)){
                asig.setTrasladado(true);
            }
        });
        
        asignacion.setEmpresasTransportistas(asignaciones);
        iNegocios.actualizarAsignacion(asignacion);
    }
    
    /**
     * Metodo para mostrarMsjConfirmacion
     */
    private void mostrarMsjConfirmacion(){
         JOptionPane.showMessageDialog(this, "Se ha registrado el traslado. ", "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Metodo para mostrarMsjAdvertencia
     * @param mensaje 
     */
    private void mostrarMsjAdvertencia(String mensaje){
         JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Metodo para mostrarMsjError
     * @param mensaje 
     */
    private void mostrarMsjError(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Metodo para limpiarFormulario
     */
    private void limpiarFormulario(){
        DefaultTableModel modeloTblVehiculos = (DefaultTableModel) this.tblVehiculos.getModel();
        DefaultTableModel modeloTblTraslados = (DefaultTableModel) this.tblTrasladosAsignados.getModel();
        this.tblTrasladosAsignados.clearSelection();
        this.txtCosto.setText("");
        this.txtKmRecorridos.setText("");
        this.txtTratamiento.setText("");
        this.txtaDescripción.setText("");
        this.dtpFecha.setText("");
        for (int i = 0; i < tblVehiculos.getRowCount(); i++) {
            modeloTblVehiculos.removeRow(i);
            i-=1;
        }
        
        for (int i = 0; i < tblTrasladosAsignados.getRowCount(); i++) {
            modeloTblTraslados.removeRow(i);
            i-=1;
        }
        this.deshabilitarCampos();
    }
    
    /**
     * Metodo para validarTraslado
     * @return 
     */
    private boolean validarTraslado(){
        int cont = 0;
        for(int i = 0; i < this.tblVehiculos.getRowCount(); i++){
            if(this.tblVehiculos.getValueAt(i, 2).equals(true)) cont++;
        }
        
        if(cont == 0){
            this.mostrarMsjAdvertencia("Seleccione al menos un vehículo.");
            return false;
        }
        
        if(this.txtKmRecorridos.getText().isEmpty() || this.txtCosto.getText().isEmpty() || this.txtTratamiento.getText().isEmpty() || this.txtaDescripción.getText().isEmpty() || this.dtpFecha.getText().isEmpty()){
            this.mostrarMsjAdvertencia("No es posible registrar campos vacíos.");
            return false;
        }
        
        try {  
            Float.parseFloat(this.txtCosto.getText());
        } catch(NumberFormatException e){  
            this.mostrarMsjAdvertencia("El costo debe ser de caracteres numéricos.");
            return false;  
        }
        
        try {  
            Float.parseFloat(this.txtKmRecorridos.getText());
        } catch(NumberFormatException e){  
            this.mostrarMsjAdvertencia("Los kilómetros deben ser de caracteres numéricos.");
            return false;  
        }
        
        if(Float.parseFloat(this.txtCosto.getText()) <= 0){
            this.mostrarMsjAdvertencia("El costo debe ser un número positivo.");
            return false;
        }
        
        if(Float.parseFloat(this.txtKmRecorridos.getText()) <= 0){
            this.mostrarMsjAdvertencia("Los kilómetros deben ser un número positivo.");
            return false;
        }
        
        if(Float.parseFloat(this.txtKmRecorridos.getText()) < 1000){
            this.mostrarMsjAdvertencia("Los kilómetros deben ser mayor a 1000.");
            return false;
        }
        return true;
    }
    
    /**
     * Metodo para deshabilitarCampos
     */
    private void deshabilitarCampos(){
        this.btnLimpiar.setEnabled(false);
        this.btnGuardar.setEnabled(false);
        this.dtpFecha.setEnabled(false);
        this.txtCosto.setEnabled(false);
        this.txtKmRecorridos.setEnabled(false);
        this.txtTratamiento.setEnabled(false);
        this.txtaDescripción.setEnabled(false);
        this.tblVehiculos.setEnabled(false);
        this.cbxEmpresasTransportistas.setEnabled(true);
        this.btnRegistrar.setEnabled(true);
        this.tblTrasladosAsignados.setEnabled(true);
        this.btnBuscar.setEnabled(true);
    }
    
    /**
     * Metodo para habilitarCampos
     */
    private void habilitarCampos(){
        this.btnLimpiar.setEnabled(true);
        this.btnGuardar.setEnabled(true);
        this.dtpFecha.setEnabled(true);
        this.txtCosto.setEnabled(true);
        this.txtKmRecorridos.setEnabled(true);
        this.txtTratamiento.setEnabled(true);
        this.txtaDescripción.setEnabled(true);
        this.tblVehiculos.setEnabled(true);
        this.btnBuscar.setEnabled(false);
        this.tblTrasladosAsignados.setEnabled(false);
        this.btnRegistrar.setEnabled(false);
        this.cbxEmpresasTransportistas.setEnabled(false);
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
        pnlVehiculos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVehiculos = new javax.swing.JTable();
        lblKm = new javax.swing.JLabel();
        lblCosto = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblTratamiento = new javax.swing.JLabel();
        dtpFecha = new com.github.lgooddatepicker.components.DatePicker();
        txtCosto = new javax.swing.JTextField();
        txtKmRecorridos = new javax.swing.JTextField();
        txtTratamiento = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        pnlDescripcion = new javax.swing.JScrollPane();
        txtaDescripción = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnVolver = new javax.swing.JButton();
        lblEmpresaTransportista = new javax.swing.JLabel();
        cbxEmpresasTransportistas = new javax.swing.JComboBox<>();
        pnlTraslados = new javax.swing.JPanel();
        pnlTrasladosAsignados = new javax.swing.JScrollPane();
        tblTrasladosAsignados = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrar Traslado");

        lblTitulo.setText("Registrar Traslado");
        lblTitulo.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(3, 4, 94));

        pnlVehiculos.setBackground(new java.awt.Color(202, 240, 248));
        pnlVehiculos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Vehículos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 14), new java.awt.Color(2, 62, 138))); // NOI18N

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblVehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID ", "Vehículo", "Seleccionado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblVehiculos);

        javax.swing.GroupLayout pnlVehiculosLayout = new javax.swing.GroupLayout(pnlVehiculos);
        pnlVehiculos.setLayout(pnlVehiculosLayout);
        pnlVehiculosLayout.setHorizontalGroup(
            pnlVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVehiculosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlVehiculosLayout.setVerticalGroup(
            pnlVehiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVehiculosLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        lblKm.setText("Kilómetros");
        lblKm.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        lblKm.setForeground(new java.awt.Color(2, 62, 138));

        lblCosto.setText("Costo");
        lblCosto.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        lblCosto.setForeground(new java.awt.Color(2, 62, 138));

        lblFecha.setText("Fecha llegada");
        lblFecha.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(2, 62, 138));

        lblTratamiento.setText("Tratamiento");
        lblTratamiento.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        lblTratamiento.setForeground(new java.awt.Color(2, 62, 138));

        lblDescripcion.setText("Descripción");
        lblDescripcion.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(2, 62, 138));

        txtaDescripción.setColumns(20);
        txtaDescripción.setRows(5);
        pnlDescripcion.setViewportView(txtaDescripción);

        btnGuardar.setText("Guardar");
        btnGuardar.setBackground(new java.awt.Color(247, 249, 249));
        btnGuardar.setFont(new java.awt.Font("Arial Narrow", 1, 13)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(3, 4, 94));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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

        lblEmpresaTransportista.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        lblEmpresaTransportista.setForeground(new java.awt.Color(2, 62, 138));
        lblEmpresaTransportista.setText("Empresa Transportista");

        pnlTraslados.setBackground(new java.awt.Color(202, 240, 248));
        pnlTraslados.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Traslados asignados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 14), new java.awt.Color(2, 62, 138))); // NOI18N

        pnlTrasladosAsignados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblTrasladosAsignados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha solicitada", "ID Residuo", "Residuo", "Cantidad", "Productor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pnlTrasladosAsignados.setViewportView(tblTrasladosAsignados);

        javax.swing.GroupLayout pnlTrasladosLayout = new javax.swing.GroupLayout(pnlTraslados);
        pnlTraslados.setLayout(pnlTrasladosLayout);
        pnlTrasladosLayout.setHorizontalGroup(
            pnlTrasladosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTrasladosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTrasladosAsignados)
                .addContainerGap())
        );
        pnlTrasladosLayout.setVerticalGroup(
            pnlTrasladosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTrasladosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTrasladosAsignados, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnBuscar.setBackground(new java.awt.Color(247, 249, 249));
        btnBuscar.setFont(new java.awt.Font("Arial Narrow", 1, 13)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(3, 4, 94));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnRegistrar.setBackground(new java.awt.Color(247, 249, 249));
        btnRegistrar.setFont(new java.awt.Font("Arial Narrow", 1, 13)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(3, 4, 94));
        btnRegistrar.setText("Registrar Traslado");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(247, 249, 249));
        btnLimpiar.setFont(new java.awt.Font("Arial Narrow", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(3, 4, 94));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblEmpresaTransportista)
                                        .addGap(28, 28, 28)
                                        .addComponent(cbxEmpresasTransportistas, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(pnlTraslados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnRegistrar, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnVolver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblDescripcion)
                                        .addGap(24, 24, 24)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(pnlDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblFecha)
                                                    .addComponent(lblKm))
                                                .addGap(14, 14, 14))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblTratamiento)
                                                .addGap(25, 25, 25)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTratamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtKmRecorridos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(16, 16, 16)
                                                .addComponent(lblCosto)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(dtpFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(11, 11, 11))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jSeparator1)
                                .addGap(5, 5, 5)))))
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addComponent(lblTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxEmpresasTransportistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEmpresaTransportista)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnRegistrar)
                        .addGap(47, 47, 47)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVolver))
                    .addComponent(pnlTraslados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFecha)
                            .addComponent(dtpFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCosto)
                            .addComponent(txtKmRecorridos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKm)
                            .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTratamiento)
                            .addComponent(txtTratamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDescripcion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardar)
                            .addComponent(btnLimpiar)))
                    .addComponent(pnlVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        this.buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if(this.tblTrasladosAsignados.getSelectedRow() == -1){
            this.mostrarMsjAdvertencia("Seleccione un traslado.");
            return;
        }
        asignacion = iNegocios.buscarAsignacion((ObjectId) this.tblTrasladosAsignados.getValueAt(this.tblTrasladosAsignados.getSelectedRow(), 0));
        residuo = iNegocios.buscarResiduo((ObjectId) this.tblTrasladosAsignados.getValueAt(this.tblTrasladosAsignados.getSelectedRow(), 2));
        this.habilitarCampos();
        this.llenarTblVehiculos();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.txtCosto.setText("");
        this.txtKmRecorridos.setText("");
        this.txtTratamiento.setText("");
        this.txtaDescripción.setText("");
        this.dtpFecha.setText("");
        for (int i = 0; i < tblVehiculos.getRowCount(); i++) {
            tblVehiculos.setValueAt(false, i, 2);
        }
        this.tblVehiculos.clearSelection();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cbxEmpresasTransportistas;
    private com.github.lgooddatepicker.components.DatePicker dtpFecha;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblCosto;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblEmpresaTransportista;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblKm;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTratamiento;
    private javax.swing.JScrollPane pnlDescripcion;
    private javax.swing.JPanel pnlTraslados;
    private javax.swing.JScrollPane pnlTrasladosAsignados;
    private javax.swing.JPanel pnlVehiculos;
    private javax.swing.JTable tblTrasladosAsignados;
    private javax.swing.JTable tblVehiculos;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtKmRecorridos;
    private javax.swing.JTextField txtTratamiento;
    private javax.swing.JTextArea txtaDescripción;
    // End of variables declaration//GEN-END:variables
}
