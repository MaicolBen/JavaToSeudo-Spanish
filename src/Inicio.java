import Transformer.CargarVentana;
import Transformer.Conversor;
import Transformer.GuardarVentana;
import javax.swing.JOptionPane;


public class Inicio extends javax.swing.JFrame {
    
   CargarVentana  open;
   GuardarVentana save;
    public Inicio() {
        initComponents();
        guardar.setVisible(false);
    }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jDialog1 = new javax.swing.JDialog();
        cargar = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        bucleCheck = new javax.swing.JCheckBox();
        comentariosCheck = new javax.swing.JCheckBox();
        reempCheck = new javax.swing.JCheckBox();
        jToggleButton1 = new javax.swing.JToggleButton();

        org.jdesktop.layout.GroupLayout jDialog1Layout = new org.jdesktop.layout.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ToSeudo Converter");

        cargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/text-x-csharp.png"))); // NOI18N
        cargar.setToolTipText("Cargue el codigo para conseguir el seudocodigo");
        cargar.setBorderPainted(false);
        cargar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarActionPerformed(evt);
            }
        });

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/document-save-as.png"))); // NOI18N
        guardar.setMnemonic('G');
        guardar.setToolTipText("Guardar como seudo codigo");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        bucleCheck.setSelected(true);
        bucleCheck.setText("Activar transformacion de bucles");
        bucleCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bucleCheckActionPerformed(evt);
            }
        });

        comentariosCheck.setSelected(true);
        comentariosCheck.setText("Quitar comentarios");

        reempCheck.setSelected(true);
        reempCheck.setText("Activar reemplazador de palabras");

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/system-help.png"))); // NOI18N
        jToggleButton1.setText("jToggleButton1");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(reempCheck)
                    .add(comentariosCheck)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(cargar)
                            .add(bucleCheck))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(guardar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 142, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .add(11, 11, 11)
                .add(jToggleButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, guardar, 0, 0, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, cargar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(18, 18, 18)
                        .add(bucleCheck)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(reempCheck)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 12, Short.MAX_VALUE)
                        .add(comentariosCheck))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jToggleButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 51, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        cargar.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarActionPerformed
        open=new CargarVentana();
        open.setVisible(true);
        guardar.setVisible(true);
        
    }//GEN-LAST:event_cargarActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        String leido=open.leido;
        JOptionPane.showMessageDialog(null, "El software es un programa , nunca va a poder hacerlo como un humano ,\n por favor revise el seudocodigo detenidamente luego de su conversion.\n Si hubo errores , desactive la opción necesaria.");
        Conversor conv=new Conversor(leido,this.bucleCheck.isSelected(),this.comentariosCheck.isSelected(),this.reempCheck.isSelected());
        save=new GuardarVentana(conv.seudo);
        save.setVisible(true);
    }//GEN-LAST:event_guardarActionPerformed

    private void bucleCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bucleCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bucleCheckActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        JOptionPane.showMessageDialog(null, "El software es un producto de ayuda para convertir un codigo en java o c# a seudocodigo. \n Por Maicol Bentancor. Version 2.1");
    }//GEN-LAST:event_jToggleButton1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox bucleCheck;
    private javax.swing.JButton cargar;
    private javax.swing.JCheckBox comentariosCheck;
    private javax.swing.JButton guardar;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JCheckBox reempCheck;
    // End of variables declaration//GEN-END:variables
    
}
