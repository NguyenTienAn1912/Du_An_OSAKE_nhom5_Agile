/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model_Voucher.Model_TaiKhoan;
import Repository.Repository_Nhan_Vien;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Hùng
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    Repository.Repository_Nhan_Vien nv = new Repository_Nhan_Vien();
    String ma_nhanVienDN;
    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));

    }

//    void check(ArrayList<Model_TaiKhoan> ds) {
//        if (txt_taiKhoan2.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");
//            return;
//        }
//        if (txt_matKhau.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");
//            return;
//        }
//        String ten = txt_taiKhoan2.getText();
//        String mk = txt_matKhau.getText();
//        for (Model_TaiKhoan d : ds) {
//            if (ten.trim().equals(d.getEmail())) {
//                if (mk.trim().equals(d.getMatKhau())) {
//
//                    try {
//                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
//                    
//                    // Kiểm tra vai trò người dùng
//                    if (d.isVaitro().equals("nhanvien")) {
//                        // Nếu là nhân viên, mở MainNhanVien
//                        MainNhanVien mainNhanVien = new MainNhanVien();
//                        mainNhanVien.setVisible(true);
//                    } else if (d.getRole().equals("quanly")) {
//                        // Nếu là quản lý, mở MainQuanLy
//                        MainQuanLy mainQuanLy = new MainQuanLy();
//                        mainQuanLy.setVisible(true);
//                    }
//                    
//                    this.dispose();  // Đóng cửa sổ đăng nhập
//                    return;
//                } catch (ParseException ex) {
//                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                }
//            }
//        }
//        JOptionPane.showMessageDialog(null, "Email hoặc mật khẩu không chính xác! Vui lòng thử lại");
//        return;
//    }
void check(ArrayList<Model_TaiKhoan> ds) throws SQLException {
    // Kiểm tra nếu các trường nhập vào rỗng
    if (txt_taiKhoan2.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");
        return;
    }
    if (txt_matKhau.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");
        return;
    }

    String ten = txt_taiKhoan2.getText().trim(); // Loại bỏ khoảng trắng thừa
    String mk = txt_matKhau.getText().trim(); // Loại bỏ khoảng trắng thừa

    // Duyệt qua danh sách tài khoản từ cơ sở dữ liệu
    for (Model_TaiKhoan d : ds) {
        // Kiểm tra xem email có trùng khớp không (so sánh chính xác, loại bỏ khoảng trắng)
        if (ten.equalsIgnoreCase(d.getEmail())) {  // So sánh không phân biệt chữ hoa/thường
            if (mk.equals(d.getMatKhau())) { // Kiểm tra mật khẩu
                try {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
                    // Kiểm tra vai trò và mở màn hình chính tương ứng
                    if (d.isVaitro()) {
                        // Nếu là quản lý, mở MainQuanLy
                        Main mainQuanLy = new Main();
                        ma_nhanVienDN = d.getma_nhanVien();
                        mainQuanLy.setVisible(true);
                    } else {
                        // Nếu là nhân viên, mở MainNhanVien
                        MainNhanVien mainNhanVien = new MainNhanVien();
                        mainNhanVien.setVisible(true);
                        ma_nhanVienDN = d.getma_nhanVien();
                    }
                    this.dispose();  // Đóng cửa sổ đăng nhập
                    return;
                } catch (ParseException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    // Nếu không tìm thấy tài khoản hoặc mật khẩu sai
    JOptionPane.showMessageDialog(null, "Email hoặc mật khẩu không chính xác! Vui lòng thử lại");
    return;
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneBorder1 = new View.swing.PaneBorder();
        paneBorder2 = new View.swing.PaneBorder();
        txt_taiKhoan2 = new javax.swing.JTextField();
        txt_matKhau = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_dangNhap = new com.Product.swing.ButtonBadges();
        btn_thoat = new com.Product.swing.ButtonBadges();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        paneBorder1.setBackground(new java.awt.Color(255, 255, 255));

        paneBorder2.setBackground(new java.awt.Color(102, 0, 102));

        txt_taiKhoan2.setBackground(new java.awt.Color(255, 255, 255));
        txt_taiKhoan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_taiKhoan2ActionPerformed(evt);
            }
        });

        txt_matKhau.setBackground(new java.awt.Color(255, 255, 255));
        txt_matKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_matKhauActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tài Khoản:");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Mật Khẩu:");

        btn_dangNhap.setForeground(new java.awt.Color(0, 0, 0));
        btn_dangNhap.setText("Đăng Nhập");
        btn_dangNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_dangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dangNhapActionPerformed(evt);
            }
        });
        btn_dangNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_dangNhapKeyPressed(evt);
            }
        });

        btn_thoat.setForeground(new java.awt.Color(0, 0, 0));
        btn_thoat.setText("Thoát");
        btn_thoat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneBorder2Layout = new javax.swing.GroupLayout(paneBorder2);
        paneBorder2.setLayout(paneBorder2Layout);
        paneBorder2Layout.setHorizontalGroup(
            paneBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneBorder2Layout.createSequentialGroup()
                .addGroup(paneBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneBorder2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneBorder2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(paneBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_matKhau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_taiKhoan2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, paneBorder2Layout.createSequentialGroup()
                        .addComponent(btn_dangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_thoat, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        paneBorder2Layout.setVerticalGroup(
            paneBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneBorder2Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(paneBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txt_taiKhoan2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(paneBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(paneBorder2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt_matKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46)
                .addGroup(paneBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_dangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_thoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setForeground(new java.awt.Color(102, 0, 102));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/ruou.jpg"))); // NOI18N

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font(".VnCentury SchoolbookH", 3, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("OSAKE WINES");

        javax.swing.GroupLayout paneBorder1Layout = new javax.swing.GroupLayout(paneBorder1);
        paneBorder1.setLayout(paneBorder1Layout);
        paneBorder1Layout.setHorizontalGroup(
            paneBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(75, 75, 75)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paneBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paneBorder1Layout.setVerticalGroup(
            paneBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(paneBorder1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(paneBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void txt_taiKhoan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_taiKhoan2ActionPerformed
        // TODO add your handling code here:
        txt_taiKhoan2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    check(nv.DangNhap());
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }//GEN-LAST:event_txt_taiKhoan2ActionPerformed

    private void btn_dangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dangNhapActionPerformed
        try {
            check(nv.DangNhap());
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_dangNhapActionPerformed

    private void btn_thoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thoatActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btn_thoatActionPerformed

    private void txt_matKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_matKhauActionPerformed
        // TODO add your handling code here:
        txt_matKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    check(nv.DangNhap());
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }//GEN-LAST:event_txt_matKhauActionPerformed

    private void btn_dangNhapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_dangNhapKeyPressed
    
       
    }//GEN-LAST:event_btn_dangNhapKeyPressed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.Product.swing.ButtonBadges btn_dangNhap;
    private com.Product.swing.ButtonBadges btn_thoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private View.swing.PaneBorder paneBorder1;
    private View.swing.PaneBorder paneBorder2;
    private javax.swing.JPasswordField txt_matKhau;
    private javax.swing.JTextField txt_taiKhoan;
    private javax.swing.JTextField txt_taiKhoan1;
    private javax.swing.JTextField txt_taiKhoan2;
    // End of variables declaration//GEN-END:variables
}
