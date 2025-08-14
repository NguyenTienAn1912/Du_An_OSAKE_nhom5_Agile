/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;


//import View.form.Form_ThongKe;


import View.form.Form_NhanVien;
import com.Product.compoment.Menu;
import com.Product.form1.Form_ThietLap;
import com.Product.form1.Form_DangNhap;
import com.Product.form1.Form_BanHang;
import com.Product.form1.Form_KhachHang;
import com.Product.form1.Form_KhuyenMai;
import com.Product.form1.Form_LichSu;


import com.Product.form1.Form_NCC;
//import com.Product.form1.Form_NhanVien;
import com.Product.form1.Form_SanPham;
import com.Product.form1.Form_ThongKe;
//import com.Product.form1.Form_ThietLap;
//import com.Product.form1.Form_ThongKe;
import com.Product.form1.Form_home;

import com.raven.event.EventMenuSelected;
import java.awt.Color;
import java.awt.Scrollbar;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    
    private Form_DangNhap DN;
    private Form_BanHang BanHang;
    private Form_KhachHang KhachHang;
    private Form_KhuyenMai KhuyenMai;
    private Form_LichSu LichSu;
    private Form_NCC NCC;
    private Form_NhanVien NhanVien;
    private Form_SanPham SanPham;
    private Form_ThongKe ThongKe;
    private Form_ThietLap ThietLap;
    private Login login;
    
   
    
    
    
    public Main() throws SQLException, ParseException {
        initComponents();
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        menu1.initMoving(Main.this);
        DN = new Form_DangNhap();
        BanHang = new Form_BanHang();
        KhachHang = new Form_KhachHang();
        LichSu = new Form_LichSu();
        NCC = new Form_NCC();
        NhanVien = new Form_NhanVien();
        SanPham = new Form_SanPham();
        ThongKe = new Form_ThongKe();
        KhuyenMai = new Form_KhuyenMai();
        ThietLap = new Form_ThietLap();
        login = new Login();
        
       
        
        
         
        menu1.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                
                if(index == 0){
                    setForm(ThongKe);
                }else if(index == 2){
                    setForm(BanHang);
                }else if(index == 4){
                    setForm(SanPham);
                }else if(index == 6){
                    setForm(NhanVien);
                }else if(index == 8){
                    setForm(KhachHang);
                }else if(index == 10){
                    setForm(NCC);
                }else if(index == 12){
                    setForm(KhuyenMai);
                }else if(index == 14){
                    setForm(LichSu);
                }else if(index == 16){
                    setForm(ThietLap);
                }else if(index == 20){
                    dispose();
                    login.setVisible(true);
                }
            
            }
        });
        
        
       
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneBorder2 = new View.swing.PaneBorder();
        mainPanel = new javax.swing.JPanel();
        form_Start1 = new com.Product.form1.Form_Start();
        menu1 = new com.Product.compoment.Menu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());
        mainPanel.add(form_Start1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout paneBorder2Layout = new javax.swing.GroupLayout(paneBorder2);
        paneBorder2.setLayout(paneBorder2Layout);
        paneBorder2Layout.setHorizontalGroup(
            paneBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneBorder2Layout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paneBorder2Layout.setVerticalGroup(
            paneBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(paneBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }
  
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.Product.form1.Form_Start form_Start1;
    private javax.swing.JPanel mainPanel;
    private com.Product.compoment.Menu menu1;
    private View.swing.PaneBorder paneBorder2;
    // End of variables declaration//GEN-END:variables
}
