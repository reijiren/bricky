/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bricky;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import koneksi.Connect;
//import com.mysql.jdbc.Connection;
//import koneksi.Connect;

/**
 *
 * @author ASUS-ROG
 */
public class Daftar extends javax.swing.JFrame {

    /**
     * Creates new form Daftar
     */
    public Daftar() {
        initComponents();
        this.setVisible(true);
    }
    
    private void newData(String user, String pass){
        String sql = "INSERT INTO tblogin VALUES ('" + user + "','" + pass + "')";
        String sql2 = "INSERT INTO tblscore (Username,exp,score1,score2,score3,score4) VALUES ('"+ user + "', '" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "','" + 0 + "')";
        try {
            Statement statement = (Statement) Connect.GetConnection().createStatement();
            statement.executeUpdate(sql);
            statement.executeUpdate(sql2);
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
        
            Login log = new Login();
            this.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan!");
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

        lblDaftar = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        butDaftar = new javax.swing.JButton();
        butCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bricky");
        setResizable(false);

        lblDaftar.setText("Daftar Akun Baru");

        lblUser.setText("Username :");

        jLabel1.setText("Password :");

        butDaftar.setText("Daftar");
        butDaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butDaftarActionPerformed(evt);
            }
        });

        butCancel.setText("Cancel");
        butCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblDaftar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtPass))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblUser)
                                .addGap(18, 18, 18)
                                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(butCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(butDaftar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDaftar)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUser)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butDaftar)
                    .addComponent(butCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void butDaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butDaftarActionPerformed
        String User = txtUser.getText();
        String Pass = txtPass.getText();
        newData(User, Pass);
    }//GEN-LAST:event_butDaftarActionPerformed

    private void butCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCancelActionPerformed
        Login log = new Login();
        this.setVisible(false);
    }//GEN-LAST:event_butCancelActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butCancel;
    private javax.swing.JButton butDaftar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDaftar;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
