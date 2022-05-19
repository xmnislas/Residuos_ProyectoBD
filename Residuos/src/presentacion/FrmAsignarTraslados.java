/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import entidades.Asignacion;
import entidades.AsignacionEmpresa;
import entidades.EmpresaTransportista;
import entidades.Residuo;
import entidades.ResiduoSolicitud;
import entidades.SolicitudTraslado;
import interfaces.INegocios;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;

/**
 *
 * @author xmnislas
 */
public final class FrmAsignarTraslados extends javax.swing.JFrame {
    private SolicitudTraslado solicitudTraslado;
    private INegocios iNegocios;
    private static FrmAsignarTraslados instancia;
    
    /**
     * Creates new form AsignarTraslados
     */
    public FrmAsignarTraslados(){
        initComponents();
        this.getContentPane().setBackground(new Color(202, 240, 248));
    }
    
    /**
     * Metodo para obtener FrmAsignarTraslados
     * @param parent
     * @return 
     */
    public static FrmAsignarTraslados getInstance(java.awt.Frame parent) {
        if (instancia == null) {
            instancia = new FrmAsignarTraslados();
        }
        return instancia;
    }
    
    public void llenarDatos(INegocios iNegocios){
        this.iNegocios = iNegocios;
        if(!this.llenarTblSolicitudes()){
            return;
        }
        this.deshabilitarCampos();
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
    
    /**
     * Metodo para llenar la tabla de solicitudes no atendida
     * @return s
     */
    public boolean llenarTblSolicitudes(){
        List<SolicitudTraslado> solicitudes = this.iNegocios.buscarSolicitudesTrasladoNoAtendidas();
        
        if(solicitudes.isEmpty()){
            this.mostrarMsjError("No hay solicitudes de traslado no atendidas.");
            this.dispose();
            return false;
        }
        
        DefaultTableModel modeloTabla = (DefaultTableModel)this.tblSolicitudesTraslado.getModel();
        modeloTabla.setRowCount(0);
        solicitudes.forEach(solicitud -> {
            Object[] fila = new Object[4];
            fila[0] = solicitud.getId();
            fila[1] = solicitud.getProductor().getNombre();
            Date fecha = solicitud.getFecha();
            String formatoFecha = fecha.getDate() +"/" + (fecha.getMonth() + 1) + "/" + (fecha.getYear() + 1900);
            fila[2] = formatoFecha;
            fila[3] = solicitud.getResiduos();
            modeloTabla.addRow(fila);
        });
        
        tblSolicitudesTraslado.getColumnModel().getColumn(0).setMaxWidth(0);
        tblSolicitudesTraslado.getColumnModel().getColumn(0).setMinWidth(0);
        tblSolicitudesTraslado.getColumnModel().getColumn(0).setPreferredWidth(0);
        return true;
    }
    
    private void asignar(){
        int filaSeleccionada = this.tblSolicitudesTraslado.getSelectedRow();
        
        if(filaSeleccionada == -1){
            this.mostrarMsjAdvertencia("Seleccione una solicitud.");
            return;
        }
        DefaultTableModel modeloTblSolicitudes = (DefaultTableModel)this.tblSolicitudesTraslado.getModel();
        ObjectId idSolicitud = (ObjectId) modeloTblSolicitudes.getValueAt(filaSeleccionada, 0);
        solicitudTraslado = iNegocios.buscarSolicitudTraslado(idSolicitud);
        this.llenarTblResiduos();
        this.habilitarCampos();
    }
    
    /**
     * Metodo para llenarDatos
     * @param iNegocios
     * @param solicitudTraslado 
     */
    private void llenarTblResiduos(){
        this.txtProductor.setText(solicitudTraslado.getProductor().getNombre());
        this.txtFecha.setText(solicitudTraslado.getFecha().getDate() +"/" + (solicitudTraslado.getFecha().getMonth() + 1) + "/" + (solicitudTraslado.getFecha().getYear() + 1900));
        List<ResiduoSolicitud> listaResiduos = solicitudTraslado.getResiduos();
        
        DefaultTableModel modeloTabla = (DefaultTableModel)this.tblResiduos.getModel();
        modeloTabla.setRowCount(0);
        listaResiduos.forEach(residuo -> {
            if(!residuo.isAsignado()){
                Object[] fila = new Object[3];
                fila[0] = residuo.getResiduo().getId();
                fila[1] = residuo.getResiduo().getNombre();
                fila[2] = residuo.getCantidad();
                modeloTabla.addRow(fila);
            }
        });
        
        tblResiduos.getColumnModel().getColumn(0).setMaxWidth(0);
        tblResiduos.getColumnModel().getColumn(0).setMinWidth(0);
        tblResiduos.getColumnModel().getColumn(0).setPreferredWidth(0);
        if(modeloTabla.getRowCount() < 1){
            JOptionPane.showMessageDialog(this, "Ya no hay residuos a transportar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiarFormulario();
            this.llenarTblSolicitudes();
        }else{
            this.llenarTblEmpresas();
        }
    }
    
    /**
     * Metodo para llenarTblEmpresas
     */
    private void llenarTblEmpresas(){
        List<EmpresaTransportista> empresasTransportistas = this.iNegocios.buscarEmpresasTransportistas();
        
        if(empresasTransportistas.isEmpty()){
            this.mostrarMsjError("No hay empresas transportistas disponibles.");
            this.dispose();
            return;
        }
 
        DefaultTableModel modeloTablaEmpresas = (DefaultTableModel)this.tblEmpresasTransportistas.getModel();
        modeloTablaEmpresas.setRowCount(0);
        empresasTransportistas.forEach(empresa -> {
            Object[] fila = new Object[3];
            fila[0] = empresa.getId();
            fila[1] = empresa.getNombre();
            fila[2] = false;
            modeloTablaEmpresas.addRow(fila);
        });
        
        tblEmpresasTransportistas.getColumnModel().getColumn(0).setMaxWidth(0);
        tblEmpresasTransportistas.getColumnModel().getColumn(0).setMinWidth(0);
        tblEmpresasTransportistas.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    
    /**
     * Metodo para guardar
     */
    private void guardar(){
        if(!this.validaciones()){
            return;
        }
        int cont = 0;
        int filaSeleccionada = this.tblResiduos.getSelectedRow();
        ObjectId idResiduo = (ObjectId) this.tblResiduos.getModel().getValueAt(filaSeleccionada, 0);
        List<AsignacionEmpresa> empresasTransportistas = new ArrayList<>();
        
        for(int i = 0; i < this.tblEmpresasTransportistas.getRowCount(); i++){
                if(this.tblEmpresasTransportistas.getValueAt(i, 2).equals(true)){
                    cont++;
                    AsignacionEmpresa asignacionEmpresa = new AsignacionEmpresa((ObjectId) this.tblEmpresasTransportistas.getValueAt(i, 0), false);
                    empresasTransportistas.add(asignacionEmpresa);
                }
        }
        
        float cantidad = ((float) this.tblResiduos.getModel().getValueAt(filaSeleccionada, 2)) / cont;
        Asignacion asignacion = new Asignacion(empresasTransportistas, cantidad, solicitudTraslado.getFecha(), idResiduo);
        
        iNegocios.guadarAsignacion(asignacion);
        JOptionPane.showMessageDialog(this, "Se ha registrado la asignación de traslado.", "Información", JOptionPane.INFORMATION_MESSAGE);
        actualizarSolicitud(idResiduo);
        
        this.llenarTblResiduos();
    }
    
    /**
     * Metodo para actualizarSolicitud
     * @param idResiduo 
     */
    private void actualizarSolicitud(ObjectId idResiduo){
        List<ResiduoSolicitud> residuosSolicitud = solicitudTraslado.getResiduos();
        int cont = 0;
        
        for(int i = 0; i < residuosSolicitud.size(); i++){
            
            if(residuosSolicitud.get(i).getResiduo().getId() == idResiduo) residuosSolicitud.get(i).setAsignado(true);
            
            if(residuosSolicitud.get(i).isAsignado()) cont++;
        }
        
        solicitudTraslado.setResiduos(residuosSolicitud);
        
        if(cont == residuosSolicitud.size())  solicitudTraslado.setAtendida(true);
        
        iNegocios.actualizarSolicitudTraslado(solicitudTraslado);
    }
    
    /**
     * Metodo para limpiarFormulario
     */
    private void limpiarFormulario(){
        DefaultTableModel modeloTblResiduos = (DefaultTableModel) this.tblResiduos.getModel();
        DefaultTableModel modeloTblEmpresas = (DefaultTableModel) this.tblEmpresasTransportistas.getModel();
        this.tblResiduos.clearSelection();
        this.tblSolicitudesTraslado.clearSelection();
        this.txtFecha.setText("");
        this.txtProductor.setText("");
        
        for (int i = 0; i < tblResiduos.getRowCount(); i++) {
            modeloTblResiduos.removeRow(i);
            i-=1;
        }
        
        for (int i = 0; i < tblEmpresasTransportistas.getRowCount(); i++) {
            modeloTblEmpresas.removeRow(i);
            i-=1;
        }
        this.deshabilitarCampos();
    }
    
    /**
     * Metodo para mostrarMsjError
     * @param mensaje 
     */
    private void mostrarMsjError(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Metodo para mostrarMsjAdvertencia
     * @param mensaje 
     */
    private void mostrarMsjAdvertencia(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Metodo para validar
     * @return 
     */
    private boolean validaciones(){
        if(this.tblResiduos.getSelectedRow() == -1){
            this.mostrarMsjAdvertencia("Seleccione el residuo a transportar.");
            return false;
        }
        
        int cont = 0;
        for(int i = 0; i < this.tblEmpresasTransportistas.getRowCount(); i++){
                if(this.tblEmpresasTransportistas.getValueAt(i, 2).equals(true)) cont++;
        }
        
        if(cont == 0){
            this.mostrarMsjAdvertencia("Seleccione al menos una empresa transportista.");
            return false;
        }
        
        return true;
    }
    
    /**
     * Metodo para deshabilitarCampos
     */
    private void deshabilitarCampos(){
        this.btnGuardar.setEnabled(false);
        this.btnLimpiar.setEnabled(false);
        this.tblEmpresasTransportistas.setEnabled(false);
        this.tblResiduos.setEnabled(false);
        this.tblSolicitudesTraslado.setEnabled(true);
        this.btnAsignar.setEnabled(true);
    }
    
    /**
     * Metodo para habilitarCampos
     */
    private void habilitarCampos(){
        this.btnGuardar.setEnabled(true);
        this.btnLimpiar.setEnabled(true);
        this.tblEmpresasTransportistas.setEnabled(true);
        this.tblResiduos.setEnabled(true);
        this.tblSolicitudesTraslado.setEnabled(false);
        this.btnAsignar.setEnabled(false);
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
        lblProductor = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        txtProductor = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        pnlEmpresasTransportistas = new javax.swing.JPanel();
        pnlEmpresasDisponibles = new javax.swing.JScrollPane();
        tblEmpresasTransportistas = new javax.swing.JTable();
        pnlResiduos = new javax.swing.JScrollPane();
        tblResiduos = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        pnlSolicitudesTraslado = new javax.swing.JScrollPane();
        tblSolicitudesTraslado = new javax.swing.JTable();
        btnAsignar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asignar Traslados");

        lblTitulo.setText("Asignar Traslados");
        lblTitulo.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(3, 4, 94));

        lblProductor.setText("Productor");
        lblProductor.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        lblProductor.setForeground(new java.awt.Color(2, 62, 138));

        lblFecha.setText("Fecha solicitada");
        lblFecha.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(2, 62, 138));

        txtProductor.setEditable(false);

        txtFecha.setEditable(false);

        pnlEmpresasTransportistas.setBackground(new java.awt.Color(202, 240, 248));
        pnlEmpresasTransportistas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Empresas Transportistas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 14), new java.awt.Color(2, 62, 138))); // NOI18N

        pnlEmpresasDisponibles.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblEmpresasTransportistas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Seleccionada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Boolean.class
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
        pnlEmpresasDisponibles.setViewportView(tblEmpresasTransportistas);

        javax.swing.GroupLayout pnlEmpresasTransportistasLayout = new javax.swing.GroupLayout(pnlEmpresasTransportistas);
        pnlEmpresasTransportistas.setLayout(pnlEmpresasTransportistasLayout);
        pnlEmpresasTransportistasLayout.setHorizontalGroup(
            pnlEmpresasTransportistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmpresasTransportistasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEmpresasDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlEmpresasTransportistasLayout.setVerticalGroup(
            pnlEmpresasTransportistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmpresasTransportistasLayout.createSequentialGroup()
                .addComponent(pnlEmpresasDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlResiduos.setBackground(new java.awt.Color(202, 240, 248));
        pnlResiduos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Residuos a Transportar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 14), new java.awt.Color(2, 62, 138))); // NOI18N

        tblResiduos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Residuo", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pnlResiduos.setViewportView(tblResiduos);

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

        jPanel1.setBackground(new java.awt.Color(202, 240, 248));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Solicitudes no atendidas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 1, 14), new java.awt.Color(2, 62, 138))); // NOI18N

        tblSolicitudesTraslado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Productor", "Fecha solicitada", "Residuos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pnlSolicitudesTraslado.setViewportView(tblSolicitudesTraslado);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlSolicitudesTraslado)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlSolicitudesTraslado, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnAsignar.setText("Asignar");
        btnAsignar.setBackground(new java.awt.Color(247, 249, 249));
        btnAsignar.setFont(new java.awt.Font("Arial Narrow", 1, 13)); // NOI18N
        btnAsignar.setForeground(new java.awt.Color(3, 4, 94));
        btnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBackground(new java.awt.Color(247, 249, 249));
        btnLimpiar.setFont(new java.awt.Font("Arial Narrow", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(3, 4, 94));
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(196, 196, 196))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(btnAsignar)
                .addGap(138, 138, 138)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lblProductor)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtProductor))
                                .addComponent(pnlResiduos, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pnlEmpresasTransportistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(97, 97, 97)
                                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblFecha)
                                    .addGap(173, 173, 173))))
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1)
                        .addComponent(jSeparator2)))
                .addGap(0, 45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAsignar)
                    .addComponent(btnVolver)
                    .addComponent(btnCancelar))
                .addGap(13, 13, 13)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFecha)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProductor))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlEmpresasTransportistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlResiduos, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnLimpiar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        this.guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.instancia = null;
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.limpiarFormulario();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarActionPerformed
        this.asignar();
    }//GEN-LAST:event_btnAsignarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        for (int i = 0; i < tblEmpresasTransportistas.getRowCount(); i++) {
            tblEmpresasTransportistas.setValueAt(false, i, 2);
        }
        this.tblEmpresasTransportistas.clearSelection();
        this.tblResiduos.clearSelection();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblProductor;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane pnlEmpresasDisponibles;
    private javax.swing.JPanel pnlEmpresasTransportistas;
    private javax.swing.JScrollPane pnlResiduos;
    private javax.swing.JScrollPane pnlSolicitudesTraslado;
    private javax.swing.JTable tblEmpresasTransportistas;
    private javax.swing.JTable tblResiduos;
    private javax.swing.JTable tblSolicitudesTraslado;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtProductor;
    // End of variables declaration//GEN-END:variables
}
