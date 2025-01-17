
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anubis_1392
 */
public class ganadores extends javax.swing.JDialog {
    conexion ne=new conexion();
    Connection con=ne.Conectar();
    
    public ganadores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try{
            DefaultTableModel modelo=new DefaultTableModel();
            tpg.setModel(modelo);
            PreparedStatement ps=null;
            ResultSet rs=null;
            ps=con.prepareStatement("select concursantes.nombre,concursantes.apellido,concursantes.cedula,concursantes.telefono,premios.id from ganadores,concursantes,premios where ganadores.cedula_ganador=concursantes.cedula and ganadores.id_ganador=premios.id");
            rs=ps.executeQuery();//trae datos de consulta
            ResultSetMetaData rsMd=rs.getMetaData();//pasandole el resultado de la consulta
            int cantidad=rsMd.getColumnCount();//cantidad de columas
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Cedula");
            modelo.addColumn("Telefono");
            modelo.addColumn("ID_Premio");
            while(rs.next()){
                Object[] filas=new Object[cantidad];
                for(int i=0;i<cantidad;i++){
                    filas[i]=rs.getObject(i+1);
                }
                modelo.addRow(filas);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        tpg = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cuadrocedu = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tpg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cedula", "ID", "Nombre", "Apellido", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tpg);

        jLabel1.setText("Cedula:");

        jLabel2.setText("Borrar ganador");

        jButton1.setText("Borrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(232, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cuadrocedu, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2)
                        .addGap(125, 125, 125)))
                .addGap(173, 173, 173))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(cuadrocedu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 162, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            PreparedStatement pst2=con.prepareStatement("delete from ganadores where ganadores.cedula_ganador="+cuadrocedu.getText());
            int vus=pst2.executeUpdate();
            if (vus>0){
                JOptionPane.showMessageDialog(null, "Ganador removido correctamente");
            }

        }catch(SQLException ee){
            JOptionPane.showMessageDialog(null, "Error al remover ganador "+ee);
        }
        try{
            DefaultTableModel modelo=new DefaultTableModel();
            tpg.setModel(modelo);
            PreparedStatement ps=null;
            ResultSet rs=null;
            ps=con.prepareStatement("select concursantes.nombre,concursantes.apellido,concursantes.cedula,concursantes.telefono,premios.id from ganadores,concursantes,premios where ganadores.cedula_ganador=concursantes.cedula and ganadores.id_ganador=premios.id");
            rs=ps.executeQuery();//trae datos de consulta
            ResultSetMetaData rsMd=rs.getMetaData();//pasandole el resultado de la consulta
            int cantidad=rsMd.getColumnCount();//cantidad de columas
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Cedula");
            modelo.addColumn("Telefono");
            modelo.addColumn("ID_Premio");
            while(rs.next()){
                Object[] filas=new Object[cantidad];
                for(int i=0;i<cantidad;i++){
                    filas[i]=rs.getObject(i+1);
                }
                modelo.addRow(filas);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERROR"+ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ganadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ganadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ganadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ganadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ganadores dialog = new ganadores(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cuadrocedu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tpg;
    // End of variables declaration//GEN-END:variables
}
